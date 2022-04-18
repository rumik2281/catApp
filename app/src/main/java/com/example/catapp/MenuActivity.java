package com.example.catapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends Activity {
    Button ftcButton;
    SignInButton signButton;
    GoogleSignInClient nGoogleSignInClient;
    TextView googleName;
    FirebaseDatabase db;
    DatabaseReference users;
    Button regButton;
    int highscore;
    GoogleSignInAccount account;
    private int getHighscore() {
        return highscore;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    public void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            account = task.getResult(ApiException.class);
            System.out.println(account.toString());
            googleSignInOk(account);
        } catch(Exception e) {
            System.out.println(e.toString());
            googleSignInOk(null);
        }
    }

    public void googleSignInOk(GoogleSignInAccount account) {
        if (account == null) {
            googleName.setText("Error");
        } else {
            googleName.setText(account.getDisplayName());
            User user = new User();
            user.setMail(account.getId());
            user.setHighscore(3);
            users.child(user.getMail()).setValue(user);
        }
    }

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        db = FirebaseDatabase.getInstance("https://catapp-8482c-default-rtdb.europe-west1.firebasedatabase.app");
        users = db.getReference("Users");

        googleName = findViewById(R.id.nameText);
        signButton = (SignInButton) findViewById(R.id.sgnButton);
        regButton = (Button) findViewById(R.id.regButton);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        nGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = nGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 100);
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ftcButton = (Button) findViewById(R.id.ftcButton);
        Intent myIntent = new Intent(this, MainActivity.class);
        ftcButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                startActivity(myIntent);
            }
        });


        };
    }
