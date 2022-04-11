package com.example.sqllitesplash;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {
    Button checknews; DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        checknews=findViewById(R.id.ButCheckNewsUser);
        dataBaseHelper=new DataBaseHelper(this);
        checknews.setOnClickListener(view -> {
            Cursor r=dataBaseHelper.GetNews();
            StringBuilder buffer=new StringBuilder();
            while (r.moveToNext())
            {
                buffer.append("Title: ").append(r.getString(0)).append("\n");
                buffer.append("Content: ").append(r.getString(1)).append("\n");
                buffer.append("\n");
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(NewsActivity.this);
            builder.setCancelable(true);
            builder.setTitle("List of News");
            builder.setMessage(buffer.toString());
            builder.show();
        });

    }
}
