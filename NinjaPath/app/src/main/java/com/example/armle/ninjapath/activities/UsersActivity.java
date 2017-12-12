package com.example.armle.ninjapath.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import com.example.armle.ninjapath.R;
/**
 * Created by armle on 10/17/2017.
 */

public class UsersActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceStates){
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_users);
        getSupportActionBar().hide();

        initViews();
        initListeners();
    }

    private void initViews() {
        createButton = (AppCompatButton) findViewById(R.id.buttonCreate);
    }

    private void initListeners() {
        createButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonCreate:
                Intent createSchedule = new Intent(getApplicationContext(), SelectCourses.class);
                startActivity(createSchedule);
                break;
        }

    }


}
