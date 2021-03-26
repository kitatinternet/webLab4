package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ApplicantDAO {
    //
    @Insert
    void insertApplicant(Applicant applicant);
    //Monitoring Query Result Changes with Live Data

    @Query("select * from Applicant where examinerId=:eid order by ApplicantId")
    List<Applicant> loadApplicantsbyExaminerId(int eid);

    @Update
    void updateApplicant(Applicant applicant);

}
