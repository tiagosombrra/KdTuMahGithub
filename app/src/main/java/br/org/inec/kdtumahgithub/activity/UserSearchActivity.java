package br.org.inec.kdtumahgithub.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import br.org.inec.kdtumahgithub.R;
import br.org.inec.kdtumahgithub.adapter.UserArrayAdapter;
import br.org.inec.kdtumahgithub.apicontrol.APIManager;
import br.org.inec.kdtumahgithub.data.User;

import java.util.List;

public class UserSearchActivity extends AppCompatActivity {

    private ListView mUserList;
    private ProgressBar mProgressBar;
    private TextView mNoResultsText;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.user_search_activity_title);
        setContentView(R.layout.activity_user_search);

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setQueryHint("Digite um usuário");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        //searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                new UserSearchTask().execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }

        });

        mUserList = (ListView) findViewById(R.id.user_list);
        mProgressBar = (ProgressBar) findViewById(R.id.user_search_progressbar);
        mNoResultsText = (TextView) findViewById(R.id.user_search_no_results_text);

        mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                User user = (User) mUserList.getItemAtPosition(index);
                openUserProfile(user);
            }
        });
    }

    private void openUserProfile(User user) {
        Intent intent = new Intent(UserSearchActivity.this, UserProfileActivity.class);
        intent.putExtra("user_login", user.getLogin());
        intent.putExtra("user_avatar", user.getAvatarUrl());
        intent.putExtra("user_home", user.getHomeUrl());
        intent.putExtra("user_repositories_query", user.getRepositoriesQuery());
        UserSearchActivity.this.startActivity(intent);
    }

    private class UserSearchTask extends AsyncTask<String, Void, List<User>> {

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mUserList.setVisibility(View.GONE);
            mNoResultsText.setVisibility(View.GONE);
        }

        protected List<User> doInBackground(String... params) {
            String searchText = params[0];
            List<User> users;
            APIManager APIManager = new APIManager();
            users = APIManager.searchForUsers(searchText);
            return users;
        }

        protected void onPostExecute(List<User> response) {
            mProgressBar.setVisibility(View.GONE);
            if (response != null) {
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
