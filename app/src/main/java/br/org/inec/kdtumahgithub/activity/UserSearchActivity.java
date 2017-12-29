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
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

public class UserSearchActivity extends AppCompatActivity {

    @BindView(R.id.user_list) ListView mUserList;
    @BindView(R.id.user_search_progressbar) ProgressBar mProgressBar;
    @BindView(R.id.user_search_no_results_text) TextView mNoResultsText;
    @BindView(R.id.search_view) SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.user_search_activity_title);
        setContentView(R.layout.activity_user_search);

        //inicialização do ButterKnife
        ButterKnife.bind(this);

        //configuração da view de pesquisa de usuário
        searchView.setQueryHint(getResources().getString(R.string.user_search_view));
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();

        //método para capturar o usuário digitado e enviar o texto digitado para pesquisar na API do Github
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                new UsersSearchTask().execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        //método que captura o click na célula da listView de usuários encontrados e
        // envia as informações para o próxima tela
        mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                User user = (User) mUserList.getItemAtPosition(index);
                openUserProfile(user);
            }
        });
    }

    //método que reune as informações do usuário para ser enviado para a tela seguinte
    private void openUserProfile(User user) {
        Intent intent = new Intent(UserSearchActivity.this, UserProfileActivity.class);
        intent.putExtra("user_login", user.getLogin());
        intent.putExtra("user_avatar", user.getAvatarUrl());
        intent.putExtra("user_home", user.getHomeUrl());
        intent.putExtra("user_repositories_query", user.getRepositoriesQuery());
        UserSearchActivity.this.startActivity(intent);
    }

    //método para a busca de usuários usando a API do Gituhb com tratamento para resultados vazios
    private class UsersSearchTask extends AsyncTask<String, Void, List<User>> {

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
