package com.example.aebrahimi.firstmvp.Dagger;

import android.net.ConnectivityManager;

import com.example.aebrahimi.firstmvp.App;
import com.example.aebrahimi.firstmvp.Network.GiphyApi;
import com.example.aebrahimi.firstmvp.Network.MyInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aebrahimi on 8/14/2018 AD.
 */
@Module
public class NetworkModule {
    String baseUrl = "https://api.giphy.com/";
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new MyInterceptor((ConnectivityManager) App.getContext().getSystemService(App.getContext().CONNECTIVITY_SERVICE))).build();
    }

    @Provides
    @Singleton
    Gson gsonProvider() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    @Provides
    @Singleton
    GiphyApi provideApi(Retrofit retrofit) {
        return retrofit.create(GiphyApi.class);
    }
   /* @Provides
    @Singleton
    Interceptor provideConnectivityManager()
    {
        return new MyInterceptor();
    }*/

}
