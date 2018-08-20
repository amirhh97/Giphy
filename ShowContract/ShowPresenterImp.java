package com.example.aebrahimi.firstmvp.ShowContract;

import android.util.Log;

import com.example.aebrahimi.firstmvp.BaseContract.BaseContract;
import com.example.aebrahimi.firstmvp.Constants;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.Model.RandomModel;
import com.example.aebrahimi.firstmvp.Network.GiphyApi;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aebrahimi on 8/14/2018 AD.
 */

public class ShowPresenterImp implements ShowContract.Presenter {
    WeakReference<ShowContract.View> view;
    GiphyApi api;
    Disposable disposable;
    CompositeDisposable container;
    @Inject
    public ShowPresenterImp(GiphyApi api,CompositeDisposable container) {
        this.api = api;
        this.container=container;

    }

    @Override
    public void getRandomItems() {
        disposable=api.getRandoItem(Constants.key).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(getConsumer());
        container.add(disposable);

    }


    @Override
    public void attach(BaseContract.View view) {
        this.view = new WeakReference<>((ShowContract.View) view);
    }

    @Override
    public void detach() {
        container.clear();
        this.view.clear();
    }
    Consumer<RandomModel>getConsumer()
    {
        return model -> {
            Item item = new Item();
            if (model.getData().getUser() != null)
                item.setTitle(model.getData().getUser().getDisplay_name());
            item.setUrl(model.getData().getImage().getOriginalImage().getUrl());
            item.setOriginalUrl(model.getData().getImage().getOriginalImage().getUrl());
            item.setOriginalUrl(item.getOriginalUrl().replace("giphy_s", "200w"));
            view.get().ShowRandomItem(item);
        };
    }
}
