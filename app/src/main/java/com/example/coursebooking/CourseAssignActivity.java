package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CourseAssignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_assign);




        // buttons
        Button assign = findViewById(R.id.assign);
        Button unassign = findViewById(R.id.unassign);
        Button detail = findViewById(R.id.detail);

        EditText name = findViewById(R.id.name);
        EditText code = findViewById(R.id.code);

        CourseDatabase db = new CourseDatabase(this);

        Intent intent = getIntent();
        String str1 = intent.getStringExtra("firstname");
        String str2 = intent.getStringExtra("username");
        String str3 = intent.getStringExtra("role");


        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String coursename = name.getText().toString();
                String coursecode = code.getText().toString();

                if (coursename.equals("") || coursecode.equals("")) {
                    Toast.makeText(CourseAssignActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }
                else if(db.checkAvalibility(coursename,coursecode)== false)
                {
                    Toast.makeText(CourseAssignActivity.this, "Course does not exist", Toast.LENGTH_SHORT).show();

                }
                else if(db.hasInstructor(coursename,coursecode)== true)
                {
                    Toast.makeText(CourseAssignActivity.this, "Course already has instructor", Toast.LENGTH_SHORT).show();

                }
                else{
                    Course course= new Course(coursename,coursecode);
                    boolean success = db.setInstructor(coursename, coursecode, str1);

                    if (success){
                        Toast.makeText(CourseAssignActivity.this ,"SUCCESS",Toast.LENGTH_SHORT).show();}

                }}
        });

        unassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coursename = name.getText().toString();
                String coursecode = code.getText().toString();
                boolean success = db.nullInstructor(coursename, coursecode, str1);

                if (success){
                    Toast.makeText(CourseAssignActivity.this ,"SUCCESS",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}