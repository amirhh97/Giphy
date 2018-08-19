package com.example.aebrahimi.firstmvp.ShowContract;
import android.util.Log;
import com.example.aebrahimi.firstmvp.BaseContract.BaseContract;
import com.example.aebrahimi.firstmvp.Constants;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.Model.RandomModel;
import com.example.aebrahimi.firstmvp.Network.GiphyApi;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aebrahimi on 8/14/2018 AD.
 */

public class ShowPresenterImp implements ShowContract.Presenter {
    WeakReference<ShowContract.View> view;
    GiphyApi api;

    @Inject
    public ShowPresenterImp(GiphyApi api) {
        this.api = api;
    }

    @Override
    public void getRandomItems() {
        api.getRandoms(Constants.key).enqueue(new Callback<RandomModel>() {
            @Override
            public void onResponse(Call<RandomModel> call, Response<RandomModel> response) {
                RandomModel model = response.body();
                Item item = new Item();
                if (model.getData().getUser() != null)
                    item.setTitle(model.getData().getUser().getDisplay_name());
                item.setUrl(model.getData().getImage().getOriginalImage().getUrl());
                item.setOriginalUrl(model.getData().getImage().getOriginalImage().getUrl());
                item.setOriginalUrl(item.getOriginalUrl().replace("giphy_s", "200w"));
                view.get().ShowRandomItem(item);
            }

            @Override
            public void onFailure(Call<RandomModel> call, Throwable t) {
                Log.d("response failed", t.getMessage());
            }
        });

    }


    @Override
    public void attach(BaseContract.View view) {
        this.view = new WeakReference<>((ShowContract.View) view);
    }

    @Override
    public void detach() {
        this.view.clear();
    }
}
