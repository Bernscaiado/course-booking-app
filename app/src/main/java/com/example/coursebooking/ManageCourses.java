package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);
        //Button
        Button delete = findViewById(R.id.deleteCourse);
        Button edit = findViewById(R.id.editCourse);
        Button create = findViewById(R.id.createCourse);
        EditText code = findViewById(R.id.Name);
        EditText name= findViewById(R.id.Code);
        EditText newCode = findViewById(R.id.NameNew);
        EditText newName= findViewById(R.id.CodeNew);

        Button btn_back1 = findViewById(R.id.btn_back1);

        //handler
        CourseDatabase db = new CourseDatabase(ManageCourses.this);

        btn_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                v.getContext().startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course;

                try {
                    course= new Course(name.getText().toString(),Integer.parseInt(code.getText().toString()));
                    Toast.makeText(ManageCourses.this,course.toString(),Toast.LENGTH_SHORT).show();
                    boolean success = db.addOne(course);
                    Toast.makeText(ManageCourses.this ,"SUCCESS",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e ){
                    Toast.makeText(ManageCourses.this ,"ERROR INPUT",Toast.LENGTH_SHORT).show();
                }


            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course;
                try {
                    course= new Course(name.getText().toString(),Integer.parseInt(code.getText().toString()));
                    Toast.makeText(ManageCourses.this,course.toString(),Toast.LENGTH_SHORT).show();
                    boolean success = db.deleteCourse(name.getText().toString(),code.getText().toString());
                    Toast.makeText(ManageCourses.this ,"SUCCESS",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e ){
                    Toast.makeText(ManageCourses.this ,"ERROR INPUT",Toast.LENGTH_SHORT).show();
                }
            }
        });
        edit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.editCourse(newName.getText().toString(),newCode.getText().toString(),name.getText().toString(),code.getText().toString());
                Toast.makeText(ManageCourses.this,"Course Edited",Toast.LENGTH_SHORT).show();



            }
        }));

    }
}