package com.example.coursebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons
        Button loginAcc = findViewById(R.id.login);
        Button createAcc = findViewById(R.id.createAcc);

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

        // button listeners
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String first = firstname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = spinner.getSelectedItem().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT);

                } else {
                    Boolean checkuser = db.checkUsername(user);
                    if (checkuser == false) {
                        User new_user = new Student(user, first, role, pass);
                        Boolean insert = db.addUser(new_user);
                        if (insert == true) {
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG);
                            Intent intent;
                            if (role == "admin")
                            {
                                intent = new Intent(v.getContext(), MainActivity2.class);
                            } else {
                                intent = new Intent(v.getContext(), MainActivity3.class);
                            }
                            v.getContext().startActivity(intent);
                        }
                    }

                }

            }
        });

        loginAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String first = firstname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = spinner.getSelectedItem().toString();


                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT);
                } else {
                    Boolean checkuserpass = db.checkUsernamePassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(MainActivity.this, "Sign in Successfully", Toast.LENGTH_LONG);

                        Intent intent;
                        if (role == "admin")
                        {
                            intent = new Intent(v.getContext(), MainActivity2.class);
                        } else {
                            intent = new Intent(v.getContext(), MainActivity3.class);
                        }
                        v.getContext().startActivity(intent);
                    }
                }
            }
        });


    }
}