package com.example.aebrahimi.firstmvp;
import android.app.Application;
import com.example.aebrahimi.firstmvp.Dagger.DaggerInjector;
import com.example.aebrahimi.firstmvp.Dagger.Injector;
import com.example.aebrahimi.firstmvp.Dagger.NetworkModule;
import com.example.aebrahimi.firstmvp.Dagger.PresenterModule;


/**
 * Created by aebrahimi on 8/15/2018 AD.
 */

public class App extends Application {
    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerInjector.builder().presenterModule(new PresenterModule()).build();
    }

    public static Injector getInjector() {
        return injector;
    }

}
