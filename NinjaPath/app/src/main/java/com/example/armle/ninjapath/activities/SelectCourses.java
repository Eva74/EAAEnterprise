package com.example.armle.ninjapath.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.armle.ninjapath.R;
import com.example.armle.ninjapath.helper.InputValidation;
import com.example.armle.ninjapath.model.ClassInfo;
import com.example.armle.ninjapath.model.Courses;
import com.example.armle.ninjapath.sql.DatabaseHelper;

public class SelectCourses extends AppCompatActivity implements View.OnClickListener {

    private Activity activity = SelectCourses.this;

    private ArrayAdapter<String> adapter;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private AutoCompleteTextView acTextView1;
    private AutoCompleteTextView acTextView2;
    private AutoCompleteTextView acTextView3;
    private AutoCompleteTextView acTextView4;

    private AppCompatButton sumbitCourses;





    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_select_courses);

        initViews();
        initListeners();
        initObjects();
        loadTextViewData();
        settleAdapters();

    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
    }

    private void initListeners() {

        sumbitCourses.setOnClickListener(this);
    }


    private void loadTextViewData() {

        List<String> courses = databaseHelper.getAllCourseNames();
        adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, courses);
    }

    private void initViews() {
        acTextView1 = (AutoCompleteTextView) findViewById(R.id.dropdown1);
        acTextView2 = (AutoCompleteTextView) findViewById(R.id.dropdown2);
        acTextView3 = (AutoCompleteTextView) findViewById(R.id.dropdown3);
        acTextView4 = (AutoCompleteTextView) findViewById(R.id.dropdown4);

        sumbitCourses = (AppCompatButton) findViewById(R.id.submitCourses);
    }



    private void settleAdapters() {
        acTextView1.setAdapter(adapter);
        acTextView2.setAdapter(adapter);
        acTextView3.setAdapter(adapter);
        acTextView4.setAdapter(adapter);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.submitCourses:
                //!!!! We need to create the classes to generate the schedules

                if(!inputValidation.isInputAutoCompleteTextFilled(acTextView1, "Select Course" )){
                    return;
                }
                if(!inputValidation.isInputAutoCompleteTextFilled(acTextView2, "Select Course" )){
                    return;
                }
                if(!inputValidation.isInputAutoCompleteTextFilled(acTextView3, "Select Course" )){
                    return;
                }
                if(!inputValidation.isInputAutoCompleteTextFilled(acTextView4, "Select Course" )){
                    return;
                }

                Intent generateSchedulesIntent = new Intent();

                List<ClassInfo> classInfos = new ArrayList<ClassInfo>();
                String[] courseNames = {
                        acTextView1.getText().toString().trim(),
                        acTextView2.getText().toString().trim(),
                        acTextView3.getText().toString().trim(),
                        acTextView4.getText().toString().trim()
                };

                for(String n:courseNames){
                    classInfos.add(databaseHelper.getDaysandTimes(n));
                }

                Toast.makeText(this, "Schedule will be generated", Toast.LENGTH_SHORT).show();



                break;
        }
    }



}
