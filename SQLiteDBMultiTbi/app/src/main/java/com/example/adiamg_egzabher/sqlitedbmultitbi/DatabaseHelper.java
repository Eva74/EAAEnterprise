package com.example.adiamg_egzabher.sqlitedbmultitbi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adiamg-egzabher on 11/11/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // define variables
    public static final String DATABASE_NAME = "course.db";
    public static final String TABLE_NAME =  "courses_table";
    public static final String COL_1 = "CRN";
    public static final String COL_2 = "COURSE_NAME";
    public static final String COL_3 = "PROFESSOR";
    public static final String COL_4 = "UNITS";
    public static final String COL_5 = "SEATS";
    public static final String COL_6 = "LOCATION";
    public static final String COL_7 = "START_TIME";
    public static final String COL_8 = "END _TIME";
    public static final String COL_9 = "DAYS";

    public DatabaseHelper(Context context,) {
        super(context,  DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_NAME + "(CRN INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_NAME TEXT, PROFESSOR TEXT,SEATS TEXT)";

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS "  + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData ( String course_name, String professor, int units, int seats, int location, int start_time ,int end_time, int days){

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put (COL_2, course_name);
    contentValues.put (COL_3, professor);
    contentValues.put (COL_4, units);
    contentValues.put (COL_5, seats);
    contentValues.put (COL_6, location);
    contentValues.put (COL_7, start_time);
    contentValues.put (COL_8, end_time);
    contentValues.put (COL_9, days);

    long result = db.insert(TABLE_NAME, null ,ContentValues);
    if (result ==-1)
        return false;
    else
        return true;
}
}




