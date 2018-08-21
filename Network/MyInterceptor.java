package com.example.aebrahimi.firstmvp.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SeyedAmirhoseinHoseini on 8/21/18.
 */

public class MyInterceptor implements okhttp3.Interceptor {
    ConnectivityManager cm;
    public MyInterceptor(ConnectivityManager c)
    {
        cm=c;
    }

    @Override
    public Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
        if (isConnected()) {
            try {
                throw new MyNetworkExcption();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Request.Builder r = chain.request().newBuilder();
        return chain.proceed(r.build());
    }
    protected boolean isConnected() {
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
