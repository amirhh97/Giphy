package com.example.aebrahimi.firstmvp.DataBase;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.aebrahimi.firstmvp.Model.Item;

import java.util.List;

/**
 * Created by SeyedAmirhoseinHoseini on 8/21/18.
 */
@android.arch.persistence.room.Dao
public interface Dao {
    @Insert
    void insertItem(List<Item> item);

    @Query("select * from itemstable")
    List<Item> getItems();

    @Query("delete from itemstable")
    void deleteAll();

}
