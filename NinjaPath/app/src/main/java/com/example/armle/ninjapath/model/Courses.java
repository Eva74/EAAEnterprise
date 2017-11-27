package com.example.armle.ninjapath.model;

/**
 * Created by armle on 11/19/2017.
 */

public class Courses {
    private int id;
    private String crn;
    private String course_name;
    private String professor;
    private int seats;
    private String location;
    private String start_time;
    private String end_time;
    private String days;

    public void setId(int id){this.id = id;}
    public int getId(){return id;}
    public void setCrn(String crn){
        this.crn = crn;
    }
    public String getCrn(){
        return crn;
    }
    public void setCourse_name(String course_name){
        this.course_name = course_name;
    }
    public String getCourse_name(){
        return course_name;
    }
    public void setProfessor(String professor){
        this.professor = professor;
    }
    public String getProfessor(){
        return professor;
    }
    public void setSeats(int seats){ this.seats = seats;}
    public int getSeats(){return seats; }
    public void setLocation(String location){this.location = location;}
    public String getLocation(){return location; }
    public void setStart_time(String start_time){this.start_time = start_time;}
    public String getStart_time(){return start_time;}
    public void setEnd_time(String end_time){this.end_time = end_time;}
    public String getEnd_time(){return end_time;}
    public void setDays(String days){this.days = days;}
    public String getDays(){return this.days = days;}




}
