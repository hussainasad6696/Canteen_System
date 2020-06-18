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

public class item_adapter extends ArrayAdapter<item_detail> {
    private Context mcontext;
    private int mresource;
    public item_adapter(@NonNull Context context, int resource, @NonNull ArrayList<item_detail> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String foodname = Objects.requireNonNull(getItem(position)).getFood_name();
        String Description = Objects.requireNonNull(getItem(position)).getItem_descriptions();
        String COAST = Objects.requireNonNull(getItem(position)).getCoast();
        String TTGR = Objects.requireNonNull(getItem(position)).getTime_to_get_ready();
        String NOI = Objects.requireNonNull(getItem(position)).getNumber_of_items();

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource,parent,false);
        TextView Foodname,desc,Coast,ttgr,noi;
        Foodname = convertView.findViewById(R.id.item_name_container);
        desc = convertView.findViewById(R.id.item_description_container);
        Coast = convertView.findViewById(R.id.item_coast_container);
        ttgr = convertView.findViewById(R.id.item_time_to_get_ready_container);
        noi = convertView.findViewById(R.id.item_number_container);
        Foodname.setText(foodname);
        desc.setText(Description);
        Coast.setText(COAST);
        ttgr.setText(TTGR);
        noi.setText(NOI);
        return convertView;
    }
}

