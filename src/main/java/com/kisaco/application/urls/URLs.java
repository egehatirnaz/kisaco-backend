package com.kisaco.application.urls;

import javax.persistence.*;

@Entity
@Table(name = "URLs")
public class URLs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userID;
    @Column(name = "orig_url")
    private String origURL;
    @Column(name = "short_url")
    private String shortURL;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "expires_at")
    private long expiresAt = 0;
    @Column(name = "creator_ip")
    private String creatorIP;
    @Column(name = "visitor_count")
    private int visitorCount;
    @Column(name = "visitor_limit")
    private int visitorLimit = 0;
    @Column(name = "is_private")
    private Boolean isPrivate = false;

    public URLs(){}
    public URLs(Integer userID, String origURL, String shortURL, int createdAt, int expiresAt, String creatorIP, int visitorCount, int visitorLimit, Boolean isPrivate) {
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

    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) { this.userID = userID; }

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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
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
