package com.example.aebrahimi.firstmvp.ListContract;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.aebrahimi.firstmvp.App;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.Network.MyNetworkExcption;
import com.example.aebrahimi.firstmvp.R;
import com.example.aebrahimi.firstmvp.TrendingAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ListView extends AppCompatActivity implements ListContract.View {
    @Inject
    ListContract.Presenter presenter;
    TrendingAdapter adapter;
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ProgressBar progressBar;
    ProgressBar enteryProgress;
    RelativeLayout layout;
    Snackbar snackbar;
    static boolean isOnline = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_trend);
        progressBar = findViewById(R.id.progressBar);
        enteryProgress = findViewById(R.id.list_progress);
        layout=findViewById(R.id.layout);
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

        }

    }

    @Override
    public void ShowItems(List<Item> items) {
        adapter.Additems(items);
        // adapter.notifyItemRangeInserted(adapter.getItemCount()-items.size(),adapter.getItemCount());
        adapter.notifyDataSetChanged();
        hideProgress();
        //hideSnackBar();

    }

    @Override
    public void showSnackBar() {
        snackbar.make(layout,"Check Your Connection",Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.getListItems();
                } catch (MyNetworkExcption myNetworkExcption) {
                    myNetworkExcption.printStackTrace();
                }
            }
        }).show();
        hideProgress();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideSnackBar() {
        snackbar.dismiss();
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

}

