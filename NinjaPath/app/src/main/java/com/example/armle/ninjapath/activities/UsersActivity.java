package com.example.armle.ninjapath.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.armle.ninjapath.R;
/**
 * Created by armle on 10/17/2017.
 */

public class UsersActivity extends AppCompatActivity {

    //private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceStates){
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_users);
        getSupportActionBar().hide();
        /*textViewName = (TextView) findViewById(R.id.text1);
        String namefromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + namefromIntent);*/
    }


}
