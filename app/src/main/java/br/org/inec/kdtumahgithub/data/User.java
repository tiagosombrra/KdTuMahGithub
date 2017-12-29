package br.org.inec.kdtumahgithub.data;


public class User {
    private String mLogin;
    private String mAvatarUrl;
    private String mHomeUrl;
    private String mRepositoriesQuery;

    public User() {
        mLogin = "";
        mAvatarUrl = "";
        mHomeUrl = "";
    }

    public User(String login, String avatarUrl, String homeUrl, String repositoriesQuery) {
        mLogin = login;
        mAvatarUrl = avatarUrl;
        mHomeUrl = homeUrl;
        mRepositoriesQuery = repositoriesQuery;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }

    public String getHomeUrl() {
        return mHomeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.mHomeUrl = homeUrl;
    }

    public String getRepositoriesQuery() {
        return mRepositoriesQuery;
    }

    public void setRepositoriesQuery(String repositoriesQuery) {
        this.mRepositoriesQuery = repositoriesQuery;
    }
}
