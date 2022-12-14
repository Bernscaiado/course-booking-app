package com.example.coursebooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ENROLLED = "enrolled";


    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_ENROLLED + ")";

        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public Boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_FIRSTNAME, user.getFirstname());
        values.put(COLUMN_ROLE, user.getRole());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ENROLLED, "");


        long result = db.insert(TABLE_USERS, null, values);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE username = ?", new String[] {username});
        return cursor.getCount()>0;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE username = ? AND password = ?", new String[] {username,password});
        return cursor.getCount()>0;
    }
    public boolean deleteUser(String username, String firstname) {
        SQLiteDatabase db = this.getWritableDatabase();
        int flag = db.delete(TABLE_USERS, "username = ? and firstname = ?", new String[]{username, firstname});
        return flag !=0;
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS;
        return db.rawQuery(query, null); // returns "cursor" all Courses from the table
    }

    public boolean addEnrolled(String name, String code, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String res = getEnrolled(username);
        cv.put(COLUMN_ENROLLED, res+""+name+" "+code);
        db.update(TABLE_USERS,cv,"username = ? ",new String[]{username});
        return true;

    }

    public boolean removeEnrolled(String name, String code, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String result = getEnrolled(username);
        String res = name + " " + code;


        cv.put(COLUMN_ENROLLED, result.replace(res,""));
        db.update(TABLE_USERS,cv,"username = ? ",new String[]{username});
        return true;

    }


    public String getEnrolled(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = this.getData();

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(name)){
                return cursor.getString(5);

            }

        }
        return "";


    }

}