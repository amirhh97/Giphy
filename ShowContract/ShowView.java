package com.example.aebrahimi.firstmvp.ShowContract;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.aebrahimi.firstmvp.App;
import com.example.aebrahimi.firstmvp.Constants;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ShowView extends AppCompatActivity implements ShowContract.View {
    ImageView gifPreview;
    ProgressBar progressBar;
    Button randomButton;
    int period=10;
    @Inject
    ShowContract.Presenter presenter;
    Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //inject
        App.getInjector().inject(this);
        presenter.attach(this);
        gifPreview = findViewById(R.id.gif_preview);
        progressBar = findViewById(R.id.progressBarShow);
        randomButton = findViewById(R.id.button);
        Item item = (Item) getIntent().getSerializableExtra(Constants.intentKey);
        ShowRandomItem(item);


    }


    @Override
    public void ShowRandomItem(Item item) {
        Glide.with(this).load(item.getOriginalUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                hideProgress();
                randomButton.setEnabled(true);
                disposable=getDisposable();
                return false;
            }
        }).into(gifPreview);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
      //  disposable.dispose();
    }
    private io.reactivex.Observable getTimerObservable(){
        return  Observable.timer(period, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }
    private io.reactivex.Observable getClickObservable(){
        return   RxView.clicks(randomButton).observeOn(AndroidSchedulers.mainThread());
    }
    Observable getButtonEventObservable(){

       return null;
    }

    io.reactivex.functions.Consumer<Object> getConsumer()
    {
        return new io.reactivex.functions.Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(ShowView.this,"please wait",Toast.LENGTH_SHORT).show();
                disposable.dispose();
                randomButton.setEnabled(false);
                presenter.getRandomItems();
                showProgress();
            }
        };
    }

    Disposable getDisposable()
    {
        return getClickObservable().mergeWith(getTimerObservable()).subscribe(getConsumer());
    }

}
