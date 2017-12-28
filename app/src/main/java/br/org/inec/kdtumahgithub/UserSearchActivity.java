package br.org.inec.kdtumahgithub;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.org.inec.kdtumahgithub.adapter.UserArrayAdapter;
import br.org.inec.kdtumahgithub.apicontrol.GithubAPIManager;
import br.org.inec.kdtumahgithub.data.GithubUser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserSearchActivity extends AppCompatActivity {

    private EditText mSearchText;
    private Button mSearchButton;
    private ListView mUserList;
    private ProgressBar mProgressBar;
    private TextView mNoResultsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.user_search_activity_title);
        setContentView(R.layout.activity_user_search);

        mSearchText = (EditText) findViewById(R.id.user_search_text);
        mSearchButton = (Button) findViewById(R.id.user_search_search_button);
        mUserList = (ListView) findViewById(R.id.user_list);
        mProgressBar = (ProgressBar) findViewById(R.id.user_search_progressbar);
        mNoResultsText = (TextView) findViewById(R.id.user_search_no_results_text);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserSearchTask().execute(mSearchText.getText().toString());
            }
        });

        mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                GithubUser user = (GithubUser) mUserList.getItemAtPosition(index);
                openUserProfile(user);
            }
        });
    }

    private void openUserProfile(GithubUser user) {
        Intent intent = new Intent(UserSearchActivity.this, UserProfileActivity.class);
        intent.putExtra("user_login", user.getLogin());
        intent.putExtra("user_avatar", user.getAvatarUrl());
        intent.putExtra("user_home", user.getHomeUrl());
        intent.putExtra("user_repositories_query", user.getRepositoriesQuery());
        UserSearchActivity.this.startActivity(intent);
    }

    private class UserSearchTask extends AsyncTask<String, Void, List<GithubUser>> {

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mUserList.setVisibility(View.GONE);
            mNoResultsText.setVisibility(View.GONE);
        }

        protected List<GithubUser> doInBackground(String... params) {
            String searchText = params[0];
            List<GithubUser> users;
            GithubAPIManager githubAPIManager = new GithubAPIManager();
            users = githubAPIManager.searchForUsers(searchText);
            return users;
        }

        protected void onPostExecute(List<GithubUser> response) {
            mProgressBar.setVisibility(View.GONE);
            if(response != null) {
                Log.i("RESPONSE", response.toString());
                UserArrayAdapter adapter = new UserArrayAdapter(UserSearchActivity.this,
                                                            R.layout.user_list_row, response);
                mUserList.setAdapter(adapter);
                mUserList.setVisibility(View.VISIBLE);
            } else {
                mNoResultsText.setVisibility(View.VISIBLE);
            }
        }
    }
}
