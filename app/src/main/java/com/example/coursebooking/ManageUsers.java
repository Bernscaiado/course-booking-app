package com.example.coursebooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManageUsers extends AppCompatActivity {
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        Button btn_back2 = findViewById(R.id.btn_back2);
        Button deleteStudent = findViewById(R.id.deleteStudent);
        EditText firstname = findViewById(R.id.firstname);
        EditText username = findViewById(R.id.username);
        dbHandler = new DBHandler(this);

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