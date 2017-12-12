package com.example.armle.ninjapath.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
/**
 * Created by armle on 10/17/2017.
 */

public class InputValidation {
    private Context context;

    public InputValidation(Context context){
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();

        if(value.isEmpty()){
            textInputLayout.setError(message);
            hideKeyBoardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputAutoCompleteTextFilled(AutoCompleteTextView autoCompleteTextView, String message){
        String value = autoCompleteTextView.getText().toString().trim();

        if(value.isEmpty()){
            autoCompleteTextView.setError(message);
            hideKeyBoardFrom(autoCompleteTextView);
            return false;
        }
        else{
            autoCompleteTextView.setError(null);
        }
        return true;
    }


    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();

        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            hideKeyBoardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    public boolean isInputTextMatches(TextInputEditText textInputEditText, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();

        if(!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyBoardFrom(textInputEditText2);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void hideKeyBoardFrom(View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromInputMethod(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
