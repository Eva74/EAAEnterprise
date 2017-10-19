package com.example.armle.ninjapath.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.armle.ninjapath.model.User;

/**
 * Created by armle on 10/17/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NinjaPath.db";
    private static final String TABLE_USER = "user";


    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_USER_EMAIL =  "user_email";
    private static final String COLUMN_USER_MAJOR = "user_major";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " TEXT PRIMARY KEY, "
            + COLUMN_FIRST_NAME + " TEXT, "
            + COLUMN_LAST_NAME + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT, "
            + COLUMN_USER_MAJOR + " TEXT, "
            + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_ID, user.getId());
        values.put(COLUMN_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_LAST_NAME, user.getLastName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_MAJOR, user.getMajor());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);

        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUser(String email, String password){

        String[] columns = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = { email, password};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER, null);

        db.close();
        return res;
    }

}
