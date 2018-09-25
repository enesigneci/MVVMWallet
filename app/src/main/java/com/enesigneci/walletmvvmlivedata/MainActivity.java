package com.enesigneci.walletmvvmlivedata;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.enesigneci.walletmvvmlivedata.adapter.MoneyAdapter;
import com.enesigneci.walletmvvmlivedata.model.Money;
import com.enesigneci.walletmvvmlivedata.viewmodel.MoneyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MoneyViewModel moneyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.wallet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MoneyAdapter adapter = new MoneyAdapter();
        recyclerView.setAdapter(adapter);
        moneyViewModel = ViewModelProviders.of(this).get(MoneyViewModel.class);
        moneyViewModel.getAllMoneys().observe(this, new Observer<List<Money>>() {
            @Override
            public void onChanged(@Nullable List<Money> moneys) {
                //update RecyclerView
                adapter.setMoneyList(moneys);
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
        Button addMoneyButton = findViewById(R.id.addMoney);
        addMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_money_dialog_layout);

                final EditText who = (EditText) dialog.findViewById(R.id.who);
                final EditText amount = (EditText) dialog.findViewById(R.id.amount);
                Button dialogButton = (Button) dialog.findViewById(R.id.addMoney);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.type);
                        int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                        moneyViewModel.insert(new Money(who.getText().toString(),Float.valueOf(amount.getText().toString()),index));
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        Button deleteMoney = findViewById(R.id.deleteMoney);
        deleteMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyViewModel.deleteAllMoney();
            }
        });

    }
}
