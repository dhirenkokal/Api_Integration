package com.example.api_integration.model;

public class imageModel {
    private UrlModel url;

    public imageModel(UrlModel url) {
        this.url = url;
    }

    public UrlModel getUrl() {
        return url;
    }

    public void setUrl(UrlModel url) {
        this.url = url;
    }
}
