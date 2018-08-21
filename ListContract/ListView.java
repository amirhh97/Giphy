package com.example.aebrahimi.firstmvp.ListContract;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aebrahimi.firstmvp.App;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.Network.MyNetworkExcption;
import com.example.aebrahimi.firstmvp.R;
import com.example.aebrahimi.firstmvp.TrendingAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListView extends AppCompatActivity implements ListContract.View {
    @Inject
    ListContract.Presenter presenter;
    TrendingAdapter adapter;
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ProgressBar progressBar;
    ProgressBar enteryProgress;
    static boolean isOnline = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_trend);
        progressBar = findViewById(R.id.progressBar);
        enteryProgress = findViewById(R.id.list_progress);
        App.getInjector().inject(this);
        presenter.attach(this);
        adapter = new TrendingAdapter(this, new ArrayList<Item>());
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TrendingAdapter(this, new ArrayList<Item>());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    progressBar.setVisibility(View.VISIBLE);
                    try {
                        presenter.getListItems();
                    } catch (MyNetworkExcption myNetworkExcption) {
                        myNetworkExcption.printStackTrace();
                    }
                }
            }
        });


            try {
                presenter.getListItems();
            } catch (MyNetworkExcption myNetworkExcption) {
                myNetworkExcption.printStackTrace();
                presenter.getCachedListItem();
                Toast.makeText(ListView.this, "No Connetion", Toast.LENGTH_SHORT).show();
            }




    }

    @Override
    public void ShowItems(List<Item> items) {
        adapter.Additems(items);
        // adapter.notifyItemRangeInserted(adapter.getItemCount()-items.size(),adapter.getItemCount());
        adapter.notifyDataSetChanged();
        hideProgress();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        enteryProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

   /* public static boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);
            sock.connect(sockaddr, timeoutMs);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }*/
}

