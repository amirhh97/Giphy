package com.example.aebrahimi.firstmvp.Model;

import com.example.aebrahimi.firstmvp.Network.GiphyApi;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aebrahimi on 8/14/2018 AD.
 */

public class RandomModel {
    public Meta getMeta() {
        return meta;
    }

    GifModel data ;

    public GifModel getData() {
        return data;
    }

    public void setData(GifModel data) {
        this.data = data;
    }
    Meta meta;
    // Getter Methods

    public void setMeta(Meta metaObject) {
        this.meta = metaObject;
    }
}
