package com.example.coursebooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManageUsers extends AppCompatActivity {
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        ArrayList<String> userList;
        ArrayAdapter adapter;
        //Array

        Button btn_back2 = findViewById(R.id.btn_back2);
        Button deleteStudent = findViewById(R.id.deleteStudent);
        Button view = findViewById(R.id.viewall);
        Button next = findViewById(R.id.view);
        //Button
        EditText firstname = findViewById(R.id.firstname);
        EditText username = findViewById(R.id.username);
        // EditText
        dbHandler = new DBHandler(this);
        //database




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor cursor = dbHandler.getData();
               if (cursor.getCount()==0){
                   Toast.makeText(ManageUsers.this,"no users available",Toast.LENGTH_SHORT);
                   return;
               }
               StringBuffer buffer = new StringBuffer();
               while (cursor.moveToNext()){
                   buffer.append("firstname:"+cursor.getString(0)+"\n");
                   buffer.append("username:"+cursor.getString(1)+"\n");
                   buffer.append("role:"+cursor.getString(2)+"\n\n");
               }
               AlertDialog.Builder builder = new AlertDialog.Builder(ManageUsers.this);
               builder.setCancelable(true);
               builder.setTitle("UsersList");
               builder.setMessage(buffer.toString());
               builder.show();



            }
        });

        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                v.getContext().startActivity(intent);
            }
        });

        deleteStudent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String userName = username.getText().toString();
                String firstName = firstname.getText().toString();
                deleteUser(userName,firstName);
            }
        });
        next.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListUsersActivity.class);
                v.getContext().startActivity(intent);

            }
        }));
    }

    private void deleteUser(String username, String firstName){
        AlertDialog alertDialog = new AlertDialog.Builder(ManageUsers.this).create();
        alertDialog.setTitle("Notification");
        boolean flag = dbHandler.deleteUser(username, firstName);
        System.out.println("FLAG: "+flag);
        if (flag)
            alertDialog.setMessage("DELETION WAS SUCCESSFUL");
        else
            alertDialog.setMessage("DELETION WAS UNSUCCESSFUL. NO SUCH USER EXISTS");

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

        });
        alertDialog.show();

    }


}