package com.example.armle.ninjapath.activities;

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
import com.example.armle.ninjapath.model.Courses;
import com.example.armle.ninjapath.sql.DatabaseHelper;

public class SelectCourses extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView acTextView1;
    private AutoCompleteTextView acTextView2;
    private AutoCompleteTextView acTextView3;
    private AutoCompleteTextView acTextView4;
    private ArrayAdapter<String> adapter;

    private AppCompatButton sumbitCourses;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_select_courses);

        initViews();
        initListeners();
        loadTextViewData();
        settleAdapters();

    }

    private void initListeners() {

        sumbitCourses.setOnClickListener(this);
    }


    private void loadTextViewData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> courses = db.getAllCourseNames();
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

                break;
        }
    }



}
