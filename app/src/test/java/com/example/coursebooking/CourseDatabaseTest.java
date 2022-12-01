package com.example.coursebooking;


import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CourseDatabaseTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private Course course;
    private CourseDatabase db = new CourseDatabase(appContext);

    @Test
    void getDataTest() {
        int listNum = db.getData().getCount();
        assertEquals(1,listNum);
    }

//    @org.junit.jupiter.api.Test
//    void testGetData() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void findByName() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void findByCode() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void findByDay() {
//    }
}