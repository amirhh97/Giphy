package com.example.aebrahimi.firstmvp.ListContract;

import com.example.aebrahimi.firstmvp.BaseContract.BaseContract;
import com.example.aebrahimi.firstmvp.Model.Item;
import com.example.aebrahimi.firstmvp.Network.MyNetworkExcption;

import java.util.List;

/**
 * Created by aebrahimi on 8/13/2018 AD.
 */

public interface ListContract {
    interface Presenter extends BaseContract.Presenter
    {
         void getListItems() throws MyNetworkExcption;
         void getCachedListItem();
    }
    interface View extends BaseContract.View
    {
        void ShowItems(List<Item> items);

        @Override
        void hideProgress();

        @Override
        void showProgress();


    }


}
