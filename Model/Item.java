package com.example.aebrahimi.firstmvp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */
@Entity(tableName = "ItemsTable")
public class Item implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "item_url")
    String url;
    @ColumnInfo(name = "item_title")
    String title;
    @ColumnInfo(name = "item_original_url")
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
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

}
