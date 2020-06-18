package com.example.canteen_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class canteen_worker_frag_container extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_worker_frag_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.canteen_worker_fragcontainer,new canteen_worker_dashboard()).commit();
    }
}