package com.example.catapp;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO {
    private String id;
    private int highscore;
    FirebaseDatabase db;
    DatabaseReference users;

    public void setId(String id) {
        this.id = id;
    }

    public DAO() {
        db = FirebaseDatabase.getInstance("https://catapp-8482c-default-rtdb.europe-west1.firebasedatabase.app");
        users = db.getReference("Users");
    }

    public int getScoreFromDB() {

            users.child(id).child("highscore").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    highscore = Integer.parseInt(String.valueOf(task.getResult().getValue()));
                }
            });
            return highscore;
    }

    public void saveToDB(int score) {
        User user = new User();
        user.setMail(id);
        user.setHighscore(score);
        users.child(user.getMail()).setValue(user);
    }





}
