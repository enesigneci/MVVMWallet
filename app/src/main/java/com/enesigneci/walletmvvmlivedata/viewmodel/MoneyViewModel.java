package com.enesigneci.walletmvvmlivedata.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.enesigneci.walletmvvmlivedata.db.MoneyRepository;
import com.enesigneci.walletmvvmlivedata.model.Money;

import java.util.List;

public class MoneyViewModel extends AndroidViewModel {
    private MoneyRepository repository;
    private LiveData<List<Money>> wallet;
    public MoneyViewModel(@NonNull Application application) {
        super(application);
        repository = new MoneyRepository(application);
        wallet = repository.getWallet();
    }
    public void insert(Money money) {
        repository.insert(money);
    }

    public void update(Money money) {
        repository.update(money);
    }

    public void delete(Money money) {
        repository.delete(money);
    }

    public void deleteAllMoney() {
        repository.deleteAllMoney();
    }

    public LiveData<List<Money>> getAllMoneys() {
        return wallet;
    }
}
