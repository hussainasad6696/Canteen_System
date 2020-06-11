package com.example.canteen_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class admin_frag_container extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_frag_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,new admin_dashboard()).commit();
    }
}