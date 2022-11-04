package com.example.coursebooking;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDatabase extends SQLiteOpenHelper {
    private static final String TABLE_COURSES = "courses";
    private static final String COLUMN_COURSENAME = "name";
    private static final String COLUMN_COURSECODE = "code";
    private static final String COLUMN_ID = "id";




    private static final String DATABASE_NAME = "courses.db";
    private static final int DATABASE_VERSION = 1;

    public CourseDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_COURSES +
                "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " +
                COLUMN_COURSENAME + " TEXT, " +
                COLUMN_COURSECODE + ")";

        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);

    }

    public Boolean checkAvalibility(String coursename, String coursecode) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM courses WHERE name = ? AND code = ?", new String[] {coursename,coursecode});
        return cursor.getCount()>0;
    }

    public boolean addOne(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COURSENAME,course.getCourseName());
        cv.put(COLUMN_COURSECODE,course.getCourseCode());



        long res = db.insert(TABLE_COURSES,null,cv);
        if(res ==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteCourse(String name, String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        int flag = db.delete(TABLE_COURSES, "name=? and code = ?", new String[]{name , code});
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
        db.update(TABLE_COURSES,cv,"name = ? ",new String[]{oldName});
        db.close();


    }
}