package br.org.inec.kdtumahgithub.data;

/**
 * Classe que implementa as variáveis e métodos do objeto User
 */
public class User {

    private String mLogin;
    private String mAvatarUrl;
    private String mHomeUrl;
    private String mRepositoriesQuery;
    private String mNumberRepositoryPublic;
    private String mName;
    private String mCompany;
    private String mBlog;

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

    public User(String login, String numberRepositoryPublic, String name, String company, String blog) {
        mLogin = login;
        mNumberRepositoryPublic = numberRepositoryPublic;
        mName = name;
        mCompany = company;
        mBlog = blog;
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

    public String getmNumberRepositoryPublic() {
        return mNumberRepositoryPublic;
    }

    public void setmNumberRepositoryPublic(String mNumberRepositoryPublic) {
        this.mNumberRepositoryPublic = mNumberRepositoryPublic;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmBlog() {
        return mBlog;
    }

    public void setmBlog(String mBlog) {
        this.mBlog = mBlog;
    }

}
