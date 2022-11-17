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
    private static final String COLUMN_INSTRUCTOR = "instructor";
    private static final String COLUMN_DAYS = "day";
    private static final String COLUMN_HOURS = "hour";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CAPACITY = "capacity";





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
                COLUMN_COURSECODE + " TEXT, " +
                COLUMN_INSTRUCTOR  + " TEXT, " +
                COLUMN_DAYS + " TEXT, " +
                COLUMN_HOURS + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_CAPACITY + ")";

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

    public boolean setInstructor(String name, String code, String instructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_INSTRUCTOR, instructor);
        db.update(TABLE_COURSES,cv,"name = ? and code = ? ",new String[]{name,code});
        return true;
    }

    public boolean nullInstructor(String name, String code, String instructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.putNull(COLUMN_INSTRUCTOR);
        db.update(TABLE_COURSES,cv,"name = ? and code = ? AND instructor = ?",new String[]{name,code,instructor});
        return true;
    }

    public boolean hasInstructor(String coursename, String coursecode) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM courses WHERE name = ? AND code = ? AND instructor IS NOT NULL", new String[] {coursename,coursecode});
        return cursor.getCount()>0;
    }

    public boolean checkInstructor(String name,String code,String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM courses WHERE name = ? AND code = ? AND instructor = ?", new String[] {name,code,username});


        return cursor.getCount()>0;


    }
    public String getInstructor(String name , String code){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = this.getData();

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(name)&&cursor.getString(2).equals(code)){
                return cursor.getString(3);

            }

        }
        return "";






    }


    public void clearDetails(String name, String code){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.putNull(COLUMN_DAYS);
        cv.putNull(COLUMN_HOURS);
        cv.putNull(COLUMN_DESCRIPTION);
        cv.putNull(COLUMN_CAPACITY);

        db.update(TABLE_COURSES,cv,"name = ? and code = ? ",new String[]{name,code});



    }
    public boolean setDays(String name, String code, String day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DAYS, day);
        db.update(TABLE_COURSES,cv,"name = ? and code = ? ",new String[]{name,code});
        return true;
    }
    public boolean setHours(String name, String code, String hour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_HOURS, hour);
        db.update(TABLE_COURSES,cv,"name = ? and code = ? ",new String[]{name,code});
        return true;
    }
    public boolean setDescription(String name, String code, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DESCRIPTION, description);
        db.update(TABLE_COURSES,cv,"name = ? and code = ?",new String[]{name,code});
        return true;
    }
    public boolean setCapacity(String name, String code, String capacity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COURSENAME, name);
        cv.put(COLUMN_COURSECODE, code);
        cv.put(COLUMN_CAPACITY, capacity);
        db.update(TABLE_COURSES,cv,"name = ? and code = ?",new String[]{name,code});
        return true;
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
        db.update(TABLE_COURSES,cv,"name = ? and code = ? ",new String[]{oldName,oldCode});
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_COURSES;
        return db.rawQuery(query, null); // returns "cursor" all Courses from the table
    }

    public Cursor getData(String coursename, String coursecode){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM TABLE_COURSES WHERE name = ? AND code = ?", new String[]{coursename,coursecode} );
    }

    public Cursor findByName(String keyword) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_COURSES + " where " + COLUMN_COURSENAME + " = ?" ,new String[] { keyword });
        return cursor;
    }
    public Cursor findByCode(String keyword) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_COURSES + " where " + COLUMN_COURSECODE + " = ?" ,new String[] { keyword });
        return cursor;
    }

    public Cursor findCourse(String name, String code) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_COURSES + " where " + COLUMN_COURSENAME + " = ? and " + COLUMN_COURSECODE + "= ?" ,new String[] { name, code });
        return cursor;
    }
}