package br.org.inec.kdtumahgithub;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RepositoryProfileActivity extends AppCompatActivity {

    private TextView mRepositoryName;
    private TextView mRepositoryOwnerName;
    private TextView mRepositoryDescription;
    private TextView mRepositoryLanguage;
    private TextView mRepositoryPrivacy;
    private String mRepositoryHomePage;
    private Button mOpenHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.repository_profile_activity_title);
        setContentView(R.layout.activity_repository_profile);
        mRepositoryName = (TextView) findViewById(R.id.repository_profile_repository_name);
        mRepositoryOwnerName = (TextView) findViewById(R.id.repository_profile_owner_name_text);
        mRepositoryDescription = (TextView) findViewById(R.id.repository_profile_description_text);
        mRepositoryLanguage = (TextView) findViewById(R.id.repository_profile_language_text);
        mRepositoryPrivacy = (TextView) findViewById(R.id.repository_profile_private_text);
        mOpenHomeButton = (Button) findViewById(R.id.repository_profile_open_home_button);

        populateFieldsWithIntent();

        mOpenHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepositoryHomePage));
                startActivity(intent);
            }
        });
    }

    private void populateFieldsWithIntent() {
        Intent intent = getIntent();
        mRepositoryName.setText(intent.getStringExtra("repository_name"));
        mRepositoryOwnerName.setText(intent.getStringExtra("repository_owner_name"));
        verifyDescription(intent.getStringExtra("repository_description"));
        verifyLanguage(intent.getStringExtra("repository_language"));
        correctTextPrivacy(intent.getBooleanExtra("repository_private", false));
        mRepositoryHomePage = intent.getStringExtra("repository_home");
    }

    private void verifyDescription(String description) {
        if (description.equals("null")) {
            description = "Sem descrição";
        }
        mRepositoryDescription.setText(description);
    }

    private void verifyLanguage(String language) {
        if (language.equals("null")) {
            language = "Linguagem não definida";
        }
        mRepositoryLanguage.setText(language);
    }

    private void correctTextPrivacy(boolean isPrivate) {
        String privacy = "Não";
        if (isPrivate) {
            privacy = "Sim";
        }
        mRepositoryPrivacy.setText(privacy);
    }
}
