package com.kisaco.application.requests;

import javax.persistence.*;

@Entity
@Table(name = "Url_Requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "url_id")
    private Integer urlID;
    @Column(name = "request_ip")
    private String requestIP;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "request_referrer")
    private int requestReferrer;
    @Column(name = "created_at")
    private int createdAt;

    public Requests(){}

    public Requests(Integer id, Integer urlID, String requestIP, String countryCode, int requestReferrer, int createdAt) {
        this.id = id;
        this.urlID = urlID;
        this.requestIP = requestIP;
        this.countryCode = countryCode;
        this.requestReferrer = requestReferrer;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUrlID() {
        return urlID;
    }

    public void setUrlID(Integer urlID){
        this.urlID = urlID;
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

    public void setCreatedAt(int createdAt){
        this.createdAt = createdAt;
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
