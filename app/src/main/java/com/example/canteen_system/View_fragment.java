package com.example.canteen_system;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class View_fragment extends Fragment {

     ArrayList data_key;
    DatabaseReference ref ,reference;
    SearchView searchView;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_fragment, container, false);
        final ListView listView = view.findViewById(R.id.list_view);
        data_key = new ArrayList<String>();
        final ArrayList<canteen_detail> list = new ArrayList<>();
         ref = FirebaseDatabase.getInstance().getReference().child("Canteens");
        final listView_adapter adapter = new listView_adapter(Objects.requireNonNull(getActivity()),R.layout.canteen_detail_container,list);
        listView.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final canteen_detail canteenDetail = dataSnapshot.getValue(canteen_detail.class);
                list.add(canteenDetail);
                adapter.notifyDataSetChanged();
                data_key.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
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

     /*  ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
               {
                  canteen_detail  canteenDetail = dataSnapshot1.getValue(canteen_detail.class);
                  list.add(canteenDetail);
               }
               adapter.notifyDataSetChanged();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
                adapter.notifyDataSetChanged();
           }
       });*/

        searchView = view.findViewById(R.id.search);
        view.findViewById(R.id.search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_search = searchView.getQuery().toString();
                deleteData(query_search);
            }
        });
        return view;
    }

    private void deleteData(String query_search) {
        Query deleteQuery = ref.orderByChild("management_detail").equalTo(query_search);
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot deleteSnap : dataSnapshot.getChildren())
                {
                    deleteSnap.getRef().removeValue();
                }
                Toast.makeText(getContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),""+databaseError,Toast.LENGTH_SHORT).show();
            }
        });
    }
}