package com.example.myapplication;

import androidx.lifecycle.Observer;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Examiner {
    @PrimaryKey(autoGenerate = true)
    private int examinerId;
    private String firstname;
    private String lastname;
    private String testcenter;
    private String password;


    public Examiner(int examinerId, String firstname, String lastname, String testcenter, String password){
        this.examinerId = examinerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.testcenter = testcenter;
        this.password = password;
    }

    public int getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(int examinerId) {
        this.examinerId = examinerId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void observe(LoginActivity loginActivity, Observer<Examiner> examinerObserver) {
    }
}