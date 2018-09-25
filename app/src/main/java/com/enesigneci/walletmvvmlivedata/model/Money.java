package com.enesigneci.walletmvvmlivedata.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "money")
public class Money {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private float amount;
    private int type;

    public Money(String title, float amount, int type) {
        this.title = title;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public float getAmount() {
        return amount;
    }

    public int getType() {
        return type;
    }
}
