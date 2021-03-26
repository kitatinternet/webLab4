package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Room database class
@Database(entities = {Test.class}, version = 1)
public abstract class TestDatabase extends RoomDatabase {
    //
    private static volatile TestDatabase INSTANCE;
    private static final String DATABASE_NAME = "TestDB";
    public abstract TestDAO testDAO();
    //
    public static synchronized TestDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    TestDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}
