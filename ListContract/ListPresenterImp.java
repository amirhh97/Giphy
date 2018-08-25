package com.example.aebrahimi.firstmvp.ListContract;

import com.example.aebrahimi.firstmvp.BaseContract.BaseContract;
import com.example.aebrahimi.firstmvp.Constants;
import com.example.aebrahimi.firstmvp.DataBase.AppDataBase;
import com.example.aebrahimi.firstmvp.Model.GifModel;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.Model.ItemsModel;
import com.example.aebrahimi.firstmvp.Network.GiphyApi;
import com.example.aebrahimi.firstmvp.Network.MyNetworkExcption;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public class ListPresenterImp implements ListContract.Presenter {
    static WeakReference<ListContract.View> view;
    static int Offset = 0;
    GiphyApi Api;
    CompositeDisposable compositeDisposable;
    static AppDataBase db;
    static boolean firstPage=true;
    @Inject
    public ListPresenterImp(GiphyApi api, CompositeDisposable contanier, AppDataBase dataBase) {
        this.Api = api;
        this.compositeDisposable = contanier;
        db = dataBase;
    }

    @Override
    public void getListItems()  {
        compositeDisposable = new CompositeDisposable();
        try {
            compositeDisposable.add(
                    Api.getTrending(Constants.key, Offset, 20).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<ItemsModel, List<Item>>() {
                        @Override
                        public List<Item> apply(ItemsModel model) throws Exception {
                            Offset = (int) (model.getPagination().getOffset() + 20);
                            List<Item> item = new ArrayList<>();
                            for (int i = 0; i < model.getData().size(); i++) {
                                GifModel.User u = model.getData().get(i).getUser();
                                Item a = new Item();
                                if (u != null)
                                    a.setTitle(model.getData().get(i).getUser().getDisplay_name());
                                a.setUrl(model.getData().get(i).getImage().getFixed_heightObject().getUrl());
                                a.setOriginalUrl(model.getData().get(i).getImage().getOriginalImage().getUrl());
                                a.setOriginalUrl(a.getOriginalUrl().replace("giphy_s", "200w"));
                                item.add(a);
                            }
                            return item;
                        }
                    }).subscribe(getTrendingConsumer(), new io.reactivex.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if(Offset==0&&firstPage) {
                                getCachedListItem();
                                firstPage=false;
                            }
                                view.get().showSnackBar();
                        }
                    })
            );
        } catch (MyNetworkExcption myNetworkExcption) {
            myNetworkExcption.printStackTrace();
        }

    }

    @Override
    public void getCachedListItem() {
        view.get().ShowItems(db.itemdao().getItems());
    }


    @Override
    public void attach(BaseContract.View view) {
        this.view = new WeakReference<ListContract.View>((ListContract.View) view);
    }

    @Override
    public void detach() {
        compositeDisposable.clear();
        db=null;
        view.clear();
    }

    private static io.reactivex.functions.Consumer<List<Item>> getTrendingConsumer() {
        return new io.reactivex.functions.Consumer<List<Item>>() {

            @Override
            public void accept(List<Item> items) {
               if (Offset == 20) {
                    db.itemdao().deleteAll();
                    db.itemdao().insertItem(items);
                }
                firstPage=true;
                view.get().ShowItems(items);


            }
        };
    }



}
