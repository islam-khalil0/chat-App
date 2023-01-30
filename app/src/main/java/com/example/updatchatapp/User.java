package com.example.updatchatapp;

import android.net.Uri;

public class User {

    private String userName ;
    private String email ;
    public  String uid ;
    private String password ;
    private Uri img_profile ;


    public User (Uri img_profile) {
        this.img_profile = img_profile;
    }

    public User() {
    }

    public User(String userName, String email, String uid, String password) {
        this.userName = userName;
        this.email = email;
        this.uid = uid;
        this.password = password;
    }

    public Uri getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(Uri img_profile) {
        this.img_profile = img_profile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
