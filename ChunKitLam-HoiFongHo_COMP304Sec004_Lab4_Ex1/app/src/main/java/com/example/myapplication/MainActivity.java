package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setTitle(R.string.text_main_activity_title);

        SharedPreferences myScores = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        if (myScores.contains("LoginSuccessfulId")) {
            int currentExaminerId = myScores.getInt("LoginSuccessfulId", 0);
            TextView textViewEnterTest = (TextView) findViewById(R.id.textViewEnterTest);
            TextView textViewEnterView = (TextView) findViewById(R.id.textViewEnterView);
            TextView textViewViewTest = (TextView) findViewById(R.id.textViewViewTest);
            TextView textViewUpdateTest = (TextView) findViewById(R.id.textViewUpdateTest);
            ImageButton imageButtonEnterTest = (ImageButton) findViewById(R.id.imageButtonEnterTest);
            ImageButton imageButtonEnterView = (ImageButton) findViewById(R.id.imageButtonEnterView);
            ImageButton imageButtonViewTest = (ImageButton) findViewById(R.id.imageButtonViewTest);
            ImageButton imageButtonUpdateTest = (ImageButton) findViewById(R.id.imageButtonUpdateTest);
            textViewEnterTest.setVisibility(View.VISIBLE);
            textViewEnterView.setVisibility(View.VISIBLE);
            textViewViewTest.setVisibility(View.VISIBLE);
            textViewUpdateTest.setVisibility(View.VISIBLE);
            imageButtonEnterTest.setVisibility(View.VISIBLE);
            imageButtonEnterView.setVisibility(View.VISIBLE);
            imageButtonViewTest.setVisibility(View.VISIBLE);
            imageButtonUpdateTest.setVisibility(View.VISIBLE);

            Toast.makeText(MainActivity.this, "Login successful!" + currentExaminerId, Toast.LENGTH_SHORT).show();
        }


    }

    public void loginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void applicantActivity(View view) {
        Intent intent = new Intent(this, ApplicantActivity.class);
        startActivity(intent);
    }

    public void testActivity(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void viewTestActivity(View view) {
        Intent intent = new Intent(this, ViewTestActivity.class);
        startActivity(intent);
    }

    public void updateActivity(View view) {
        Intent intent = new Intent(this, UpdateActivity.class);
        startActivity(intent);
    }
}