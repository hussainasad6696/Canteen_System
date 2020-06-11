package com.example.canteen_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {

    ExpandableRelativeLayout signIn_Content;
    Button signIn,logIn;
    EditText username,password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        firebaseAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.signIn);
        logIn = findViewById(R.id.LogIn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString();
                String pass = password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(user_name,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(),"Welcome "+username.getText().toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(admin_login.this, admin_frag_container.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public void showmyinformation(View view)
    {
        signIn_Content = findViewById(R.id.signIn_content);
        signIn_Content.toggle();
        signIn.setVisibility(View.GONE);
    }
}