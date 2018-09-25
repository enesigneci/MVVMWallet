package com.enesigneci.walletmvvmlivedata.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.enesigneci.walletmvvmlivedata.model.Money;

import java.util.List;

public class MoneyRepository {
    private MoneyDao moneyDao;
    private LiveData<List<Money>> wallet;

    public MoneyRepository(Application application) {
        MoneyDatabase moneyDatabase = MoneyDatabase.getInstance(application);
        moneyDao = moneyDatabase.moneyDao();
        wallet = moneyDao.getAllMoney();
    }
    public void insert(Money money){
        new InsertMoneyAsyncTask(moneyDao).execute(money);
    }
    public void update(Money money){
        new UpdateMoneyAsyncTask(moneyDao).execute(money);
    }
    public void delete(Money money){
        new DeleteMoneyAsyncTask(moneyDao).execute(money);
    }
    public void deleteAllMoney(){
        new DeleteAllMoneyAsyncTask(moneyDao).execute();
    }

    public LiveData<List<Money>> getWallet() {
        return wallet;
    }

    private static class InsertMoneyAsyncTask extends AsyncTask<Money,Void,Void>{
        MoneyDao moneyDao;

        public InsertMoneyAsyncTask(MoneyDao moneyDao) {
            this.moneyDao = moneyDao;
        }

        @Override
        protected Void doInBackground(Money... monies) {
            moneyDao.insert(monies[0]);
            return null;
        }
    }
    private static class UpdateMoneyAsyncTask extends AsyncTask<Money,Void,Void>{
        MoneyDao moneyDao;

        public UpdateMoneyAsyncTask(MoneyDao moneyDao) {
            this.moneyDao = moneyDao;
        }

        @Override
        protected Void doInBackground(Money... monies) {
            moneyDao.update(monies[0]);
            return null;
        }
    }
    private static class DeleteMoneyAsyncTask extends AsyncTask<Money,Void,Void>{
        MoneyDao moneyDao;

        public DeleteMoneyAsyncTask(MoneyDao moneyDao) {
            this.moneyDao = moneyDao;
        }

        @Override
        protected Void doInBackground(Money... monies) {
            moneyDao.delete(monies[0]);
            return null;
        }
    }
    private static class DeleteAllMoneyAsyncTask extends AsyncTask<Void,Void,Void>{
        MoneyDao moneyDao;

        public DeleteAllMoneyAsyncTask(MoneyDao moneyDao) {
            this.moneyDao = moneyDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            moneyDao.deleteAllMoney();
            return null;
        }
    }
}
