package br.org.inec.kdtumahgithub.apicontrol;

import android.util.Log;

import br.org.inec.kdtumahgithub.data.Repository;
import br.org.inec.kdtumahgithub.data.User;
import br.org.inec.kdtumahgithub.jsonmanager.JSONManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Classe para gerenciamento das requisições da API do Github
 */
public class APIManager {
    private static final String GITHUB_API_USERS_SEARCH_URL =
            "https://api.github.com/search/users?q=";
    private static final String GITHUB_API_USER_SEARCH_URL =
            "https://api.github.com/users/";
    private static final String GITHUB_API_REPOSITORY_SEARCH_URL =
            "https://api.github.com/search/repositories?q=";

    public APIManager() {
    }

    public List<User> searchForUsers(String searchText) {
        String responseContent = callUsersSearch(searchText);
        JSONManager jsonManager = new JSONManager();
        List<User> foundUsers = jsonManager.getUsersFromJSON(responseContent);
        return foundUsers;
    }

    public User searchForUser(String searchText) {
        String responseContent = callUserSearch(searchText);
        JSONManager jsonManager = new JSONManager();
        User foundUser = jsonManager.getUserFromJSON(responseContent);
        return foundUser;
    }

    public List<Repository> searchForRepositories(String searchText) {
        String responseContent = callRepositorySearch(searchText);
        JSONManager jsonManager = new JSONManager();
        List<Repository> foundRepositories = jsonManager.getRepositoriesFromJSON(responseContent);
        return foundRepositories;
    }

    public List<Repository> searchForUserRepositories(String query) {
        String responseContent = callUserRepositoriesSearch(query);
        JSONManager jsonManager = new JSONManager();
        List<Repository> foundRepositories = jsonManager.getUserRepositoriesFromJSON(responseContent);
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

    private String callUsersSearch(String searchText) {
        return readContentFromJSON(GITHUB_API_USERS_SEARCH_URL + searchText);
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
