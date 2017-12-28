package br.org.inec.kdtumahgithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mUserSearchButton;
    private Button mRepositorySearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.main_activity_title);
        setContentView(R.layout.activity_main);
        mUserSearchButton = (Button) findViewById(R.id.user_search_button);
        mRepositorySearchButton = (Button) findViewById(R.id.repository_search_button);

        mUserSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userSearchIntent = new Intent(MainActivity.this, UserSearchActivity.class);
                MainActivity.this.startActivity(userSearchIntent);
            }
        });

        mRepositorySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent repositorySearchIntent = new Intent(MainActivity.this, RepositorySearchActivity.class);
                MainActivity.this.startActivity(repositorySearchIntent);
            }
        });
    }
}
