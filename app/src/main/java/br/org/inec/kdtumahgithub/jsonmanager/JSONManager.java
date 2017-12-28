package br.org.inec.kdtumahgithub.jsonmanager;

import android.util.Log;

import br.org.inec.kdtumahgithub.data.GithubRepository;
import br.org.inec.kdtumahgithub.data.GithubUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JSONManager {
    public JSONManager() {}

    public List<GithubUser> getUsersFromJSON(String jsonContent) {
        ArrayList<GithubUser> foundUsers = new ArrayList<GithubUser>();
        try {
            JSONObject object = new JSONObject(jsonContent);
            JSONArray usersFromSearch = object.getJSONArray("items");
            for (int i=0; i<usersFromSearch.length(); i++) {
                JSONObject userData = usersFromSearch.getJSONObject(i);
                String userLogin = userData.getString("login");
                String userAvatarUrl = userData.getString("avatar_url");
                String userHomeUrl = userData.getString("html_url");
                String userRepositoriesQuery = userData.getString("repos_url");
                GithubUser githubUser = new GithubUser(userLogin, userAvatarUrl, userHomeUrl,
                        userRepositoriesQuery);
                foundUsers.add(githubUser);
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        if (foundUsers.isEmpty()) {
            return null;
        }
        return foundUsers;
    }

    public List<GithubRepository> getRepositoriesFromJSON(String jsonContent) {
        ArrayList<GithubRepository> foundRepositories = new ArrayList<GithubRepository>();
        try {
            JSONObject object = new JSONObject(jsonContent);
            JSONArray repositoriesFromSearch = object.getJSONArray("items");
            for (int i=0; i<repositoriesFromSearch.length(); i++) {
                JSONObject repositoryData = repositoriesFromSearch.getJSONObject(i);

                String name = repositoryData.getString("name");
                String ownerName = repositoryData.getJSONObject("owner").getString("login");
                String homeUrl = repositoryData.getString("html_url");
                String description = repositoryData.getString("description");
                String language = repositoryData.getString("language");
                boolean isPrivate = repositoryData.getBoolean("private");

                GithubRepository githubRepository = new GithubRepository(name, ownerName,
                                                        homeUrl, description, language,
                                                        isPrivate);
                foundRepositories.add(githubRepository);
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        if (foundRepositories.isEmpty()) {
            return null;
        }
        return foundRepositories;
    }

    public List<GithubRepository> getUserRepositoriesFromJSON(String jsonContent) {
        ArrayList<GithubRepository> foundRepositories = new ArrayList<GithubRepository>();
        try {
            JSONArray repositoriesFromSearch = new JSONArray(jsonContent);
            for (int i=0; i<repositoriesFromSearch.length(); i++) {
                JSONObject repositoryData = repositoriesFromSearch.getJSONObject(i);

                String name = repositoryData.getString("name");
                String ownerName = repositoryData.getJSONObject("owner").getString("login");
                String homeUrl = repositoryData.getString("html_url");
                String description = repositoryData.getString("description");
                String language = repositoryData.getString("language");
                boolean isPrivate = repositoryData.getBoolean("private");

                GithubRepository githubRepository = new GithubRepository(name, ownerName,
                        homeUrl, description, language,
                        isPrivate);
                foundRepositories.add(githubRepository);
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        if (foundRepositories.isEmpty()) {
            return null;
        }
        return foundRepositories;
    }
}
