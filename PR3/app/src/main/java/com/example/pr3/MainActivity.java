package com.example.pr3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.VideoView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private int CAMERA_CAPTURE;
    private int VIDEO_CAPTURE;
    Button btn;
    ImageView img;
    VideoView videoView;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnTakePhoto);
        img = findViewById(R.id.img);
        videoView = findViewById(R.id.video);
        btn2 = findViewById(R.id.btnVideo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(captureIntent, CAMERA_CAPTURE);
                }
                catch (RemoteViews.ActionException e)
                {

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(captureIntent, VIDEO_CAPTURE);
                }
                catch (RemoteViews.ActionException e)
                {

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        try {
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(thumbnailBitmap);
        }
        catch (Exception e)
        {

        }
        try {
            videoView.setVideoURI(data.getData());
            videoView.setZOrderOnTop(true);
            videoView.start();
        }
        catch (Exception e)
        {

        }

    }
}