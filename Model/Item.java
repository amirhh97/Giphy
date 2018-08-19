package com.example.aebrahimi.firstmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public class Item implements Serializable {
    String url;
    String title;
    String originalUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}
