package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListStudentEnrolledActivity extends AppCompatActivity {
    ListView CourseListView;
    ArrayList<String> CourseList;
    ArrayAdapter adapter;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrolled_detail);

        Intent intent = getIntent();
        String str2 = intent.getStringExtra("username");

        CourseList = new ArrayList<>();
        CourseListView = findViewById(R.id.EnrolledListView);
        dbHandler = new DBHandler(this);

        CourseList.clear();
        String res = dbHandler.getEnrolled(str2);
        if(res ==""){
            Toast.makeText(ListStudentEnrolledActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();


        }
        else{
            CourseList.add(res);
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CourseList);
        CourseListView.setAdapter(adapter);
    }
}