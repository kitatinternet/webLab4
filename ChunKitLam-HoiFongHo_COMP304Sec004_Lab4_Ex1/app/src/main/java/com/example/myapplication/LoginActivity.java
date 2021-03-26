package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ExaminerViewModel examinerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().setTitle(R.string.text_main_login_activity_title);

        examinerViewModel = ViewModelProviders.of(this).get(ExaminerViewModel.class);
        //
        //Examiner examiner = new Examiner(1001, "Chun", "Lam", "Maple", "1111");
        Examiner examiner = new Examiner(1002, "Kit", "Lam", "Maple", "2222");
        examinerViewModel.insert(examiner);

        examinerViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(LoginActivity.this, "Examiner successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Error saving examiner", Toast.LENGTH_SHORT).show();
                }
            }
        });

        examinerViewModel.getAllExaminers().observe(this, new Observer<List<Examiner>>() {
            @Override
            public void onChanged(@Nullable List<Examiner> result) {
                String output="";
                for(Examiner examiner : result) {
                    output+= examiner.getExaminerId() +"\n";
                }
                Toast.makeText(LoginActivity.this, "Examiners:" + output, Toast.LENGTH_SHORT).show();
            }
        });

    }// end onCreate

    public void checkLogin(View view)
    {
        TextView editTextTextExaminerID = findViewById(R.id.editTextTextExaminerID);
        TextView editTextTextPassword = findViewById(R.id.editTextTextPassword);
        int inputExaminerId = Integer.parseInt(editTextTextExaminerID.getText().toString());
        String inputPassword = editTextTextPassword.getText().toString();

         //create an interface object
        examinerViewModel.getExaminer(inputExaminerId);
        examinerViewModel.getExaminerResult().observe(this, new Observer<Examiner>() {
            @Override
            public void onChanged(@Nullable Examiner e) {

                if (e.getExaminerId() == inputExaminerId && e.getPassword().equals(inputPassword)){
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    Toast.makeText(LoginActivity.this, "Login Successful!" + inputExaminerId + "/" + inputPassword, Toast.LENGTH_SHORT).show();
                    editor.putInt("LoginSuccessfulId", inputExaminerId);
                    editor.commit();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful!" + inputExaminerId + "/" + inputPassword, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }// end checkLogin

}