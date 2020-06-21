package com.example.canteen_system;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class place_order extends Fragment {
    Spinner spinner;
    ListView listView;
     ArrayList<String> list;
     ArrayList<String> _list;
    DatabaseReference reference,ref;
    order_list_detail orderListDetail;
    String price,items;
    EditText item_ordered;
    private static final String TAG = "place_order";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_order, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Items_list");
        ref = FirebaseDatabase.getInstance().getReference().child("Orders");
        listView = view.findViewById(R.id.ordered_items_listView);
        list = new ArrayList<>();
        _list = new ArrayList<String>();

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                 items = dataSnapshot.child("food_name").getValue(String.class);
                 price = dataSnapshot.child("coast").getValue(String.class);
                list.add(items);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
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
       spinner = view.findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),  android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        item_ordered = view.findViewById(R.id.items_name_order);

        view.findViewById(R.id.add_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String itemsss = item_ordered.getText().toString();


                    _list.add(itemsss);

                    //    orderListDetail = new order_list_detail(item_value[1],"1");
                ArrayAdapter adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,_list);
                listView.setAdapter(adapter1);

            }
        });
        final EditText name = view.findViewById(R.id.your_name);

view.findViewById(R.id.order_menu).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ref.child(name.getText().toString()).setValue(_list).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                name.setText("");
                item_ordered.setText("");
                Toast.makeText(getContext(),"Order Made",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
            }
        });
    }
});

        return view;
    }
}