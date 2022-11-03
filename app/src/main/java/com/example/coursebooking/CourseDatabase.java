package com.example.coursebooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDatabase extends SQLiteOpenHelper {
    private static final String TABLE_COURSE = "COURSE";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COURSECODE = "CODE";
    private static final String COLUMN_COURSENAME = "NAME";



    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public CourseDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_COURSE +
                "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " +
                COLUMN_COURSENAME + " TEXT, " +
                COLUMN_COURSECODE + ")";

        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COURSENAME,course.getCourseName());
        cv.put(COLUMN_COURSECODE,course.getCourseCode());

        long insert = db.insert(TABLE_COURSE,null,cv);
        if(insert ==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteCourse(String name, String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        int flag = db.delete(TABLE_COURSE, "name = ? and price = ?", new String[]{name, code});
        return flag !=0;
    }
    public void editCourse(String newName, String newCode, String oldName, String oldCode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(newCode.equals("")){
            cv.put(COLUMN_COURSENAME, newName);
            cv.put(COLUMN_COURSECODE, oldCode);

        }
        else if (newName.equals("")){
            cv.put(COLUMN_COURSENAME, oldName);
            cv.put(COLUMN_COURSECODE, newCode);

        }
        else{
            cv.put(COLUMN_COURSENAME, newName);
            cv.put(COLUMN_COURSECODE, newCode);
        }
        db.update(TABLE_COURSE,cv,"CourseName=",new String[]{oldName,oldCode});
        db.close();


    }
}