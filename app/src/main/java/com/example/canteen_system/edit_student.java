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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class edit_student extends Fragment {

    ListView listView;
    DatabaseReference reference;
    FirebaseAuth auth;
    EditText change;
    ArrayList<students_detail> list;
    Spinner spinner;
    Object value;
    EditText management;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Students_admin_part");
        listView = view.findViewById(R.id.list_view_update_student);
        list = new ArrayList<students_detail>();
        final admin_student_adapter adapter = new admin_student_adapter(getContext(),R.layout.student_detail_container,list);
        listView.setAdapter(adapter);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                students_detail studentsDetail = dataSnapshot.getValue(students_detail.class);
                list.add(studentsDetail);
                adapter.notifyDataSetChanged();
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
        auth = FirebaseAuth.getInstance();
        spinner = view.findViewById(R.id.student_change);
        change = view.findViewById(R.id.change_student);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.update_options_students,android.R.layout.simple_spinner_item);
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
        management = view.findViewById(R.id.number_to_updatestu);
        view.findViewById(R.id.update_student).setOnClickListener(new View.OnClickListener() {
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

        return view;
    }
}