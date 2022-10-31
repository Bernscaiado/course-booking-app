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

public class SignupActivity4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup4);
        Button signup = findViewById(R.id.signup);
        Button previous = findViewById(R.id.previous);
        EditText firstname = findViewById(R.id.firstname);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        // Spinner config
        Spinner spinner = findViewById(R.id.spinner);
        String[] roles = { "Instructor", "Student", "Admin" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        DBHandler db = new DBHandler(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String first = firstname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = spinner.getSelectedItem().toString();



                if (user.equals("") || pass.equals("") || first.equals("")) {
                    Toast.makeText(SignupActivity4.this, "Please enter all fields", Toast.LENGTH_SHORT).show();}
                else if(role.equals("Admin")){
                    Toast.makeText(SignupActivity4.this, "Admin cannot be created", Toast.LENGTH_SHORT).show();

                } else {
                    User new_user = new Student(user, first, role, pass);
                    Boolean checkuser = db.checkUsername(user);
                    if (checkuser == false) {

                        Boolean insert = db.addUser(new_user);
                        if (insert == true) {
                            Toast.makeText(SignupActivity4.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            Intent intent;
                                intent = new Intent(v.getContext(), StudentInstructorActivity.class);
                                v.getContext().startActivity(intent);


                        }
                    }

                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }



}