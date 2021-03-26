package com.example.myapplication;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;





public class ApplicantRepository {
    private final ApplicantDAO applicantDAO;
    private MutableLiveData<Integer> insertApplicantResult = new MutableLiveData<>();
    private MutableLiveData<Integer> updateApplicantResult = new MutableLiveData<>();
    //private LiveData<List<Examiner>> examinersList;
    private MutableLiveData<List<Applicant>> getApplicantsByExaminerIdResult = new MutableLiveData<>();
    //private Examiner examiner;
    //
    public ApplicantRepository(Context context) {
        //create a database object
        ApplicantDatabase db = ApplicantDatabase.getInstance(context);
        //create an interface object
        applicantDAO = db.applicantDAO();
        //call interface method
        //examinersList = applicantDAO.getAllLogin();
    }
    // returns query results as LiveData object
    //LiveData<List<Examiner>> getAllExaminers() {
       // return examinersList;
    //}
    //inserts a person asynchronously
    public void insertApplicant(Applicant applicant) {
        insertApplicantAsync(applicant);
    }

    public void updateApplicant(Applicant applicant) {
        updateApplicantAsync(applicant);
    }

    public void getApplicantByExaminerId(int examinerId) { getApplicantByExaminerIdAsync(examinerId);}

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertApplicantResult() {
        return insertApplicantResult;
    }

    public LiveData<Integer> getUpdateApplicantResult() {
        return updateApplicantResult;
    }

    public LiveData<List<Applicant>> getApplicantByExaminerIdResult() {return getApplicantsByExaminerIdResult;}

    private void insertApplicantAsync(final Applicant applicant) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    applicantDAO.insertApplicant(applicant);
                    insertApplicantResult.postValue(1);
                } catch (Exception e) {
                    insertApplicantResult.postValue(0);
                }
            }
        }).start();
    }

    private void updateApplicantAsync(final Applicant applicant) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    applicantDAO.updateApplicant(applicant);
                    updateApplicantResult.postValue(1);
                } catch (Exception e) {
                    updateApplicantResult.postValue(0);
                }
            }
        }).start();
    }

    private void getApplicantByExaminerIdAsync(final int examinerId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                getApplicantsByExaminerIdResult.postValue(applicantDAO.loadApplicantsbyExaminerId(examinerId));
            }
        }).start();
    }

}
