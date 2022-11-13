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
        Button list = findViewById(R.id.listCourses);
        EditText code = findViewById(R.id.Code);
        EditText name= findViewById(R.id.Name);
        EditText newCode = findViewById(R.id.CodeNew);
        EditText newName= findViewById(R.id.NameNew);

        Button btn_back1 = findViewById(R.id.btn_back1);

        //handler
        CourseDatabase db = new CourseDatabase(this);

        btn_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                v.getContext().startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String coursename = name.getText().toString();
                String coursecode = code.getText().toString();

                if (coursename.equals("") || coursecode.equals("")) {
                    Toast.makeText(ManageCourses.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }
                else if(db.checkAvalibility(coursename,coursecode)== true)
                {
                    Toast.makeText(ManageCourses.this, "Same Course Created", Toast.LENGTH_SHORT).show();

                }
                else{
                    Course course= new Course(coursename,coursecode);
                    boolean success = db.addOne(course);

                    if (success){
                        Toast.makeText(ManageCourses.this ,"SUCCESS",Toast.LENGTH_SHORT).show();}

            }}
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coursename = name.getText().toString();
                String coursecode = code.getText().toString();

                if (coursename.equals("") || coursecode.equals("")) {
                    Toast.makeText(ManageCourses.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }
                else if(db.checkAvalibility(coursename,coursecode)== false)
                {
                    Toast.makeText(ManageCourses.this, "No such course", Toast.LENGTH_SHORT).show();

                }

                else{



                    boolean success = db.deleteCourse(coursename,coursecode);
                    if (success){
                    Toast.makeText(ManageCourses.this ,"DeleteSUCCESS",Toast.LENGTH_SHORT).show();}


            }}
        });
        edit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coursename = name.getText().toString();
                String coursecode = code.getText().toString();
                String newname = newName.getText().toString();
                String newcode = newCode.getText().toString();
                if (coursename.equals("") || coursecode.equals("")) {
                    Toast.makeText(ManageCourses.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }
                else if(db.checkAvalibility(newname,newcode)== true)
                {
                    Toast.makeText(ManageCourses.this, "Same Course Created", Toast.LENGTH_SHORT).show();

                }
                else if(db.checkAvalibility(coursename,coursecode)==false){
                    Toast.makeText(ManageCourses.this,"Course Edited",Toast.LENGTH_SHORT).show();}


                else{

                db.editCourse(newName.getText().toString(),newCode.getText().toString(),name.getText().toString(),code.getText().toString());
                Toast.makeText(ManageCourses.this,"Course Edited",Toast.LENGTH_SHORT).show();}



            }
        }));

        list.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListCoursesActivity.class);
                v.getContext().startActivity(intent);

            }
        }));

    }
}