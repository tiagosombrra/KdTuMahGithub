package br.org.inec.kdtumahgithub;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import br.org.inec.kdtumahgithub.adapter.UserRepositoriesArrayAdapter;
import br.org.inec.kdtumahgithub.apicontrol.GithubAPIManager;
import br.org.inec.kdtumahgithub.data.GithubRepository;

import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView mUserAvatar;
    private TextView mUserLogin;
    private ListView mUserRepositoriesList;
    private ProgressBar mProgressBar;
    private TextView mRepositoriesLabel;
    private TextView mNoResultsText;
    private Button mOpenHomeButton;
    private String mUserHomeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.user_profile_activity_title);
        setContentView(R.layout.activity_user_profile);

        mUserAvatar = (ImageView) findViewById(R.id.user_profile_user_avatar);
        mUserLogin = (TextView) findViewById(R.id.user_profile_user_name);
        mUserRepositoriesList = (ListView) findViewById(R.id.user_profile_user_repositories_list);
        mProgressBar = (ProgressBar) findViewById(R.id.user_profile_repositories_progressbar);
        mRepositoriesLabel = (TextView) findViewById(R.id.user_profile_user_repositories_label);
        mNoResultsText = (TextView) findViewById(R.id.user_profile_no_repositories);
        mOpenHomeButton = (Button) findViewById(R.id.user_profile_open_home_button);

        populateFieldsWithIntent();

        mUserRepositoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                GithubRepository user = (GithubRepository) mUserRepositoriesList.getItemAtPosition(index);
                openRepositoryProfile(user);
            }
        });

        mOpenHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUserHomeUrl));
                startActivity(intent);
            }
        });
    }

    private void openRepositoryProfile(GithubRepository repository) {
        Intent intent = new Intent(UserProfileActivity.this, RepositoryProfileActivity.class);
        intent.putExtra("repository_name", repository.getName());
        intent.putExtra("repository_owner_name", repository.getOwnerName());
        intent.putExtra("repository_home", repository.getHomeUrl());
        intent.putExtra("repository_description", repository.getDescription());
        intent.putExtra("repository_language", repository.getLanguage());
        intent.putExtra("repository_private", repository.isPrivate());
        UserProfileActivity.this.startActivity(intent);
    }

    private void populateFieldsWithIntent() {
        Intent intent = getIntent();
        Glide.with(this)
                .load(intent.getStringExtra("user_avatar"))
                .override(350,350)
                .centerCrop()
                .into(mUserAvatar);
        mUserLogin.setText(intent.getStringExtra("user_login"));
        mUserHomeUrl = intent.getStringExtra("user_home");
        String query = intent.getStringExtra("user_repositories_query");
        Log.i("QUERY", query);
        new UserRepositoriesTask().execute(query);
    }

    private class UserRepositoriesTask extends AsyncTask<String, Void, List<GithubRepository>> {

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mUserRepositoriesList.setVisibility(View.GONE);
            mRepositoriesLabel.setVisibility(View.GONE);
            mNoResultsText.setVisibility(View.GONE);
        }

        protected List<GithubRepository> doInBackground(String... params) {
            String query = params[0];
            List<GithubRepository> repositories;
            GithubAPIManager githubAPIManager = new GithubAPIManager();
            repositories = githubAPIManager.searchForUserRepositories(query);
            return repositories;
        }

        protected void onPostExecute(List<GithubRepository> response) {
            mProgressBar.setVisibility(View.GONE);
            if(response != null) {
                Log.i("RESPONSE", response.toString());
                UserRepositoriesArrayAdapter adapter = new UserRepositoriesArrayAdapter(UserProfileActivity.this,
                        R.layout.user_repositories_list_row, response);
                mRepositoriesLabel.setVisibility(View.VISIBLE);
                mUserRepositoriesList.setAdapter(adapter);
                mUserRepositoriesList.setVisibility(View.VISIBLE);
            } else {
                mNoResultsText.setVisibility(View.VISIBLE);
            }
        }
    }
}
