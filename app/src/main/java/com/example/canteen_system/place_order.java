package com.example.canteen_system;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class place_order extends Fragment {
    Spinner spinner;
    ListView listView;
     ArrayList<String> list,_list;
    DatabaseReference reference;
    Object value;
    int vvv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_order, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Items_list");
        listView = view.findViewById(R.id.ordered_items_listView);
        list = new ArrayList<>();

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String items = dataSnapshot.child("food_name").getValue(String.class);
                list.add(items);
         //       adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
           //     adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
             //   adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        spinner = view.findViewById(R.id.menu_list_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),  android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        _list = new ArrayList<>();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = parent.getItemAtPosition(position).toString();
                vvv = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),""+vvv,Toast.LENGTH_SHORT).show();
             //       _list.add(vvv);
           //         ArrayAdapter<String> adapter_listView = new ArrayAdapter(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,_list);
           //     listView.setAdapter(adapter_listView);


            }
        });

        return view;
    }
}