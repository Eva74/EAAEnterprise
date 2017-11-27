package com.example.armle.ninjapath.sql;

import android.provider.BaseColumns;

/**
 * Created by armle on 11/26/2017.
 */

public class CoursesContract {

    public static final class CoursesEntry implements BaseColumns{

        public static final String TABLE_NAME = "courses_table";
        public static final String COL_CRN = "crn";
        public static final String COL_COURSE_NAME = "course_name";
        public static final String COL_PROFESSOR = "professor";
        public static final String COL_SEATS = "seats";
        public static final String COL_LOCATION = "location";
        public static final String COL_START_TIME = "start_time";
        public static final String COL_END_TIME = "end_time";
        public static final String COL_DAYS = "days";


    }

}
