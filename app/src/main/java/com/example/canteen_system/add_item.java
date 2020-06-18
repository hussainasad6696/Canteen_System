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

public class add_item extends Fragment {
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Items_list");
        final EditText food_name = view.findViewById(R.id.fodd_name);
        final EditText food_desc = view.findViewById(R.id.food_description);
        final EditText coast = view.findViewById(R.id.food_coast);
        final EditText time_to_get_ready = view.findViewById(R.id.food_time_to_get_ready);
        final EditText food_item_numbers = view.findViewById(R.id.food_item_number);

        view.findViewById(R.id.add_items_toDB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Food_name =  food_name.getText().toString();
                String dec = food_desc.getText().toString();
                String Co = coast.getText().toString();
                String TTGR =  time_to_get_ready.getText().toString();
                String FIN =  food_item_numbers.getText().toString();
                item_detail itemDetail = new item_detail(Food_name,dec,Co,FIN,TTGR);
                String key = databaseReference.push().getKey();
                if(key != null){
                    databaseReference.child(food_name.getText().toString()).setValue(itemDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(),"Student Added",Toast.LENGTH_SHORT).show();
                            food_name.setText("");
                            food_desc.setText("");
                            coast.setText("");
                            time_to_get_ready.setText("");
                            food_item_numbers.setText("");
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