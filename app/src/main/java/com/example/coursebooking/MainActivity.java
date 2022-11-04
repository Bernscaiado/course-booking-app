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

        // Creation of DBHandler to manage User database
        DBHandler db = new DBHandler(this);

        // Creation of only admin account
        User new_user = new Admin("admin", "admin", "Admin", "admin123");
        Boolean check_new_user = db.checkUsername("admin");
        if (check_new_user == false) {
            db.addUser(new_user);
        }


        // button listeners
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String first = firstname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = spinner.getSelectedItem().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean checkuser = db.checkUsername(user);
                    if (checkuser == true) {
                        Toast.makeText(MainActivity.this, "User is already registered", Toast.LENGTH_SHORT).show();
                    }
                    if (role == "Admin") {
                        Toast.makeText(MainActivity.this, "Cannot Create Admin Account", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        User new_user = new Student(user, first, role, pass);
                        Boolean insert = db.addUser(new_user);
                        if (insert == true) {
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent;
                            if (role.equals("Admin"))
                            {
                                intent = new Intent(v.getContext(), MainActivity2.class);
                            } else {
                                intent = new Intent(v.getContext(), MainActivity3.class);
                            }
                            intent.putExtra("firstname", first);
                            intent.putExtra("username", user);
                            intent.putExtra("role", role);


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
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = db.checkUsernamePassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(MainActivity.this, "Sign in Successfully", Toast.LENGTH_LONG).show();

                        Intent intent;
                        if (role.equals("Admin"))
                        {
                            intent = new Intent(v.getContext(), MainActivity2.class);
                        } else {
                            intent = new Intent(v.getContext(), MainActivity3.class);
                        }
                        intent.putExtra("firstname", first);
                        intent.putExtra("username", user);
                        intent.putExtra("role", role);

                        v.getContext().startActivity(intent);
                    }
                }
            }
        });


    }
}