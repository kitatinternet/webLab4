package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private TestRepository testRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Test>> testsResult;
    //
    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        insertResult = testRepository.getInsertTestResult();
        testsResult = testRepository.getExaminerByExaminerIdResult();
    }
    //calls repository to insert a person
    public void insert(Test test) {
        testRepository.insertTest(test);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public void getTest(int examinerId){
        testRepository.getTestsByExaminerId(examinerId);
    }
    public LiveData<List<Test>> getTestsResult(){
        return testsResult;
    }

}