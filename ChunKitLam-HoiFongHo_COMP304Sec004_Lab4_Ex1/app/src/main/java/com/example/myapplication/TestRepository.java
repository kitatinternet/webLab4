package com.example.myapplication;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TestRepository {
    private final TestDAO testDAO;
    private MutableLiveData<Integer> insertTestResult = new MutableLiveData<>();

    private MutableLiveData<List<Test>> getTestsByExaminerIdResult = new MutableLiveData<>();
    //private Examiner examiner;
    //
    public TestRepository(Context context) {
        //create a database object
        TestDatabase db = TestDatabase.getInstance(context);
        //create an interface object
        testDAO = db.testDAO();
        //call interface method

    }
    // returns query results as LiveData object

    //inserts a person asynchronously
    public void insertTest(Test test) {
        insertTestAsync(test);
    }

    public void getTestsByExaminerId(int examinerId) { getTestsByExaminerIdAsync(examinerId);}

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertTestResult() {
        return insertTestResult;
    }

    public LiveData<List<Test>> getExaminerByExaminerIdResult() {return getTestsByExaminerIdResult;}

    private void insertTestAsync(final Test test) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testDAO.insertTest(test);
                    insertTestResult.postValue(1);
                } catch (Exception e) {
                    insertTestResult.postValue(0);
                }
            }
        }).start();
    }

    private void getTestsByExaminerIdAsync(final int examinerId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                getTestsByExaminerIdResult.postValue((List<Test>) testDAO.loadTestsbyExaminerId(examinerId));
            }
        }).start();
    }

}