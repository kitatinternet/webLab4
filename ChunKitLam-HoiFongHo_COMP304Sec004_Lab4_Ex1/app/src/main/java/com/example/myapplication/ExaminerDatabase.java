package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Room database class
@Database(entities = {Examiner.class}, version = 1)
public abstract class ExaminerDatabase extends RoomDatabase {
    //
    private static volatile ExaminerDatabase INSTANCE;
    private static final String DATABASE_NAME = "ExaminerDB";
    public abstract ExaminerDAO examinerDAO();
    //
    public static synchronized ExaminerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    ExaminerDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}