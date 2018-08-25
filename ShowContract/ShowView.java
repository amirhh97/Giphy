package com.example.aebrahimi.firstmvp.ShowContract;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class ShowView extends AppCompatActivity implements ShowContract.View {
    ImageView gifPreview;
    ProgressBar progressBar;
    ProgressBar horizentalProgressBar;
    Button randomButton;
    int period = 10;
    ObjectAnimator animator;
    @Inject
    ShowContract.Presenter presenter;
    Disposable disposable;
    Disposable timerDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //inject
        App.getInjector().inject(this);
        presenter.attach(this);
        gifPreview = findViewById(R.id.gif_preview);
        progressBar = findViewById(R.id.progressBarShow);
        horizentalProgressBar = findViewById(R.id.horizental_progressbar);
        randomButton = findViewById(R.id.button);
        Item item = (Item) getIntent().getSerializableExtra(Constants.intentKey);
        //instead of observer for setting progress bar you can use ObjectAnimator
        // animator.ofInt(horizentalProgressBar,"progress",10000,0).setDuration(3000).setInterpolator(new LinearInterpolator());
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
                timerDisposable = getTimeDisposable();
                disposable = getDisposable();
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

    private io.reactivex.Observable getTimerObservable() {
        return Observable.timer(period, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private io.reactivex.Observable getClickObservable() {
        return RxView.clicks(randomButton).observeOn(AndroidSchedulers.mainThread());
    }

    Observable getTimeObservable() {
        return Observable.interval(1l, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread());

    }

    Observable getButtonEventObservable() {

        return Observable.create(new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(ObservableEmitter<View> emitter) throws Exception {
                randomButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emitter.onNext(v);
                    }
                });

            }
        });


    }

    io.reactivex.functions.Consumer<Object> getConsumer() {
        return new io.reactivex.functions.Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(ShowView.this, "please wait", Toast.LENGTH_SHORT).show();
                disposable.dispose();
                timerDisposable.dispose();
                horizentalProgressBar.setProgress(0);
                randomButton.setEnabled(false);
                presenter.getRandomItems();
                showProgress();
            }
        };
    }

    Disposable getDisposable() {
        return getButtonEventObservable().mergeWith(getTimerObservable()).subscribe(getConsumer());
    }

    Disposable getTimeDisposable() {
        return getTimeObservable().subscribe(getTimerConsumer());
    }

    io.reactivex.functions.Consumer getTimerConsumer() {
        return new io.reactivex.functions.Consumer<Long>() {

            @Override
            public void accept(Long o) throws Exception {
                horizentalProgressBar.setProgress(o.intValue());

            }
        };
    }
}




