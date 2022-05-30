package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt1, txt2, txt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        final Animation animationRotateCorner = AnimationUtils.loadAnimation(this, R.anim.rotate);
        txt1.startAnimation(animationRotateCorner);
        txt2 = findViewById(R.id.txt2);
        final Animation animationTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);
        txt2.startAnimation(animationTranslate);
        txt3 = findViewById(R.id.txt3);
        final Animation animationFade = AnimationUtils.loadAnimation(this, R.anim.fade);
        txt3.startAnimation(animationFade);
    }
}