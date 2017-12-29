package br.org.inec.kdtumahgithub.activity;

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

import br.org.inec.kdtumahgithub.R;
import br.org.inec.kdtumahgithub.adapter.UserRepositoriesArrayAdapter;
import br.org.inec.kdtumahgithub.apicontrol.APIManager;
import br.org.inec.kdtumahgithub.data.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

import java.util.List;

/**
 * Classe de implementação da tela com detalhes do usuário
 */
public class UserProfileActivity extends AppCompatActivity {

    @BindView(R.id.user_profile_user_avatar) ImageView mUserAvatar;
    @BindView(R.id.user_profile_user_name) TextView mUserLogin;
    @BindView(R.id.user_profile_user_repositories_list) ListView mUserRepositoriesList;
    @BindView(R.id.user_profile_repositories_progressbar) ProgressBar mProgressBar;
    @BindView(R.id.user_profile_user_repositories_label) TextView mRepositoriesLabel;
    @BindView(R.id.user_profile_no_repositories) TextView mNoResultsText;
    @BindView(R.id.user_profile_open_home_button) Button mOpenHomeButton;

    private String mUserHomeUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.user_profile_activity_title);
        setContentView(R.layout.activity_user_profile);

        //inicialização do ButterKnife
        ButterKnife.bind(this);

        //carregar valores com informações da página anterior
        populateFieldsWithIntent();

       //método para carregar as informações do repositório da lista que foi clicado na lista de repositórios
        mUserRepositoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                Repository user = (Repository) mUserRepositoriesList.getItemAtPosition(index);
                openRepositoryProfile(user);
            }
        });

        //método para abrir a página do github com as informações completas do usuário
        mOpenHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUserHomeUrl));
                startActivity(intent);
            }
        });
    }

    //método que abre uma nova tela e passa por Intent as informações detalhadas do repositório
    private void openRepositoryProfile(Repository repository) {
        Intent intent = new Intent(UserProfileActivity.this, RepositoryProfileActivity.class);
        intent.putExtra("repository_name", repository.getName());
        intent.putExtra("repository_owner_name", repository.getOwnerName());
        intent.putExtra("repository_home", repository.getHomeUrl());
        intent.putExtra("repository_description", repository.getDescription());
        intent.putExtra("repository_language", repository.getLanguage());
        intent.putExtra("repository_private", repository.isPrivate());
        UserProfileActivity.this.startActivity(intent);
    }

    //método que carrega os valores com informações da página anterior recebido por Intent
    private void populateFieldsWithIntent() {
        Intent intent = getIntent();
        Glide.with(this)
                .load(intent.getStringExtra("user_avatar"))
                .override(350, 350)
                .centerCrop()
                .into(mUserAvatar);
        mUserLogin.setText(intent.getStringExtra("user_login"));
        mUserHomeUrl = intent.getStringExtra("user_home");
        String query = intent.getStringExtra("user_repositories_query");
        Log.i("QUERY", query);
        new UserRepositoriesTask().execute(query);
    }

    //método para carregar e setar as informações do usuário
    private class UserRepositoriesTask extends AsyncTask<String, Void, List<Repository>> {

        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mUserRepositoriesList.setVisibility(View.GONE);
            mRepositoriesLabel.setVisibility(View.GONE);
            mNoResultsText.setVisibility(View.GONE);
        }

        protected List<Repository> doInBackground(String... params) {
            String query = params[0];
            List<Repository> repositories;
            APIManager APIManager = new APIManager();
            repositories = APIManager.searchForUserRepositories(query);
            return repositories;
        }

        protected void onPostExecute(List<Repository> response) {
            mProgressBar.setVisibility(View.GONE);
            if (response != null) {
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
