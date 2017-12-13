package com.example.armle.ninjapath.calendar;

/**
 * Created by armle on 12/12/2017.
 */

import android.content.Intent;

import com.alamkanak.weekview.WeekViewEvent;
import com.example.armle.ninjapath.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */

public class CalendarActivity extends BaseActivity {

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();


        //WeekViewEvent event;


        Intent intentThatStartedActivity = getIntent();
        if(intentThatStartedActivity.hasExtra("COURSE1")){
            String course1 = intentThatStartedActivity.getExtras().getString("COURSE1");
            String day1 = intentThatStartedActivity.getExtras().getString("DAY1");
            String sTime1 = intentThatStartedActivity.getExtras().getString("ST1");
            String eTime1 = intentThatStartedActivity.getExtras().getString("ET1");

            short startingHour = getHour(sTime1);
            short startingMinute = getMinute(sTime1);

            short endingHour = getHour(eTime1);
            short endingMinute = getMinute(eTime1);

            short hours = (short)(endingHour - startingHour);
            short minutes = (short)(endingMinute-startingMinute);

            if(startingMinute > endingMinute){
                hours -=1;
                minutes *= -1;
            }


            char[] days = breakUpDayString(day1);

            for(char d:days){

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR, startingHour);
                startTime.set(Calendar.MINUTE, startingMinute);
                startTime.set(Calendar.AM_PM, 1);
                startTime.set(Calendar.DAY_OF_WEEK, getDay(d));
                startTime.set(Calendar.MONTH, newMonth -1);
                startTime.set(Calendar.YEAR, newYear);

                Calendar endTime = (Calendar) startTime.clone();
                //endTime.add(Calendar.MINUTE, minutes);
                endTime.add(Calendar.HOUR, hours);

                endTime.set(Calendar.MONTH, newMonth -1);
                WeekViewEvent event = new WeekViewEvent(1, course1, startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_01));
                events.add(event);

            }

        }
        if(intentThatStartedActivity.hasExtra("COURSE2")){
            String course2 = intentThatStartedActivity.getExtras().getString("COURSE2");
            String day2 = intentThatStartedActivity.getExtras().getString("DAY2");
            String sTime2 = intentThatStartedActivity.getExtras().getString("ST2");
            String eTime2 = intentThatStartedActivity.getExtras().getString("ET2");

            short startingHour = getHour(sTime2);
            short startingMinute = getMinute(sTime2);

            short endingHour = getHour(eTime2);
            short endingMinute = getMinute(eTime2);


            char[] days = breakUpDayString(day2);

            for(char d:days){

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR, startingHour);
                startTime.set(Calendar.MINUTE, startingMinute);
                startTime.set(Calendar.AM_PM, 0);
                startTime.set(Calendar.DAY_OF_WEEK, getDay(d));
                startTime.set(Calendar.MONTH, newMonth -1);
                startTime.set(Calendar.YEAR, newYear);


                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, newMonth -1);
                WeekViewEvent event = new WeekViewEvent(2, course2, startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_02));
                events.add(event);

            }

        }
        if(intentThatStartedActivity.hasExtra("COURSE3")){
            String course3 = intentThatStartedActivity.getExtras().getString("COURSE3");
            String day3 = intentThatStartedActivity.getExtras().getString("DAY3");
            String sTime3 = intentThatStartedActivity.getExtras().getString("ST3");
            String eTime3 = intentThatStartedActivity.getExtras().getString("ET3");

            short startingHour = getHour(sTime3);
            short startingMinute = getMinute(sTime3);

            short endingHour = getHour(eTime3);
            short endingMinute = getMinute(eTime3);

            char[] days = breakUpDayString(day3);

            for(char d:days){

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR, startingHour);
                startTime.set(Calendar.MINUTE, startingMinute);
                startTime.set(Calendar.AM_PM, 0);
                startTime.set(Calendar.DAY_OF_WEEK, getDay(d));
                startTime.set(Calendar.MONTH, newMonth -1);
                startTime.set(Calendar.YEAR, newYear);


                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, newMonth -1);
                WeekViewEvent event = new WeekViewEvent(3, course3, startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_03));
                events.add(event);



            }
        }
        if(intentThatStartedActivity.hasExtra("COURSE4")){
            String course4 = intentThatStartedActivity.getExtras().getString("COURSE4");
            String day4 = intentThatStartedActivity.getExtras().getString("DAY4");
            String sTime4 = intentThatStartedActivity.getExtras().getString("ST4");
            String eTime4 = intentThatStartedActivity.getExtras().getString("ET4");

            short startingHour = getHour(sTime4);
            short startingMinute = getMinute(sTime4);

            short endingHour = getHour(eTime4);
            short endingMinute = getMinute(eTime4);

            char[] days = breakUpDayString(day4);

            for(char d:days){

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR, startingHour);
                startTime.set(Calendar.MINUTE, startingMinute);
                startTime.set(Calendar.AM_PM, 0);
                startTime.set(Calendar.DAY_OF_WEEK, getDay(d));
                startTime.set(Calendar.MONTH, newMonth -1);
                startTime.set(Calendar.YEAR, newYear);


                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, newMonth -1);
                WeekViewEvent event = new WeekViewEvent(4, course4, startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_04));
                events.add(event);



            }
        }



        return events;
    }

    public short getHour(String timeInput){
        String[] time = breakUpTimeString(timeInput);

        short hour = Short.parseShort(time[0]);

        return hour;
    }

    public short getMinute(String timeInput){
        String[] time = breakUpTimeString(timeInput);
        short minute = Short.parseShort(time[1]);

        return minute;
    }


    public String[] breakUpTimeString(String timeInput){

        String time[] =  timeInput.split("[:,pm, am, AM, PM]");

        return time;
    }
    public char[] breakUpDayString(String day){

        return day.toCharArray();
    }
    public int getDay(char day){

        switch (day){
            case 'M':
                return 2;
            case 'T':
                return 3;
            case 'W':
                return 4;
            case 'R':
                return 5;
            case 'F':
                return 6;
        }

        return 0;
    }



}