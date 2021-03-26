package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ApplicantViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private ApplicantRepository applicantRepository;
    private LiveData<Integer> insertResult;
    private LiveData<Integer> updateResult;
    private LiveData<List<Applicant>> applicantsResult;
    //
    public ApplicantViewModel(@NonNull Application application) {
        super(application);
        applicantRepository = new ApplicantRepository(application);
        insertResult = applicantRepository.getInsertApplicantResult();
        updateResult = applicantRepository.getUpdateApplicantResult();
        applicantsResult = applicantRepository.getApplicantByExaminerIdResult();
    }
    //calls repository to insert a person
    public void insert(Applicant applicant) {
        applicantRepository.insertApplicant(applicant);
    }
    public void update(Applicant applicant) {
        applicantRepository.updateApplicant(applicant);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public LiveData<Integer> getUpdateResult() {
        return updateResult;
    }

    public void getApplicants(int examinerId){
        applicantRepository.getApplicantByExaminerId(examinerId);
    }
    public LiveData<List<Applicant>> getApplicantsResult(){
        return applicantsResult;
    }

}
