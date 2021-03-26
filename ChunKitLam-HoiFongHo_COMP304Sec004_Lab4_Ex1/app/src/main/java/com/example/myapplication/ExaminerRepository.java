package com.example.myapplication;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;



public class ExaminerRepository {
    private final ExaminerDAO examinerDAO;
    private MutableLiveData<Integer> insertExaminerResult = new MutableLiveData<>();
    private LiveData<List<Examiner>> examinersList;
    private MutableLiveData<Examiner> getExaminerByExaminerIdResult = new MutableLiveData<>();
    //private Examiner examiner;
    //
    public ExaminerRepository(Context context) {
        //create a database object
        ExaminerDatabase db = ExaminerDatabase.getInstance(context);
        //create an interface object
        examinerDAO = db.examinerDAO();
        //call interface method
        examinersList = examinerDAO.getAllLogin();
    }
    // returns query results as LiveData object
    LiveData<List<Examiner>> getAllExaminers() {
        return examinersList;
    }
    //inserts a person asynchronously
    public void insertExaminer(Examiner examiner) {
        insertExaminerAsync(examiner);
    }

    public void getExaminerByExaminerId(int examinerId) { getExaminerByExaminerIdAsync(examinerId);}

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertExaminerResult() {
        return insertExaminerResult;
    }

    public LiveData<Examiner> getExaminerByExaminerIdResult() {return getExaminerByExaminerIdResult;}

    private void insertExaminerAsync(final Examiner examiner) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    examinerDAO.insertExaminer(examiner);
                    insertExaminerResult.postValue(1);
                } catch (Exception e) {
                    insertExaminerResult.postValue(0);
                }
            }
        }).start();
    }

    private void getExaminerByExaminerIdAsync(final int examinerId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                getExaminerByExaminerIdResult.postValue(examinerDAO.loadExaminerbyExaminerId(examinerId));
            }
        }).start();
    }

}

