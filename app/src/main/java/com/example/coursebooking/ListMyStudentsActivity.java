package com.example.coursebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListMyStudentsActivity extends AppCompatActivity {
    ListView StudentListView;
    ArrayList<String> CourseList;
    ArrayAdapter adapter;
    CourseDatabase dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_students);


        Intent intent = getIntent();
        String str1 = intent.getStringExtra("firstname");
        String str2 = intent.getStringExtra("username");
        String str3 = intent.getStringExtra("role");


        CourseList = new ArrayList<>();
        StudentListView = findViewById(R.id.StudentListView);
        dbHandler = new CourseDatabase(this);

        CourseList.clear();

        // Retrieve list of courses taught by professor
        ArrayList<String> myCoursesList = dbHandler.getInstructorCourses(str2);
        int size = myCoursesList.size();

        if (size == 0 || myCoursesList == null) {
            Toast.makeText(ListMyStudentsActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < size; i+=2) {
                String name = myCoursesList.get(i);
                String code = myCoursesList.get(i + 1);
                CourseList.add(name + ": " + code);
                String students = dbHandler.getStudents(name,code);
                CourseList.add(students);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CourseList);
        StudentListView.setAdapter(adapter);
    }
}