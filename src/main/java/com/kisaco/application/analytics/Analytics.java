package com.kisaco.application.analytics;

import com.kisaco.application.requests.Requests;
import com.kisaco.application.urls.URLs;

public class Analytics {
    private URLs url;
    private Iterable<Requests> requests;

    public Analytics(URLs url, Iterable<Requests> requests){
        this.url = url;
        this.requests = requests;
    }

    public URLs getUrl() {
        return url;
    }

    public void setUrl(URLs url) {
        this.url = url;
    }

    public Iterable<Requests> getRequests() {
        return requests;
    }

    public void setRequests(Iterable<Requests> requests) {
        this.requests = requests;
    }
}
