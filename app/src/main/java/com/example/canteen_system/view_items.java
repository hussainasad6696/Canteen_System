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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class view_items extends Fragment {

    ArrayList data_key;
    DatabaseReference ref ;
    SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_items, container, false);
        final ListView listView = view.findViewById(R.id.list_view_items);
        data_key = new ArrayList<String>();
        final ArrayList<item_detail> list_items = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Items_list");
        final item_adapter adapter = new item_adapter(Objects.requireNonNull(getActivity()),R.layout.item_container,list_items);
        listView.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final item_detail itemDetail = dataSnapshot.getValue(item_detail.class);
                list_items.add(itemDetail);
                adapter.notifyDataSetChanged();
                data_key.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        searchView = view.findViewById(R.id.search_items);
        view.findViewById(R.id.delete_items_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_search = searchView.getQuery().toString();
                deleteData(query_search);
            }
        });

        return view;
    }
    private void deleteData(String query_search) {
        Query deleteQuery = ref.orderByChild("food_name").equalTo(query_search);
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