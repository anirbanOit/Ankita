package com.oit.test.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DashboardActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SharedPref";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setLoggedIn(true);

        boolean isLoggedIn = getLoggedIn();
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
}