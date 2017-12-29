package br.org.inec.kdtumahgithub.jsonmanager;

import android.util.Log;

import br.org.inec.kdtumahgithub.data.Repository;
import br.org.inec.kdtumahgithub.data.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe com métodos de requisições de dados JSON
 */
public class JSONManager {
    public JSONManager() {
    }

    public List<User> getUsersFromJSON(String jsonContent) {
        ArrayList<User> foundUsers = new ArrayList<User>();
        try {
            JSONObject object = new JSONObject(jsonContent);
            JSONArray usersFromSearch = object.getJSONArray("items");
            for (int i = 0; i < usersFromSearch.length(); i++) {
                JSONObject userData = usersFromSearch.getJSONObject(i);
                String userLogin = userData.getString("login");
                String userAvatarUrl = userData.getString("avatar_url");
                String userHomeUrl = userData.getString("html_url");
                String userRepositoriesQuery = userData.getString("repos_url");
                User user = new User(userLogin, userAvatarUrl, userHomeUrl,
                        userRepositoriesQuery);
                foundUsers.add(user);
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

    public List<Repository> getRepositoriesFromJSON(String jsonContent) {
        ArrayList<Repository> foundRepositories = new ArrayList<Repository>();
        try {
            JSONObject object = new JSONObject(jsonContent);
            JSONArray repositoriesFromSearch = object.getJSONArray("items");
            for (int i = 0; i < repositoriesFromSearch.length(); i++) {
                JSONObject repositoryData = repositoriesFromSearch.getJSONObject(i);

                String name = repositoryData.getString("name");
                String ownerName = repositoryData.getJSONObject("owner").getString("login");
                String homeUrl = repositoryData.getString("html_url");
                String description = repositoryData.getString("description");
                String language = repositoryData.getString("language");
                boolean isPrivate = repositoryData.getBoolean("private");

                Repository repository = new Repository(name, ownerName,
                        homeUrl, description, language,
                        isPrivate);
                foundRepositories.add(repository);
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

    public List<Repository> getUserRepositoriesFromJSON(String jsonContent) {
        ArrayList<Repository> foundRepositories = new ArrayList<Repository>();
        try {
            JSONArray repositoriesFromSearch = new JSONArray(jsonContent);
            for (int i = 0; i < repositoriesFromSearch.length(); i++) {
                JSONObject repositoryData = repositoriesFromSearch.getJSONObject(i);

                String name = repositoryData.getString("name");
                String ownerName = repositoryData.getJSONObject("owner").getString("login");
                String homeUrl = repositoryData.getString("html_url");
                String description = repositoryData.getString("description");
                String language = repositoryData.getString("language");
                boolean isPrivate = repositoryData.getBoolean("private");

                Repository repository = new Repository(name, ownerName,
                        homeUrl, description, language,
                        isPrivate);
                foundRepositories.add(repository);
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

    public User getUserFromJSON(String jsonContent) {
        User foundUser;
        try {
            JSONObject object = new JSONObject(jsonContent);
                String userLogin = object.getString("login");
                String userNumberRepositoryPublic = object.getString("public_repos");
                String userName = object.getString("name");
                String userCompany = object.getString("company");
                String userBlog = object.getString("blog");

            foundUser = new User(userLogin, userNumberRepositoryPublic, userName, userCompany, userBlog);

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }

        return foundUser;
    }
}
