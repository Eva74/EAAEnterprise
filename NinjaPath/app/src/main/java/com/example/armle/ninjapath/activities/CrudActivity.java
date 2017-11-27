package com.example.armle.ninjapath.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.armle.ninjapath.R;
import com.example.armle.ninjapath.helper.InputValidation;
import com.example.armle.ninjapath.model.Courses;
import com.example.armle.ninjapath.sql.DatabaseHelper;

/**
 * Created by armle on 11/24/2017.
 */

public class CrudActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = CrudActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutCRN;
    private TextInputLayout textInputLayoutCourseName;
    private TextInputLayout textInputLayoutProfessor;
    private TextInputLayout textInputLayoutSeats;
    private TextInputLayout textInputLayoutLocation;
    private TextInputLayout textInputLayoutStartTime;
    private TextInputLayout textInputLayoutEndTime;
    private TextInputLayout textInputLayoutDays;

    private TextInputEditText textInputEditTextCRN;
    private TextInputEditText textInputEditTextCourseName;
    private TextInputEditText textInputEditTextProfessor;
    private TextInputEditText textInputEditTextSeats;
    private TextInputEditText textInputEditTextLocation;
    private TextInputEditText textInputEditTextStartTime;
    private TextInputEditText textInputEditTextEndTime;
    private TextInputEditText textInputEditTextDays;

    private AppCompatButton appCompatButtonAddToCourses;
    private AppCompatTextView appCompatTextViewSemesterCourseList;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper; 
    private Courses course; 

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        getSupportActionBar().hide();
        
        initViews(); 
        initObjects(); 
        initListeners(); 
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutCRN = (TextInputLayout) findViewById(R.id.textInputLayoutCRN);
        textInputLayoutCourseName = (TextInputLayout) findViewById(R.id.textInputLayoutCourseName);
        textInputLayoutProfessor = (TextInputLayout) findViewById(R.id.textInputLayoutProfessor);
        textInputLayoutSeats = (TextInputLayout) findViewById(R.id.textInputLayoutSeats);
        textInputLayoutLocation = (TextInputLayout) findViewById(R.id.textInputLayoutLocation);
        textInputLayoutStartTime = (TextInputLayout) findViewById(R.id.textInputLayoutStartTime);
        textInputLayoutEndTime = (TextInputLayout) findViewById(R.id.textInputLayoutEndTime);
        textInputLayoutDays = (TextInputLayout) findViewById(R.id.textInputLayoutDays);

        textInputEditTextCRN = (TextInputEditText) findViewById(R.id.textInputEditTextCRN);
        textInputEditTextCourseName = (TextInputEditText) findViewById(R.id.textInputEditTextCourseName);
        textInputEditTextProfessor = (TextInputEditText) findViewById(R.id.textInputEditTextProfessor);
        textInputEditTextSeats = (TextInputEditText) findViewById(R.id.textInputEditTextSeats);
        textInputEditTextLocation = (TextInputEditText) findViewById(R.id.textInputEditTextLocation);
        textInputEditTextStartTime = (TextInputEditText) findViewById(R.id.textInputEditTextStartTime);
        textInputEditTextEndTime = (TextInputEditText) findViewById(R.id.textInputEditTextEndTime);
        textInputEditTextDays = (TextInputEditText) findViewById(R.id.textInputEditTextDays);

        appCompatButtonAddToCourses = (AppCompatButton) findViewById(R.id.appCompatAddToCourses);
        appCompatTextViewSemesterCourseList = (AppCompatTextView) findViewById(R.id.appCompatTextViewShowList);


    }

    private void initListeners() {
        appCompatButtonAddToCourses.setOnClickListener(this);
        appCompatTextViewSemesterCourseList.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        course = new Courses();
        
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatAddToCourses:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewShowList:
                Intent coursesIntent = new Intent(activity,SemesterCourseListActivity.class);
                coursesIntent.putExtra("CRN", textInputEditTextCRN.getText().toString().trim());
                coursesIntent.putExtra("COURSE_NAME", textInputEditTextCourseName.getText().toString().trim());
                coursesIntent.putExtra("PROFESSOR", textInputEditTextProfessor.getText().toString().trim());
                coursesIntent.putExtra("SEATS", textInputEditTextSeats.getText().toString().trim());
                coursesIntent.putExtra("LOCATION", textInputEditTextLocation.getText().toString().trim());
                coursesIntent.putExtra("START_TIME", textInputEditTextStartTime.getText().toString().trim());
                coursesIntent.putExtra("END_TIME", textInputEditTextEndTime.getText().toString().trim());
                coursesIntent.putExtra("DAYS", textInputEditTextDays.getText().toString().trim()); 
                
                emptyEditText(); 
                startActivity(coursesIntent); 

                break;
        }
    }

    private void emptyEditText() {
        textInputEditTextCRN.setText(null);
        textInputEditTextCourseName.setText(null);
        textInputEditTextProfessor.setText(null);
        textInputEditTextSeats.setText(null);
        textInputEditTextLocation.setText(null);
        textInputEditTextStartTime.setText(null);
        textInputEditTextEndTime.setText(null);
        textInputEditTextDays.setText(null);
    }

    private void postDataToSQLite() {

        if(!inputValidation.isInputEditTextFilled(textInputEditTextCRN,textInputLayoutCRN, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextCourseName,textInputLayoutCourseName, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextProfessor,textInputLayoutProfessor, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextSeats,textInputLayoutSeats, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextLocation,textInputLayoutLocation, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextStartTime,textInputLayoutStartTime, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextEndTime,textInputLayoutEndTime, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextDays,textInputLayoutDays, getString(R.string.error_message_name))){
            return;
        }
        /* need to implement text input validation later*/

        course.setCrn(textInputEditTextCRN.getText().toString().trim());
        course.setCourse_name(textInputEditTextCourseName.getText().toString().trim());
        course.setProfessor(textInputEditTextProfessor.getText().toString().trim());
        course.setSeats(Integer.parseInt(textInputEditTextSeats.getText().toString().trim()));
        course.setLocation(textInputEditTextLocation.getText().toString().trim());
        course.setStart_time(textInputEditTextStartTime.getText().toString().trim());
        course.setEnd_time(textInputEditTextEndTime.getText().toString().trim());
        course.setDays(textInputEditTextDays.getText().toString().trim());

        databaseHelper.addCourse(course);



        //Snack bar to show successs message that record saved successfully
        Intent coursesIntent = new Intent(activity, SemesterCourseListActivity.class);
        Toast.makeText(this, "Course Added Successfully", Toast.LENGTH_SHORT).show();

        coursesIntent.putExtra("CRN", textInputEditTextCRN.getText().toString().trim());
        coursesIntent.putExtra("COURSE_NAME", textInputEditTextCourseName.getText().toString().trim());
        coursesIntent.putExtra("PROFESSOR", textInputEditTextProfessor.getText().toString().trim());
        coursesIntent.putExtra("SEATS", textInputEditTextSeats.getText().toString().trim());
        coursesIntent.putExtra("LOCATION", textInputEditTextLocation.getText().toString().trim());
        coursesIntent.putExtra("START_TIME", textInputEditTextStartTime.getText().toString().trim());
        coursesIntent.putExtra("END_TIME", textInputEditTextEndTime.getText().toString().trim());
        coursesIntent.putExtra("DAYS", textInputEditTextDays.getText().toString().trim());

        emptyEditText();
        startActivity(coursesIntent);
    }


}
