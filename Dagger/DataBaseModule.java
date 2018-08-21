package com.example.aebrahimi.firstmvp.Dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.aebrahimi.firstmvp.DataBase.AppDataBase;
import com.example.aebrahimi.firstmvp.DataBase.Dao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SeyedAmirhoseinHoseini on 8/21/18.
 */

@Module
public class DataBaseModule {
    AppDataBase db;

    @Singleton
    @Provides
    AppDataBase provideDataBase(Application application) {
        return db = Room.databaseBuilder(application, AppDataBase.class, "ItemsDataBase").allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    Dao provideDao() {
        return db.itemdao();
    }


}
