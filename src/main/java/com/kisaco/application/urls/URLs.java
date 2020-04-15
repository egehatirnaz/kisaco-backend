package com.kisaco.application.urls;

public class URLs {
    private final long id;
    private final long userID;
    private String origURL;
    private String shortURL;
    private final int createdAt;
    private int expiresAt = 0;
    private String creatorIP;
    private int visitorCount;
    private int visitorLimit = 0;
    private Boolean isPrivate = false;

    public URLs(long id, long userID, String origURL, String shortURL, int createdAt, int expiresAt, String creatorIP, int visitorCount, int visitorLimit, Boolean isPrivate) {
        this.id = id;
        this.userID = userID;
        this.origURL = origURL;
        this.shortURL = shortURL;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.creatorIP = creatorIP;
        this.visitorCount = visitorCount;
        this.visitorLimit = visitorLimit;
        this.isPrivate = isPrivate;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return userID;
    }

    public String getOrigURL() {
        return origURL;
    }

    public void setOrigURL(String origURL) {
        this.origURL = origURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public int getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(int expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getCreatorIP() {
        return creatorIP;
    }

    public void setCreatorIP(String creatorIP) {
        this.creatorIP = creatorIP;
    }

    public int getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(int visitorCount) {
        this.visitorCount = visitorCount;
    }

    public int getVisitorLimit() {
        return visitorLimit;
    }

    public void setVisitorLimit(int visitorLimit) {
        this.visitorLimit = visitorLimit;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id=" + id +
                ", userID=" + userID +
                ", origURL='" + origURL + '\'' +
                ", shortURL='" + shortURL + '\'' +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                ", creatorIP='" + creatorIP + '\'' +
                ", visitorCount=" + visitorCount +
                ", visitorLimit=" + visitorLimit +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
