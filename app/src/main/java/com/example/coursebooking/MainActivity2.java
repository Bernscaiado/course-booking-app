package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String str1 = intent.getStringExtra("firstname");
        String str2 = intent.getStringExtra("username");
        String str3 = intent.getStringExtra("role");

        Button btn_users = findViewById(R.id.btn_users);
        Button btn_courses = findViewById(R.id.btn_courses);



        TextView receiver_msg =  findViewById(R.id.received_value_id);
        receiver_msg.setText("admin" + "/" + "admin" + "! You are logged in as '" + "Admin" + "'");

        btn_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), ManageCourses.class);
                v.getContext().startActivity(intent);
            }
        });

        btn_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), ManageUsers.class);
                v.getContext().startActivity(intent);
            }
        });


    }
}