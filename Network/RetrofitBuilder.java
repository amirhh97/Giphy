package com.example.aebrahimi.firstmvp.Network;

import com.example.aebrahimi.firstmvp.Model.GifModel;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public class RetrofitBuilder {
    ;
    String BaseUri="https://api.giphy.com/";
    private Retrofit retrofit;
    public GiphyApi getApi()
    {
    retrofit=new Retrofit.Builder().baseUrl(BaseUri).addConverterFactory( GsonConverterFactory.create((new GsonBuilder().setLenient().create()))).build();
    return retrofit.create(GiphyApi.class);
    }

}
