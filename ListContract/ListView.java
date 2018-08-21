package com.example.aebrahimi.firstmvp.ListContract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.aebrahimi.firstmvp.App;
import com.example.aebrahimi.firstmvp.Model.Item;
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
                    presenter.getListItems();
                }
            }
        });

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

}
