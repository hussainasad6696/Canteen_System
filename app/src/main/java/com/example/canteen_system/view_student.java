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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class view_student extends Fragment {

    ArrayList data_key;
    DatabaseReference ref ;
    SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_student, container, false);
        final ListView listView = view.findViewById(R.id.list_view_student);
        data_key = new ArrayList<String>();
        final ArrayList<students_detail> list_students = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Students_admin_part");
        final admin_student_adapter adapter = new admin_student_adapter(Objects.requireNonNull(getActivity()),R.layout.student_detail_container,list_students);
        listView.setAdapter(adapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final students_detail studentsDetail = dataSnapshot.getValue(students_detail.class);
                list_students.add(studentsDetail);
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


        searchView = view.findViewById(R.id.search_student);
        view.findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_search = searchView.getQuery().toString();
                deleteData(query_search);
            }
        });

        return view;
    }
    private void deleteData(String query_search) {
        Query deleteQuery = ref.orderByChild("fName").equalTo(query_search);
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