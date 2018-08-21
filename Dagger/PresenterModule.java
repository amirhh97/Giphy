package com.example.aebrahimi.firstmvp.Dagger;

import com.example.aebrahimi.firstmvp.DataBase.AppDataBase;
import com.example.aebrahimi.firstmvp.ListContract.ListContract;
import com.example.aebrahimi.firstmvp.ListContract.ListPresenterImp;
import com.example.aebrahimi.firstmvp.Network.GiphyApi;
import com.example.aebrahimi.firstmvp.ShowContract.ShowContract;
import com.example.aebrahimi.firstmvp.ShowContract.ShowPresenterImp;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by aebrahimi on 8/14/2018 AD.
 */
@Module
public class PresenterModule {

    @Provides
    ListContract.Presenter provideShowPresenter(GiphyApi api, CompositeDisposable compositeDisposable,AppDataBase db) {
        return new ListPresenterImp(api,compositeDisposable,db);
    }

    @Provides
    ShowContract.Presenter provideListPresenter(GiphyApi api,CompositeDisposable compositeDisposable) {
        return new ShowPresenterImp(api,compositeDisposable);
    }
    @Provides
    CompositeDisposable provideCompositDisposable()
    {
        return  new CompositeDisposable();
    }



}
