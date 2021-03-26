package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExaminerDAO {
    //
    @Insert
    void insertExaminer(Examiner examiner);
    //Monitoring Query Result Changes with Live Data

    @Query("select * from Examiner order by examinerId")
    LiveData<List<Examiner>> getAllLogin();

    @Query("select * from Examiner where examinerId=:eid LIMIT 1")
    Examiner loadExaminerbyExaminerId(int eid);

}