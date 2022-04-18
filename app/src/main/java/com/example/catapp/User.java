package com.example.catapp;

public class User {
    String mail;
    int highscore;

    public User() {}

    public User(String mail) {
        this.mail = mail;
        this.highscore = 0;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
