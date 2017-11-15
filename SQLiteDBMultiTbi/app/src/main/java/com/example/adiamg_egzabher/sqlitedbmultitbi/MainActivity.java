package com.example.adiamg_egzabher.sqlitedbmultitbi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
    DatabaseHelper myDB;
    EditText editCOURSE_NAME, editPROFESSOR, editUNITS, editSEATS,editLOCATION, editSTART_TIME,editEND_TIME,editDAYS;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DataBaseHelper(this);

        editCOURSE_NAME = (EditText)findViewbyID(R.id.editText_course_name);
        editPROFESSOR = (EditText)findViewbyID(R.id.editText_professor);
        editUNITS = (EditText)findViewByID(R.id.editText_units);
        editSEATS = (EditText)findViewByID(R.id.editText_seats);
        editLOCATION = (EditText)findViewByID(R.id.editText_location);
        editSTART_TIME = (EditText)findViewByID(R.id.editText_start_time);
        editEND_TIME = (EditText)findViewByID(R.id.editText_end_time);
        editDAYS = (EditText)findViewByID(R.id.editText_days);
        btnAddData = (Button)findViewByID(R.id.button_add);
        AddData();

    }
    public void AddData(){
        btnAddData.setOnClickListnener() {
            new View.OnClickListener)() {

            }
        }
    }
}
