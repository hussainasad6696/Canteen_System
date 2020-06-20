package com.example.canteen_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class student_fragcontainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fragcontainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.student_fragmentcontainer,new student_dashboard()).commit();
    }
}