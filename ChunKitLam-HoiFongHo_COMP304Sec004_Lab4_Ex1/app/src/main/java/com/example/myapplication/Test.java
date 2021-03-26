package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Test {

    @PrimaryKey(autoGenerate = true)
    private int testId;
    private int applicantId;
    private int examinerId;
    private String testresult;
    private String testdate;
    private String testrout;
    @Ignore
    private int tIdCount = 1;

    public Test(int applicantId, int examinerId, String testresult, String testdate, String testrout){
        this.testId = tIdCount++;
        this.applicantId = applicantId;
        this.examinerId = examinerId;
        this.testresult = testresult;
        this.testdate = testdate;
        this.testrout = testrout;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(int examinerId) {
        this.examinerId = examinerId;
    }

    public String getTestresult() {
        return testresult;
    }

    public void setTestresult(String testresult) {
        this.testresult = testresult;
    }

    public String getTestdate() {
        return testdate;
    }

    public void setTestdate(String testdate) {
        this.testdate = testdate;
    }

    public String getTestrout() {
        return testrout;
    }

    public void setTestrout(String testrout) {
        this.testrout = testrout;
    }
}