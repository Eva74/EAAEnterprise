package com.example.armle.ninjapath.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

/**
 * Created by armle on 10/16/2017.
 */
import com.example.armle.ninjapath.R;
import com.example.armle.ninjapath.helper.InputValidation;
import com.example.armle.ninjapath.model.User;
import com.example.armle.ninjapath.sql.DatabaseHelper;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutId;
    private TextInputLayout textInputLayoutMajor;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextLastName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextMajor;
    private TextInputEditText textInputEditTextId;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;


    private AppCompatButton appCompatButton;
    private AppCompatTextView appCompatTextView;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutMajor = (TextInputLayout) findViewById(R.id.textInputLayoutMajor);
        textInputLayoutId = (TextInputLayout) findViewById(R.id.textInputLayoutStudentID);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextLastName = (TextInputEditText) findViewById(R.id.textInputEditTextLastName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextMajor = (TextInputEditText) findViewById(R.id.textInputEditTextMajor);
        textInputEditTextId = (TextInputEditText) findViewById(R.id.textInputEditTextStudentID);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButton = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextView = (AppCompatTextView) findViewById(R.id.textViewLinkLogin);

    }

    private void initListeners(){
        appCompatButton.setOnClickListener(this);
        appCompatTextView.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.textViewLinkLogin:
                finish();
                break;
        }
    }

    private void postDataToSQLite(){
        if(!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextId, textInputLayoutId, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextMajor, textInputLayoutMajor, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword, getString(R.string.error_password_match))){
            return;
        }
        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){

            user.setFirstName(textInputEditTextFirstName.getText().toString().trim());
            user.setLastName(textInputEditTextLastName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setId(textInputEditTextId.getText().toString().trim());
            user.setMajor(textInputEditTextMajor.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }
        else{
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextFirstName.setText(null);
        textInputEditTextLastName.setText(null);
        textInputEditTextId.setText(null);
        textInputEditTextMajor.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);


    }

}
