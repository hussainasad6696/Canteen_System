package com.example.canteen_system;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;

public class Order_adapter extends ArrayAdapter<order_list_detail> {
    private Context mcontext;
    private int mresource;
    public Order_adapter(@NonNull Context context, int resource, @NonNull ArrayList<order_list_detail> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String foodname = Objects.requireNonNull(getItem(position)).getOrder_name();
        String price = Objects.requireNonNull(getItem(position)).getOrder_price();


        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource,parent,false);
        TextView Foodname,Price;
        Foodname = convertView.findViewById(R.id.product_name_container);
        Price = convertView.findViewById(R.id.product_price_container);
        Foodname.setText(foodname);
        Price.setText(price);
        return convertView;
    }
}


