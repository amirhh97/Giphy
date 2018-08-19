package com.example.aebrahimi.firstmvp.ShowContract;

import com.example.aebrahimi.firstmvp.BaseContract.BaseContract;
import com.example.aebrahimi.firstmvp.Model.Item;

/**
 * Created by aebrahimi on 8/14/2018 AD.
 */

public interface ShowContract {
    interface Presenter extends BaseContract.Presenter
    {
    void getRandomItems();
    }
    interface View extends BaseContract.View
    {
        void ShowRandomItem(Item item);}
    }

