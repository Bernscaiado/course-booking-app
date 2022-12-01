package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CourseEnrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_enroll);

        // buttons
        Button assign = findViewById(R.id.enroll);
        Button unassign = findViewById(R.id.unenroll);
//        Button detail = findViewById(R.id.detail);

        EditText name = findViewById(R.id.name);
        EditText code = findViewById(R.id.code);

        CourseDatabase db = new CourseDatabase(this);
        DBHandler dbUser = new DBHandler(this);

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
                    Toast.makeText(CourseEnrollActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }
                else if(db.checkAvalibility(coursename,coursecode)== false)
                {
                    Toast.makeText(CourseEnrollActivity.this, "Course does not exist", Toast.LENGTH_SHORT).show();

                }
//                else if(db.hasInstructor(coursename,coursecode)== true)
//                {
//                    String res = db.getInstructor(coursename,coursecode);
//                    Toast.makeText(CourseEnrollActivity.this, "Course already assigned by: "+res, Toast.LENGTH_SHORT).show();
//
//                }
                else{
                    Course course= new Course(coursename,coursecode);
                    boolean success = db.addStudent(coursename, coursecode, str2);
                    dbUser.addEnrolled(coursename,coursecode,str2);

                    if (success){
                        Toast.makeText(CourseEnrollActivity.this ,"SUCCESS",Toast.LENGTH_SHORT).show();}
                }}
        });

        unassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coursename = name.getText().toString();
                String coursecode = code.getText().toString();

                db.removeStudent(coursename,coursecode, str2);
                dbUser.removeEnrolled(coursename,coursecode,str2);
                Toast.makeText(CourseEnrollActivity.this ,"SUCCESS",Toast.LENGTH_SHORT).show();
            }
        });

//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(),ListStudentEnrolledActivity.class);
//
//                intent.putExtra("username", str2);
//
//
//                view.getContext().startActivity(intent);
//
//            }
//        });



    }
}