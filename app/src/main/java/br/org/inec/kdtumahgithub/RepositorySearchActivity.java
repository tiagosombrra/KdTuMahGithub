package br.org.inec.kdtumahgithub;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.org.inec.kdtumahgithub.adapter.RepositoryArrayAdapter;
import br.org.inec.kdtumahgithub.adapter.UserArrayAdapter;
import br.org.inec.kdtumahgithub.apicontrol.GithubAPIManager;
import br.org.inec.kdtumahgithub.data.GithubRepository;
import br.org.inec.kdtumahgithub.data.GithubUser;

import java.util.List;

public class RepositorySearchActivity extends AppCompatActivity {

    private EditText mSearchText;
    private Button mSearchButton;
    private ListView mRepositoryList;
    private ProgressBar mProgressBar;
    private TextView mNoResultsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.repository_search_activity_title);
        setContentView(R.layout.activity_repository_search);

        mSearchText = (EditText) findViewById(R.id.repository_search_text);
        mSearchButton = (Button) findViewById(R.id.repository_search_search_button);
        mRepositoryList = (ListView) findViewById(R.id.repository_list);
        mProgressBar = (ProgressBar) findViewById(R.id.repository_search_progressbar);
        mNoResultsText = (TextView) findViewById(R.id.repository_search_no_results_text);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RepositorySearchTask().execute(mSearchText.getText().toString());
            }
        });

        mRepositoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                GithubRepository user = (GithubRepository) mRepositoryList.getItemAtPosition(index);
                openRepositoryProfile(user);
            }
        });
    }

    private void openRepositoryProfile(GithubRepository repository) {
        Intent intent = new Intent(RepositorySearchActivity.this, RepositoryProfileActivity.class);
        intent.putExtra("repository_name", repository.getName());
        intent.putExtra("repository_owner_name", repository.getOwnerName());
        intent.putExtra("repository_home", repository.getHomeUrl());
        intent.putExtra("repository_description", repository.getDescription());
        intent.putExtra("repository_language", repository.getLanguage());
        intent.putExtra("repository_private", repository.isPrivate());
        RepositorySearchActivity.this.startActivity(intent);
    }

    private class RepositorySearchTask extends AsyncTask<String, Void, List<GithubRepository>> {

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mRepositoryList.setVisibility(View.GONE);
            mNoResultsText.setVisibility(View.GONE);
        }

        protected List<GithubRepository> doInBackground(String... params) {
            String searchText = params[0];
            List<GithubRepository> repositories;
            GithubAPIManager githubAPIManager = new GithubAPIManager();
            repositories = githubAPIManager.searchForRepositories(searchText);
            return repositories;
        }

        protected void onPostExecute(List<GithubRepository> response) {
            mProgressBar.setVisibility(View.GONE);
            if(response != null) {
                Log.i("RESPONSE", response.toString());
                RepositoryArrayAdapter adapter = new RepositoryArrayAdapter(RepositorySearchActivity.this,
                        R.layout.repository_list_row, response);
                mRepositoryList.setAdapter(adapter);
                mRepositoryList.setVisibility(View.VISIBLE);
            } else {
                mNoResultsText.setVisibility(View.VISIBLE);
            }
        }
    }
}
