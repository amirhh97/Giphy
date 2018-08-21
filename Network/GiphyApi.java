package com.example.aebrahimi.firstmvp.Network;

import com.example.aebrahimi.firstmvp.Model.ItemsModel;
import com.example.aebrahimi.firstmvp.Model.RandomModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public interface GiphyApi {
    @GET("v1/gifs/trending")
    Observable<ItemsModel> getTrending(@Query("api_key") String key, @Query("offset") int offset, @Query("limit") int limit) throws MyNetworkExcption;
    @GET("/v1/gifs/random")
    Single<RandomModel> getRandoItem(@Query("api_key") String Key);
}
