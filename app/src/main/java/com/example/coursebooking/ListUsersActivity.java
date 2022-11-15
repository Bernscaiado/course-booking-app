package com.example.coursebooking;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {
    ListView UserListView;
    ArrayList<String> UserList;
    ArrayAdapter adapter;
    DBHandler dbHandler;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UserList = new ArrayList<>();
        UserListView = findViewById(R.id.CourseListView);
        dbHandler = new DBHandler(this);

        UserList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(ListUsersActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            UserList.add("username"+ " "+ "firstname" + " " + "role");
            while (cursor.moveToNext()) {
                UserList.add(cursor.getString(1) + " "+ cursor.getString(2) + " " + cursor.getString(3));
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, UserList);
        UserListView.setAdapter(adapter);




    }
}
