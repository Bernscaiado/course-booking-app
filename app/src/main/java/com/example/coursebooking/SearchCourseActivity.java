package com.example.coursebooking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchCourseActivity extends AppCompatActivity {
    TextView courseID;
    EditText course_name, course_code;
    Button findBtn;
    ListView coursesListView;

    ArrayList<String> courseList;
    ArrayAdapter adapter;
    CourseDatabase db;
    //Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);

        courseList = new ArrayList<>();

        // info layout
        courseID = findViewById(R.id.courseID);
        course_name = findViewById(R.id.course_name);
        course_code = findViewById(R.id.course_code);
        //btn_back = findViewById(R.id.btn_back);

        //buttons
        findBtn = findViewById(R.id.btn_search);


        // listview
        coursesListView = findViewById(R.id.coursesListView);

        // db handler
        db = new CourseDatabase(this);

        // button listeners

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = course_name.getText().toString();
                String Code = course_code.getText().toString();
                course_name.setText("");
                course_code.setText("");

                if(name.length() > 0 && Code.length() >0)
                    findCourse(name, Code);
                else if (name.length() > 0)
                    findByCourseName(name);
                else if(Code.length() > 0)
                    findByCourseCode(Code);
                else {
                    emptyFieldException();
                }

                //Toast.makeText(MainActivity.this, "Delete Course", Toast.LENGTH_SHORT).show();
            }
        });

        viewCourses();
    }

    private void viewCourses() {
        courseList.clear();
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(SearchCourseActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                courseList.add(cursor.getString(1) +" " +cursor.getString(2));
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }

    private void findByCourseName(String name){
        courseList.clear();
        Cursor cursor = db.findByName(name);
        if (cursor.moveToFirst()) {
            do{
                Course Course = new Course(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                courseList.add(Course.toString());
            }while(cursor.moveToNext());
        }

        else{
            courseList.add("NO SUCH Course EXISTS");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }

    private void findByCourseCode(String Code){
        courseList.clear();
        Cursor cursor = db.findByCode(Code);
        if (cursor.moveToFirst()) {
            do{
                Course Course = new Course(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                courseList.add(Course.toString());
            }while(cursor.moveToNext());
        }

        else{
            courseList.add("NO SUCH Course EXISTS");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }

    private void findCourse(String name, String Code){
        courseList.clear();
        Cursor cursor = db.findCourse(name, Code);
        if (cursor.moveToFirst()) {
            do{
                Course Course = new Course(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                courseList.add(Course.toString());
            }while(cursor.moveToNext());
        }

        else{
            courseList.add("NO SUCH Course EXISTS");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }

    private void emptyFieldException(){
        courseList.clear();
        courseList.add("This Operation cannot be performed. Please complete at least one field.");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }
}


