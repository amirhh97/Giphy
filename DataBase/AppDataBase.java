package com.example.aebrahimi.firstmvp.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.aebrahimi.firstmvp.Model.Item;

import javax.inject.Inject;

/**
 * Created by SeyedAmirhoseinHoseini on 8/21/18.
 */

@Database(entities = {Item.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract Dao itemdao();


}
