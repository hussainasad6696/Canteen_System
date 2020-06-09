package com.example.canteen_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class admin_login extends AppCompatActivity {

    ExpandableRelativeLayout signIn_Content;
    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        signIn = findViewById(R.id.signIn);
    }
    public void showmyinformation(View view)
    {
        signIn_Content = findViewById(R.id.signIn_content);
        signIn_Content.toggle();
        signIn.setVisibility(View.GONE);
    }
}