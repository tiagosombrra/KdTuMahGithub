package br.org.inec.kdtumahgithub.data;

public class Repository {
    private String mName;
    private String mOwnerName;
    private String mHomeUrl;
    private String mDescription;
    private String mLanguage;
    private boolean mIsPrivate;

    public Repository() {
        mName = "";
        mOwnerName = "";
        mHomeUrl = "";
        mDescription = "";
        mLanguage = "";
        mIsPrivate = false;
    }

    public Repository(String name, String ownerName, String homeUrl, String description,
                      String languange, boolean isPrivate) {
        mName = name;
        mOwnerName = ownerName;
        mHomeUrl = homeUrl;
        mDescription = description;
        mLanguage = languange;
        mIsPrivate = isPrivate;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public void setOwnerName(String mOwnerName) {
        this.mOwnerName = mOwnerName;
    }

    public String getHomeUrl() {
        return mHomeUrl;
    }

    public void setHomeUrl(String mHomeUrl) {
        this.mHomeUrl = mHomeUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public boolean isPrivate() {
        return mIsPrivate;
    }
}
