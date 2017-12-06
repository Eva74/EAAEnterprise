package com.example.armle.ninjapath.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.armle.ninjapath.R;
import com.example.armle.ninjapath.adapter.SemesterCourseRecyclerAdapter;
import com.example.armle.ninjapath.model.Courses;
import com.example.armle.ninjapath.sql.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by armle on 11/24/2017.
 */

public class SemesterCourseListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private AppCompatActivity activity = SemesterCourseListActivity.this;
    Context context = SemesterCourseListActivity.this;
    private RecyclerView recyclerViewSemester;
    private ArrayList<Courses> courseList;
    private SemesterCourseRecyclerAdapter semesterCourseRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    //SearchView searchBox;
    //private ArrayList<Courses> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_course_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        initViews();
        initObjects();

        Intent intentThatStartedActivity = getIntent();
        if(intentThatStartedActivity.hasExtra("COURSE_NAME")){
            String crn = getIntent().getExtras().getString("CRN");
            String courseName = getIntent().getExtras().getString("COURSE_NAME");
            String professor = getIntent().getExtras().getString("PROFESSOR");
            int seats = getIntent().getExtras().getInt("SEATS");
            String location = getIntent().getExtras().getString("LOCATION");
            String startTime = getIntent().getExtras().getString("START_TIME");
            String endTime = getIntent().getExtras().getString("END_TIME");
            String days = getIntent().getExtras().getString("DAYS");
        }
        else{
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        recyclerViewSemester = (RecyclerView) findViewById(R.id.recyclerViewSemesterCourses);
    }

    private void initObjects() {
        courseList = new ArrayList<>();
        semesterCourseRecyclerAdapter = new SemesterCourseRecyclerAdapter(courseList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSemester.setLayoutManager(mLayoutManager);
        recyclerViewSemester.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSemester.setHasFixedSize(true);
        recyclerViewSemester.setAdapter(semesterCourseRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();
    }

    //added for query
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.beneficiary_search, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query){

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Courses> newList = new ArrayList<>();
        for (Courses course : courseList) {
            String name = course.getCourse_name().toLowerCase();
            if (name.contains(newText)) {
                newList.add(course);
            }
        }
        semesterCourseRecyclerAdapter.setFilter(newList);
        return true;
    }


    /*
        This method's purpose is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        //AsyncTask is used so that SQLite operation doesn't block the UI thread
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params){
                courseList.clear();
                courseList.addAll(databaseHelper.getAllCourses());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                semesterCourseRecyclerAdapter.notifyDataSetChanged();
            }

        }.execute();

    }
}
