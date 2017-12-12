package com.example.armle.ninjapath.activities;

import java.util.List;
import java.util.ResourceBundle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.armle.ninjapath.R;
import com.example.armle.ninjapath.model.Courses;
import com.example.armle.ninjapath.sql.DatabaseHelper;

public class SelectCourses extends AppCompatActivity implements OnItemSelectedListener {

    //Spinner element
    Spinner spinner;

    //Add button
    Button btnAdd;

    //Input text
    EditText inputLabel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_select_courses);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        // ----links elements in xml to java file

        //Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        //add button
        btnAdd = (Button) findViewById(R.id.btn_add);

        //new label input field
        inputLabel = (EditText) findViewById(R.id.input_label);

        //Spinner click listener
        spinner.setOnItemSelectedListener(this);

        //Loading spinner data from database
        loadSpinnerData();

        /**
         * Add new label button click listener
         */
        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
                    public void onClick(View arg0){
                        String label = inputLabel.getText().toString();

                        if(label.trim().length() > 0){
                            //database handler
                            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                            //inserting new label into database
                            //db.insertLabel(label);

                            //making input filed text to blank
                            inputLabel.setText("");

                            //Hiding the keyboard         //unsure of following line
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);

                            // loading spinner with newly added data
                            loadSpinnerData();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Please enter label name", Toast.LENGTH_SHORT).show();

                        }
            }
        });
    }

    /**
     * Function to load the spinner data from SQLite database
     */
    private void loadSpinnerData(){
        //database handler
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        //Spinner Drop down elements
        List<String> labels = db.getAllCourseNames();  //Get all COURSES???

        //Creating adapter for spinner      //courses instead of labels???
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        //Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        //On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        //Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
                                      //Should this be + courses ^^  ?????
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){

    }

}
