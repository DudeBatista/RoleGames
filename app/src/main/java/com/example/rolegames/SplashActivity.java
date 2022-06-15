package com.example.rolegames;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TimerTask;

public class
SplashActivity extends AppCompatActivity {

    //ProgressBar pb;
    int counter = 0;

    static int TIMEOUT_MILLIS = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        run();
    }
    /*public void prog(){
        pb = (ProgressBar)findViewById(R.id.pb);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);

                if (counter=100)
                    t.cancel();
            }
        };
        t.schedule(tt, delay:0, period:100);
    }*/
    public void run() {

        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
    }
}