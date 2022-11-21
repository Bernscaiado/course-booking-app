package com.example.coursebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

        Spinner days = findViewById(R.id.day);
        String[] dayss = { "Monday", "Tuesday", "Wednesday","Thursday","Friday" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dayss);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(adapter);

        Spinner hours = findViewById(R.id.hour);
        String[] hourss = { "8:30-9:50", "10:00-11:20", "11:30-12:50","13:00-14:20","14:30-15:50","16:00-17:20","17:30-18:50","19:00-20:20","20:30-21:50" };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hourss);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours.setAdapter(adapter2);


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
                String day = days.getSelectedItem().toString();
                String hour = hours.getSelectedItem().toString();
                String description = descriptions.getText().toString();
                String capacity = capacitys.getText().toString();
                try{


                        if (day!=null){
                            db.setDays(name,code,day);

                        }
                        if (hour!=null){
                            db.setHours(name,code,hour);

                        }
                        if (description!=null){
                            db.setDescription(name,code,description);

                        }
                        if (capacity!=null){
                            int x = Integer.valueOf(capacity);
                            if(x<200&&x>0) {
                                db.setCapacity(name, code, capacity);
                            }
                            else{
                                Toast.makeText(CourseDetailActivity.this ,"CAPACITY OUT OF BOUND",Toast.LENGTH_SHORT).show();
                                return;

                            }

                        }






                }
                catch (Exception e){
                    Toast.makeText(CourseDetailActivity.this ,"CAPACITY TYPE ERROR",Toast.LENGTH_SHORT).show();
                    return;

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