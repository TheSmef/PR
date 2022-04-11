package com.example.sqllitesplash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class SplashScreen extends AppCompatActivity {
    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashcreen);
        Intent intent=new Intent(this,MainActivity.class);
        final int interval = 5000;
        Handler handler = new Handler();
        Runnable runnable = () -> {
            startActivity(intent);
        };

        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);
    }

}
