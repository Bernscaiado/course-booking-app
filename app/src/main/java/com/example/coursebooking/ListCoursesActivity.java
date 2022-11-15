package com.example.coursebooking;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListCoursesActivity extends AppCompatActivity {
    ListView CourseListView;
    ArrayList<String> CourseList;
    ArrayAdapter adapter;
    CourseDatabase dbHandler;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);


        CourseList = new ArrayList<>();
        CourseListView = findViewById(R.id.CourseListView);
        dbHandler = new CourseDatabase(this);

        CourseList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(ListCoursesActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                CourseList.add(cursor.getString(1) + " " + cursor.getString(2));
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CourseList);
        CourseListView.setAdapter(adapter);




    }
}
