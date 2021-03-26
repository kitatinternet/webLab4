package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;



@Dao
public interface TestDAO {
    //
    @Insert
    void insertTest(Test test);
    //Monitoring Query Result Changes with Live Data

    @Query("select * from Test where examinerId=:eid")
    LiveData<List<Test>> loadTestsbyExaminerId(int eid);

}