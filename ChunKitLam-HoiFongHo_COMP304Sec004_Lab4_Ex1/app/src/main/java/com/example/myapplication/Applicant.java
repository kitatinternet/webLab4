package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Applicant {

    @PrimaryKey(autoGenerate = true)
    private int applicantId;
    private String firstname;
    private String lastname;
    private String testcenter;
    private int examinerId;


    public Applicant(int applicantId, String firstname, String lastname, String testcenter, int examinerId){
        this.applicantId = applicantId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.testcenter = testcenter;
        this.examinerId = examinerId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTestcenter() {
        return testcenter;
    }

    public void setTestcenter(String testcenter) {
        this.testcenter = testcenter;
    }

    public int getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(int examinerId) {
        this.examinerId = examinerId;
    }

}