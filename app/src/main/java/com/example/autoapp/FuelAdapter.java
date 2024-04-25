package com.example.autoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FuelAdapter extends BaseAdapter {

    Context context;
    String listTotalPrice[];
    String listPPL[];
    LayoutInflater inflater;

    public FuelAdapter(Context ctx, String[] listTotalPrice, String[] listPPL) {
        this.context = ctx;
        this.listTotalPrice = listTotalPrice;
        this.listPPL = listPPL;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listTotalPrice.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_fuel_element, null);
        TextView TotalPriceView = (TextView) convertView.findViewById(R.id.total_price);
        TotalPriceView.setText(listTotalPrice[position]);
        TextView PPLView = (TextView) convertView.findViewById(R.id.price_per_liter);
        PPLView.setText(listPPL[position]);
        return convertView;
    }
}
