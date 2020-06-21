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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_student extends Fragment {
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Students_admin_part");
        auth = FirebaseAuth.getInstance();
       final EditText Fname = view.findViewById(R.id.fname);
        final EditText semester = view.findViewById(R.id.semester);
        final EditText mobile = view.findViewById(R.id.mobile_no_student);
        final EditText dob = view.findViewById(R.id.dob);
        final EditText address = view.findViewById(R.id.address_student);

        view.findViewById(R.id.create_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname =  Fname.getText().toString();
                String Semester = semester.getText().toString();
                final String Mobile = mobile.getText().toString();
                String DOB =  dob.getText().toString();
                String ADDRESS =  address.getText().toString();
                final students_detail studentsDetail = new students_detail(fullname,Semester,ADDRESS,Mobile,DOB);
                String key = databaseReference.push().getKey();
                if(key != null){
                    String mail = fullname+"@gmail.com";
                    auth.createUserWithEmailAndPassword(mail,Mobile).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getContext(),"User "+fullname+" account created",Toast.LENGTH_SHORT).show();
                            databaseReference.child(mobile.getText().toString()).setValue(studentsDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(),"Student Added",Toast.LENGTH_SHORT).show();

                                    Fname.setText("");
                                    semester.setText("");
                                    mobile.setText("");
                                    dob.setText("");
                                    address.setText("");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


        return view;
    }
}