package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ApplicantActivity extends AppCompatActivity {

    private ExaminerViewModel examinerViewModel;
    private ApplicantViewModel applicantViewModel;
    Examiner currentExaminer;
    MyCustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);
        this.getSupportActionBar().setTitle(R.string.text_applicant_activity_title);

        examinerViewModel = ViewModelProviders.of(this).get(ExaminerViewModel.class);
        applicantViewModel = ViewModelProviders.of(this).get(ApplicantViewModel.class);

        SharedPreferences myScores = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        if (myScores.contains("LoginSuccessfulId")) {
            int currentExaminerId = myScores.getInt("LoginSuccessfulId", 0);
            loadExaminer(currentExaminerId);

            applicantViewModel.getApplicantsResult().observe(this, new Observer<List<Applicant>>() {
                @Override
                public void onChanged(@Nullable List<Applicant> result) {
                    String output="";
                    for(Applicant applicant : result) {
                        output+= applicant.getApplicantId() +"\n";
                    }
                    Toast.makeText(ApplicantActivity.this, "onCreate Applicants:" + output, Toast.LENGTH_SHORT).show();
                    updateListViewTest(result);
                }
            });
        }
    }

    public void loadExaminer(int examinerId){
        examinerViewModel.getExaminer(examinerId);
        examinerViewModel.getExaminerResult().observe(this, new Observer<Examiner>() {
            @Override
            public void onChanged(@Nullable Examiner e) {
                currentExaminer = e;
                TextView textViewTestExaminerName = findViewById(R.id.textViewTestExaminerName);
                textViewTestExaminerName.append(" [" + e.getFirstname() + " " + e.getLastname() + "] ");

                TextView textViewApplicantTestCenter = findViewById(R.id.textViewApplicantTestCenter);
                textViewApplicantTestCenter.append(" [" + e.getTestcenter()+ "] ");

                applicantViewModel.getApplicants(currentExaminer.getExaminerId());
            }
        });
    }

    public void clickEnter(View view) {
        TextView editTextFirstName = findViewById(R.id.editTextFirstName);
        TextView editTextLastName = findViewById(R.id.editTextLastName);
        TextView editTextApplicantId = findViewById(R.id.editTextApplicantId);
        int inputApplicantId = Integer.parseInt(editTextApplicantId.getText().toString());

        Applicant applicant = new Applicant(inputApplicantId,editTextFirstName.getText().toString(), editTextLastName.getText().toString(), currentExaminer.getTestcenter(), currentExaminer.getExaminerId());
        applicantViewModel.insert(applicant);

        applicantViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(ApplicantActivity.this, "Applicant successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ApplicantActivity.this, "Error saving Applicant", Toast.LENGTH_SHORT).show();
                }
                applicantViewModel.getApplicants(currentExaminer.getExaminerId());
            }
        });

        applicantViewModel.getApplicantsResult().observe(this, new Observer<List<Applicant>>() {
            @Override
            public void onChanged(@Nullable List<Applicant> result) {
                String output="";
                for(Applicant applicant : result) {
                    output+= applicant.getApplicantId() +"\n";
                }
                Toast.makeText(ApplicantActivity.this, "Click Applicants:" + output, Toast.LENGTH_SHORT).show();
                updateListViewTest(result);
            }
        });
    }

    public void updateListViewTest(List<Applicant> applicants){

        ArrayList<Applicant> arrayApplicants = new ArrayList<Applicant>(applicants.size());
        for (Applicant a : applicants){
            arrayApplicants.add(a);
        }
        //arrayApplicants.addAll(applicants);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.test_item, arrayApplicants);
        ListView listView = (ListView) findViewById(R.id.ListViewTest);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

    }


    private class MyCustomAdapter extends ArrayAdapter<Applicant> {

        private ArrayList<Applicant> applicants;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Applicant> applicantList) {
            super(context, textViewResourceId, applicantList);
            this.applicants = new ArrayList<Applicant>();
            this.applicants.addAll(applicantList);
        }

        private class ViewHolder {
            TextView applicantId;
            TextView applicantName;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.test_item, null);

                holder = new ViewHolder();
                holder.applicantId = (TextView) convertView.findViewById(R.id.textViewApplicatId);
                holder.applicantName = (TextView) convertView.findViewById(R.id.textViewApplicatName);
                convertView.setTag(holder);


            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Applicant applicant = applicants.get(position);

                holder.applicantId.setText(""+ applicant.getApplicantId());
                holder.applicantName.setText(applicant.getFirstname() + " " + applicant.getLastname());


            return convertView;
        }

    }
}