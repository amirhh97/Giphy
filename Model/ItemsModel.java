package com.example.aebrahimi.firstmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public class ItemsModel {
    ArrayList< GifModel > data = new ArrayList  ();

    public ArrayList<GifModel> getData() {
        return data;
    }

    public void setData(ArrayList<GifModel> data) {
        this.data = data;
    }
    @SerializedName("pagination")
    Pagination pagination;
    Meta meta;


    // Getter Methods

    public Pagination getPagination() {
        return pagination;
    }

    public Meta getMeta() {
        return meta;
    }

    // Setter Methods

    public void setPagination(Pagination paginationObject) {
        this.pagination = paginationObject;
    }

    public void setMeta(Meta metaObject) {
        this.meta = metaObject;
    }
}
