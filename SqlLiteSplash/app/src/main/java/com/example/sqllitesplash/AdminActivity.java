package com.example.sqllitesplash;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    Button editnews,checknews; DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        editnews=findViewById(R.id.ButEditNews);
        checknews=findViewById(R.id.ButCheckNews);
        dataBaseHelper=new DataBaseHelper(this);
        Intent intent_check=new Intent(this,NewsActivity.class);
        Intent intent_edit=new Intent(this,NewsEditActivity.class);
        editnews.setOnClickListener(view -> {
            startActivity(intent_edit);
        });
        checknews.setOnClickListener(view -> {
            Cursor r=dataBaseHelper.GetNews();
            StringBuilder buffer=new StringBuilder();
            while (r.moveToNext())
            {
                buffer.append("Title: ").append(r.getString(0)).append("\n");
                buffer.append("Content: ").append(r.getString(1)).append("\n");
                buffer.append("\n");
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(AdminActivity.this);
            builder.setCancelable(true);
            builder.setTitle("List of News");
            builder.setMessage(buffer.toString());
            builder.show();
        });
    }
}
