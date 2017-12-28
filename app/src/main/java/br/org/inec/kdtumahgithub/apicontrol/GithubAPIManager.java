package br.org.inec.kdtumahgithub.apicontrol;

import android.util.Log;

import br.org.inec.kdtumahgithub.data.GithubRepository;
import br.org.inec.kdtumahgithub.data.GithubUser;
import br.org.inec.kdtumahgithub.jsonmanager.JSONManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class GithubAPIManager {
    private static final String GITHUB_API_USER_SEARCH_URL =
            "https://api.github.com/search/users?q=";
    private static final String GITHUB_API_REPOSITORY_SEARCH_URL =
            "https://api.github.com/search/repositories?q=";

    public GithubAPIManager() {
    }

    public List<GithubUser> searchForUsers(String searchText) {
        String responseContent = callUserSearch(searchText);
        JSONManager jsonManager = new JSONManager();
        List<GithubUser> foundUsers = jsonManager.getUsersFromJSON(responseContent);
        return foundUsers;
    }

    public List<GithubRepository> searchForRepositories(String searchText) {
        String responseContent = callRepositorySearch(searchText);
        JSONManager jsonManager = new JSONManager();
        List<GithubRepository> foundRepositories = jsonManager.getRepositoriesFromJSON(responseContent);
        return foundRepositories;
    }

    public List<GithubRepository> searchForUserRepositories(String query) {
        String responseContent = callUserRepositoriesSearch(query);
        JSONManager jsonManager = new JSONManager();
        List<GithubRepository> foundRepositories = jsonManager.getUserRepositoriesFromJSON(responseContent);
        return foundRepositories;
    }

    private String readContentFromJSON(String query) {
        StringBuilder stringBuilder = null;
        try {
            URL url = new URL(query);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return "";
        }
        return stringBuilder.toString();
    }

    private String callUserSearch(String searchText) {
        return readContentFromJSON(GITHUB_API_USER_SEARCH_URL + searchText);
    }

    private String callRepositorySearch(String searchText) {
        return readContentFromJSON(GITHUB_API_REPOSITORY_SEARCH_URL + searchText);
    }

    private String callUserRepositoriesSearch(String query) {
        return readContentFromJSON(query);
    }
}
