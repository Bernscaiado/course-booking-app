package com.example.coursebooking;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchCourseActivity extends AppCompatActivity {
    TextView courseID;
    EditText input;
    Button findBtn;
    RadioGroup radioGroup;
    RadioButton radioButton;
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
        radioGroup = findViewById(R.id.radioGroupID);
        courseID = findViewById(R.id.courseID);
        input = findViewById(R.id.inputID);


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
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                String inputString = input.getText().toString();

                input.setText("");

                // Check which radio button was clicked
                if(radioId != -1) {
                    switch (radioId) {
                        case R.id.radioButtonDay:
                            findByCourseDay(inputString);
                            break;
                        case R.id.radioButtonName:
                            findByCourseName(inputString);
                            break;
                        case R.id.radioButtonCode:
                            findByCourseCode(inputString);
                            break;
                        default:
                            emptyFieldException();
                    }
                }
                else{emptyFieldException();}
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
            courseList.add("NO SUCH COURSE EXISTS");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }

    private void findByCourseCode(String code){
        courseList.clear();
        Cursor cursor = db.findByCode(code);
        if (cursor.moveToFirst()) {
            do{
                Course Course = new Course(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                courseList.add(Course.toString());
            }while(cursor.moveToNext());
        }

        else{
            courseList.add("NO SUCH COURSE EXISTS");
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

    private void findByCourseDay(String day){
        courseList.clear();
        Cursor cursor = db.findByDay(day);
        if (cursor.moveToFirst()) {
            do{
                Course Course = new Course(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                courseList.add(Course.toString());
            }while(cursor.moveToNext());
        }

        else{
            courseList.add("NO SUCH COURSE EXISTS");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);
    }
}


