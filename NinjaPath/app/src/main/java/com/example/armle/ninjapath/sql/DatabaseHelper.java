package com.example.armle.ninjapath.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.armle.ninjapath.model.ClassInfo;
import com.example.armle.ninjapath.model.Courses;
import com.example.armle.ninjapath.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by armle on 10/17/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    private static final int DATABASE_VERSION = 2;
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

    private String CREATE_COURSES_TABLE = "CREATE TABLE " + CoursesContract.CoursesEntry.TABLE_NAME + "("
            + CoursesContract.CoursesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CoursesContract.CoursesEntry.COL_CRN + " TEXT NOT NULL, "
            + CoursesContract.CoursesEntry.COL_COURSE_NAME + " TEXT NOT NULL, "
            + CoursesContract.CoursesEntry.COL_PROFESSOR + " TEXT NOT NULL, "
            + CoursesContract.CoursesEntry.COL_SEATS + " INTEGER NOT NULL, "
            + CoursesContract.CoursesEntry.COL_LOCATION + " TEXT NOT NULL, "
            + CoursesContract.CoursesEntry.COL_START_TIME + " TEXT NOT NULL, "
            + CoursesContract.CoursesEntry.COL_END_TIME + " TEXT NOT NULL, "
            + CoursesContract.CoursesEntry.COL_DAYS + " TEXT NOT NULL" + ")";

    private String DROP_COURSES_TABLE = "DROP TABLE IF EXISTS " + CoursesContract.CoursesEntry.TABLE_NAME;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_COURSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_COURSES_TABLE);
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
    public void addCourse(Courses courses){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CoursesContract.CoursesEntry.COL_CRN, courses.getCrn());
        values.put(CoursesContract.CoursesEntry.COL_COURSE_NAME, courses.getCourse_name());
        values.put(CoursesContract.CoursesEntry.COL_PROFESSOR, courses.getProfessor());
        values.put(CoursesContract.CoursesEntry.COL_SEATS, courses.getSeats());
        values.put(CoursesContract.CoursesEntry.COL_LOCATION, courses.getLocation());
        values.put(CoursesContract.CoursesEntry.COL_START_TIME, courses.getStart_time());
        values.put(CoursesContract.CoursesEntry.COL_END_TIME, courses.getEnd_time());
        values.put(CoursesContract.CoursesEntry.COL_DAYS, courses.getDays());

        db.insert(CoursesContract.CoursesEntry.TABLE_NAME, null, values);

        db.close();
    }

    public DatabaseHelper open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        DBHelper.close();
    }

    public List<Courses> getAllCourses(){
        String[] columns = {
                CoursesContract.CoursesEntry._ID,
                CoursesContract.CoursesEntry.COL_CRN,
                CoursesContract.CoursesEntry.COL_COURSE_NAME,
                CoursesContract.CoursesEntry.COL_PROFESSOR,
                CoursesContract.CoursesEntry.COL_SEATS,
                CoursesContract.CoursesEntry.COL_LOCATION,
                CoursesContract.CoursesEntry.COL_START_TIME,
                CoursesContract.CoursesEntry.COL_END_TIME,
                CoursesContract.CoursesEntry.COL_DAYS
        };

        //sorting orders

        String sortOrder = CoursesContract.CoursesEntry.COL_COURSE_NAME + " ASC";
        List<Courses> coursesList = new ArrayList<Courses>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CoursesContract.CoursesEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        //Traversing through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Courses course = new Courses();
                //Add the information from the columns
                course.setCrn(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_CRN)));
                course.setCourse_name(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_COURSE_NAME)));
                course.setProfessor(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_PROFESSOR)));
                course.setSeats(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_SEATS))));
                course.setLocation(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_LOCATION)));
                course.setStart_time(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_START_TIME)));
                course.setEnd_time(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_END_TIME)));
                course.setDays(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_DAYS)));

                //Adding course to list

                coursesList.add(course);

            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return coursesList;
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

    public ClassInfo getDaysandTimes(String courseName){
        ClassInfo classInfo = new ClassInfo();
        String[] columns = {
                CoursesContract.CoursesEntry.COL_START_TIME,
                CoursesContract.CoursesEntry.COL_END_TIME,
                CoursesContract.CoursesEntry.COL_DAYS};

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = CoursesContract.CoursesEntry.COL_COURSE_NAME + " = ?";

        String[] selectionArgs = {courseName};

        Cursor cursor = db.query(CoursesContract.CoursesEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst()){
            do{


                classInfo.setClassName(courseName);
                classInfo.setDay(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_DAYS)));
                classInfo.setStartTime(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_START_TIME)));
                classInfo.setEndTime(cursor.getString(cursor.getColumnIndex(CoursesContract.CoursesEntry.COL_END_TIME)));


            }while(cursor.moveToNext());
        }

        return classInfo;
    }



    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER, null);

        db.close();
        return res;
    }

    //list of course names
    public List<String> getAllCourseNames(){
        List<String> labels = new ArrayList<String>();

        //Select All Query
        String selectQuery = "SELECT " + CoursesContract.CoursesEntry.COL_COURSE_NAME + " FROM " + CoursesContract.CoursesEntry.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        //closing connection
        cursor.close();
        db.close();

        //returning labels

        return labels;

    }
}
