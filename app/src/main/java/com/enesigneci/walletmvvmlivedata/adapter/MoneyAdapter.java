package com.enesigneci.walletmvvmlivedata.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enesigneci.walletmvvmlivedata.R;
import com.enesigneci.walletmvvmlivedata.model.Money;

import java.util.ArrayList;
import java.util.List;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.MoneyViewHolder> {
    private List<Money> moneyList = new ArrayList<>();
    @NonNull
    @Override
    public MoneyAdapter.MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.money_item, viewGroup, false);
        return new MoneyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyAdapter.MoneyViewHolder moneyViewHolder, int position) {
        Money currentMoney = moneyList.get(position);
        moneyViewHolder.textViewTitle.setText(currentMoney.getTitle());
        if (currentMoney.getType()==0)
            moneyViewHolder.textViewDescription.setText("Alacak");
        else
            moneyViewHolder.textViewDescription.setText("Borc");
        moneyViewHolder.textViewPriority.setText(String.valueOf(currentMoney.getAmount()));
    }

    @Override
    public int getItemCount() {
        return moneyList.size();
    }
    public void setMoneyList(List<Money> moneyList){
        this.moneyList = moneyList;
        notifyDataSetChanged();
    }
    class MoneyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public MoneyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }

}