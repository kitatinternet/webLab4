package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Room database class
@Database(entities = {Applicant.class}, version = 1)
public abstract class ApplicantDatabase extends RoomDatabase {
    //
    private static volatile ApplicantDatabase INSTANCE;
    private static final String DATABASE_NAME = "ApplicantDB";
    public abstract ApplicantDAO applicantDAO();
    //
    public static synchronized ApplicantDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    ApplicantDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}
