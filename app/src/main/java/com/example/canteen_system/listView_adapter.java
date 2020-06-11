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

public class listView_adapter extends ArrayAdapter<canteen_detail> {
    private Context mcontext;
    private int mresource;
    public listView_adapter(@NonNull Context context, int resource, @NonNull ArrayList<canteen_detail> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String management_name = Objects.requireNonNull(getItem(position)).getManagement_detail();
        String handler = Objects.requireNonNull(getItem(position)).getHandler();
        String address = getItem(position).getAddress();
        long mobile = getItem(position).getMobile();
        long workers = getItem(position).getNo_of_worker();

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource,parent,false);
        TextView Management,Name,Number,Worker,Address;
        Management = convertView.findViewById(R.id.management);
        Name = convertView.findViewById(R.id.name);
        Number = convertView.findViewById(R.id.number);
        Worker = convertView.findViewById(R.id.worker);
        Address = convertView.findViewById(R.id.addres);
        Management.setText(management_name);
        Name.setText(handler);
        Number.setText(String.valueOf(mobile));
        Worker.setText(String.valueOf(workers));
        Address.setText(address);
        return convertView;
    }
}
