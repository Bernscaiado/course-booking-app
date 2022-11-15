package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button list = findViewById(R.id.listCourses);
        Button assign = findViewById(R.id.assign);


        Intent intent = getIntent();
        String str1 = intent.getStringExtra("firstname");
        String str2 = intent.getStringExtra("username");
        String str3 = intent.getStringExtra("role");


        TextView receiver_msg =  findViewById(R.id.received_value_id2);
        receiver_msg.setText(str1 + "/" + str2 + "! You are logged in as '" + str3 + "'");

        list.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListCoursesActivity.class);
                v.getContext().startActivity(intent);
            }
        }));

        assign.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CourseAssignActivity.class);

                intent.putExtra("firstname", str1);
                intent.putExtra("username", str2);
                intent.putExtra("role", str3);

                v.getContext().startActivity(intent);
            }
        }));
    }



}