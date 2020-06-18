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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class wallet extends Fragment {

    Button withdraw,withdraw_main,cash,cash_main;
    DatabaseReference reference;
    ArrayList<String> list;
    Object value;
    EditText cash_amount;
    Spinner spinner_person;
    TextView selected_person;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);


        reference = FirebaseDatabase.getInstance().getReference().child("Canteens");
        spinner_person = view.findViewById(R.id.person);
        list = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_person.setAdapter(arrayAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                        String mobile_number = snapshot.child("mobile").getValue(String.class);
                        list.add(mobile_number);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        selected_person = view.findViewById(R.id.selected);
        spinner_person.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),""+position,Toast.LENGTH_SHORT).show();
                selected_person.setText(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), ""+parent, Toast.LENGTH_SHORT).show();
            }
        });

        withdraw = view.findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_person.setVisibility(View.VISIBLE);
                withdraw.setVisibility(View.INVISIBLE);
                withdraw_main.setVisibility(View.VISIBLE);
            }
        });
        cash = view.findViewById(R.id.add_cash);
        cash_main = view.findViewById(R.id.add_cash_main);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_person.setVisibility(View.VISIBLE);
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
        });
        return view;
    }
}