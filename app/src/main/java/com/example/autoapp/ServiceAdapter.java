package com.example.autoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ServiceAdapter extends BaseAdapter {

    Context context;
    String listFilter[];
    String listOil[];
    String listKM[];
    String listPrice[];
    LayoutInflater inflater;
    public ServiceAdapter(Context ctx, String[] listFilter, String[] listOil, String[] listKM, String[] listPrice) {
        this.context = ctx;
        this.listFilter = listFilter;
        this.listOil =  listOil;
        this.listKM =  listKM;
        this.listPrice =  listPrice;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listFilter.length;
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
        convertView = inflater.inflate(R.layout.activity_service_element, null);
        TextView filterstxtView = (TextView) convertView.findViewById(R.id.filters);
        filterstxtView.setText(listFilter[position]);
        TextView oiltxtView = (TextView) convertView.findViewById(R.id.oil);
        oiltxtView.setText(listOil[position]);
        TextView kmtxtView = (TextView) convertView.findViewById(R.id.km);
        kmtxtView.setText(listKM[position]);
        TextView pricetxtView = (TextView) convertView.findViewById(R.id.price);
        pricetxtView.setText(listPrice[position]);
        return convertView;
    }
}
