package com.example.canteen_system;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_canteen extends Fragment {

    DatabaseReference databaseReference;
    EditText management,handler,mobile,no_of_workers,address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_canteen, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Canteens");
        management = view.findViewById(R.id.management_name);
        handler = view.findViewById(R.id.handler);
        mobile = view.findViewById(R.id.mobile_no);
        no_of_workers = view.findViewById(R.id.no_of_workers);
        address = view.findViewById(R.id.address);

        view.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               canteen_detail canteendetail = new canteen_detail(management.getText().toString(),handler.getText().toString(),address.getText().toString(),Long.parseLong(mobile.getText().toString()),Long.parseLong(no_of_workers.getText().toString()));
               String key = databaseReference.push().getKey();
               if(key != null){
               databaseReference.child(management.getText().toString()).setValue(canteendetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       Toast.makeText(getContext(),"Canteen Added",Toast.LENGTH_SHORT).show();
                       management.setText("");
                       handler.setText("");
                       mobile.setText("");
                       no_of_workers.setText("");
                       address.setText("");
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
                   }
               });}
           }
       });

        return view;
    }
}