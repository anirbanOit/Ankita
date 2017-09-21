package com.oit.test.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SharedPref";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getLoggedIn()){
            redirectToDashboard();
        }

    }

    public void setLoggedIn(boolean loggedIn) {
        getSharedPreferences(PREFS_NAME,
                getApplicationContext().MODE_PRIVATE)
                .edit()
                .putBoolean(IS_LOGGED_IN, loggedIn)
                .apply();
    }


    public boolean getLoggedIn() {
        boolean loggedIn =  getSharedPreferences(PREFS_NAME,
                getApplicationContext().MODE_PRIVATE)
                .getBoolean(IS_LOGGED_IN, false);
        return loggedIn;
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        if (username.getText().toString().equalsIgnoreCase(AppConstant.userName) &&
                password.getText().toString().equalsIgnoreCase(AppConstant.password)){
            setLoggedIn(true);
            redirectToDashboard();

        }
    }

    private void redirectToDashboard() {
        Intent launchActivity= new Intent(MainActivity.this,DashboardActivity.class);
        startActivity(launchActivity);
        finish();
    }
}