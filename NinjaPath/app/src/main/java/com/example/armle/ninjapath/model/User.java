package com.example.armle.ninjapath.model;

/**
 * Created by armle on 10/16/2017.
 */

public class User {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String major;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName(){
        return first_name;
    }

    public void setFirstName(String name){
        this.first_name = name;
    }

    public String getLastName(){
        return last_name;
    }

    public void setLastName(String last_name){
        this.last_name = last_name;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getMajor(){
        return major;
    }

    public void setMajor(String major){
        this.major = major;
    }

}

