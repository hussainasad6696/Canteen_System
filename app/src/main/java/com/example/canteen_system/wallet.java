package com.example.canteen_system;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class wallet extends Fragment {

    Button withdraw,withdraw_main,cash,cash_main;
    DatabaseReference reference;
    List<String> list;
    Object value;
    EditText cash_amount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("Canteens");

        list = new ArrayList<>();
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                canteen_detail canteenDetail = dataSnapshot.getValue(canteen_detail.class);
                list.add(canteenDetail.getMobile());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Spinner  spinner = view.findViewById(R.id.person);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
      /*  withdraw = view.findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                withdraw.setVisibility(View.INVISIBLE);
                withdraw_main.setVisibility(View.VISIBLE);
            }
        });
        cash = view.findViewById(R.id.add_cash);
        cash_main = view.findViewById(R.id.add_cash_main);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                cash.setVisibility(View.INVISIBLE);
                cash_main.setVisibility(View.VISIBLE);
            }
        });
        withdraw_main = view.findViewById(R.id.withdraw_main);
        withdraw_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cash_amount = view.findViewById(R.id.cash_amount);
        view.findViewById(R.id.add_cash_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(String.valueOf(value)).child("Balance").setValue(cash_amount.getText().toString());
            }
        }); */
        return view;
    }
}