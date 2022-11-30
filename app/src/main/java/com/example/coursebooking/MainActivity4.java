package com.example.coursebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button list = findViewById(R.id.listCourses);
        Button enroll = findViewById(R.id.enroll);
        Button searchCourse = findViewById(R.id.searchCourse);
        Button myCourses = findViewById(R.id.myCourses);


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

        searchCourse.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchCourseActivity.class);
                intent.putExtra("firstname", str1);
                intent.putExtra("username", str2);
                intent.putExtra("role", str3);

                v.getContext().startActivity(intent);
            }
        }));

        enroll.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CourseEnrollActivity.class);

                intent.putExtra("firstname", str1);
                intent.putExtra("username", str2);
                intent.putExtra("role", str3);

                v.getContext().startActivity(intent);
            }
        }));

        myCourses.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ListStudentEnrolledActivity.class);

                intent.putExtra("firstname", str1);
                intent.putExtra("username", str2);
                intent.putExtra("role", str3);

                v.getContext().startActivity(intent);

            }
        }));

    }
}