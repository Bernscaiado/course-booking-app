package com.example.coursebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons
        Button loginAcc = findViewById(R.id.login);
        Button createAcc = findViewById(R.id.createAcc);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        // button listeners
        loginAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            }
        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            }
        });


    }
}