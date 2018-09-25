package com.enesigneci.walletmvvmlivedata.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.enesigneci.walletmvvmlivedata.model.Money;

import java.util.List;

@Dao
public interface MoneyDao {
    @Insert
    void insert(Money money);
    @Update
    void update(Money money);
    @Delete
    void delete(Money money);
    @Query("DELETE FROM money")
    void deleteAllMoney();
    @Query("SELECT * FROM money ORDER by id ASC")
    LiveData<List<Money>> getAllMoney();
}
