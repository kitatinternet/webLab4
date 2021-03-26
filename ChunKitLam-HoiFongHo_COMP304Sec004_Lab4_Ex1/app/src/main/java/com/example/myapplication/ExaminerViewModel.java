package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;



public class ExaminerViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private ExaminerRepository examinerRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Examiner>> allExaminers;
    private LiveData<Examiner> examinerResult;
    //
    public ExaminerViewModel(@NonNull Application application) {
        super(application);
        examinerRepository = new ExaminerRepository(application);
        insertResult = examinerRepository.getInsertExaminerResult();
        allExaminers = examinerRepository.getAllExaminers();
        examinerResult = examinerRepository.getExaminerByExaminerIdResult();
    }
    //calls repository to insert a person
    public void insert(Examiner examiner) {
        examinerRepository.insertExaminer(examiner);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    public LiveData<List<Examiner>> getAllExaminers() { return allExaminers; }

    public void getExaminer(int examinerId){
        examinerRepository.getExaminerByExaminerId(examinerId);
    }
    public LiveData<Examiner> getExaminerResult(){
        return examinerResult;
    }

}
