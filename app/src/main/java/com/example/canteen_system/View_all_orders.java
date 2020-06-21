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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class View_all_orders extends Fragment {

    ListView listView;
    DatabaseReference reference;
    FirebaseAuth auth;
    EditText change;
    ArrayList<String> list;
    ArrayList<String> prices;
    Spinner spinner;
    Object value;
    EditText management;
    float total_price = 0;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all_orders, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Orders");
        listView = view.findViewById(R.id.list_view_Orders);
        prices = new ArrayList<>();
        list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    String values = dataSnapshot1.getValue().toString();
                    list.add(values);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        auth = FirebaseAuth.getInstance();
        spinner = view.findViewById(R.id.order_to_change);
        change = view.findViewById(R.id.change_order);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.orderList,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        management = view.findViewById(R.id.order_for_change);
        view.findViewById(R.id.Change_selected_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manag = management.getText().toString();
                String changed = change.getText().toString();
                reference.child(manag).child(String.valueOf(value)).setValue(changed).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),""+value+" Updated",Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        for (int i = 0; i < prices.size(); i++) {
            total_price = total_price + Float.parseFloat(prices.get(i));
        }
        TextView totalPrice = view.findViewById(R.id.total_price);
        totalPrice.setText(String.valueOf(total_price));

        searchView = view.findViewById(R.id.searchView_orders);
        view.findViewById(R.id.delete_selected_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_search = searchView.getQuery().toString();
                deleteData(query_search);
            }
        });
        return view;
    }
    private void deleteData(String query_search) {
        Query deleteQuery = reference.orderByChild("OrderName").equalTo(query_search);
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot deleteSnap : dataSnapshot.getChildren())
                {
                    deleteSnap.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toasty.success(getContext(), "Deleted", Toast.LENGTH_SHORT, true).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toasty.error(getContext(), "Failed To delete "+e, Toast.LENGTH_SHORT, true).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),""+databaseError,Toast.LENGTH_SHORT).show();
            }
        });
    }
}