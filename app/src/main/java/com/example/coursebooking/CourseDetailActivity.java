package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("coursename");
        String code = intent.getStringExtra("coursecode");
        //getExtra

        EditText days = findViewById(R.id.day);
        EditText hours = findViewById(R.id.hour);
        EditText descriptions = findViewById(R.id.description);
        EditText capacitys = findViewById(R.id.capacity);
        //text

        Button apply = findViewById(R.id.apply);
        Button detail = findViewById(R.id.CourseDetail);
        //button

        CourseDatabase db = new CourseDatabase(this);


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String day = days.getText().toString();
                String hour = hours.getText().toString();
                String description = descriptions.getText().toString();
                String capacity = capacitys.getText().toString();
                if (day!=null){
                    db.setDays(name,code,day.toString());

                }
                if (hour!=null){
                    db.setHours(name,code,hour.toString());

                }
                if (description!=null){
                    db.setDescription(name,code,description.toString());

                }
                if (capacity!=null){
                    db.setCapacity(name,code,capacity.toString());

                }
                Toast.makeText(CourseDetailActivity.this ,"SUCCESS",Toast.LENGTH_SHORT).show();


            }
        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListCoursesActivity.class);
                v.getContext().startActivity(intent);




            }
        });




    }
}