package com.example.catapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Map;

public class TableActivity extends AppCompatActivity {

    TextView[] name = new TextView[3];
    TextView[] highScore =  new TextView[3];
    TextView[] ids = new TextView[3];
    int i = 0;

    TextView name1;
    TextView name2;
    TextView name3;
    TextView name4;
    TextView name5;

    TextView highScore1;
    TextView highScore2;
    TextView highScore3;
    TextView highScore4;
    TextView highScore5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.results_main);

        User[] topUsers = new User[3];


        ids[0] = (TextView) findViewById(R.id.fId);
        ids[1] = (TextView) findViewById(R.id.sId);
        ids[2] = (TextView) findViewById(R.id.tId);

        name[0] = (TextView) findViewById(R.id.fName);
        name[1] = (TextView) findViewById(R.id.sName);
        name[2] = (TextView) findViewById(R.id.tName);


        highScore[0] = (TextView) findViewById(R.id.fScore);
        highScore[1] = (TextView) findViewById(R.id.sScore);
        highScore[2] = (TextView) findViewById(R.id.tScore);


        readUsersFromBd(topUsers);
        System.out.println(Arrays.toString(topUsers));



    }

    public void readUsersFromBd(User[] topUsers) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users");

        users.orderByChild("highscore").limitToLast(5).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String, User> currUser = (Map<String, User>) snapshot.getValue();
                System.out.println(currUser);
                ids[i].setText(((String.valueOf(i))));
                name[i].setText(((String.valueOf(currUser.get("name")))));
                highScore[i].setText(((String.valueOf(currUser.get("highscore")))));
                i++;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }
}
