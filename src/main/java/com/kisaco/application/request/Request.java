package com.kisaco.application.request;

public class Request {
    private final long id;
    private final long urlID;
    private String requestIP;
    private String countryCode;
    private int requestReferrer;
    private final int createdAt;

    public Request(long id, long urlID, String requestIP, String countryCode, int requestReferrer, int createdAt) {
        this.id = id;
        this.urlID = urlID;
        this.requestIP = requestIP;
        this.countryCode = countryCode;
        this.requestReferrer = requestReferrer;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getUrlID() {
        return urlID;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getRequestReferrer() {
        return requestReferrer;
    }

    public void setRequestReferrer(int requestReferrer) {
        this.requestReferrer = requestReferrer;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "URL_Request{" +
                "id=" + id +
                ", urlID=" + urlID +
                ", requestIP='" + requestIP + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", requestReferrer=" + requestReferrer +
                ", createdAt=" + createdAt +
                '}';
    }
}
