package com.example.rolegames;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TimerTask;

public class
SplashActivity extends AppCompatActivity {


    static int TIMEOUT_MILLIS = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        run();
    }

    public void run() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}