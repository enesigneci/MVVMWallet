package com.enesigneci.walletmvvmlivedata.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.enesigneci.walletmvvmlivedata.model.Money;

@Database(entities = {Money.class},version = 1)
public abstract class MoneyDatabase extends RoomDatabase {
    private static MoneyDatabase instance;
    public abstract MoneyDao moneyDao();
    public static synchronized MoneyDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context,MoneyDatabase.class,"money_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
