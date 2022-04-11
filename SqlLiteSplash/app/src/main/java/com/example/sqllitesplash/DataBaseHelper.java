package com.example.sqllitesplash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context)
    {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Users(login TEXT primary key, password TEXT, role TEXT)");
        sqLiteDatabase.execSQL("create Table News(title TEXT primary key, content TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop Table if exists Users");
        sqLiteDatabase.execSQL("drop Table if exists News");
    }

    public Boolean Insert_Users(String login, String password, String role) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = DB.insert("Users", null, contentValues);
        return result != -1;
    }
    public Boolean Insert_News(String title,String content)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        long result = DB.insert("News", null, contentValues);
        return result != -1;
    }
    public Boolean Update_News(String title,String content)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        long result = DB.update("News", contentValues, "title=?", new String[]{title});
        return result != -1;
    }
    public Boolean Delete_News(String title)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        long result = DB.delete("News", "title=?", new String[]{title});
        return result != -1;
    }
    public Cursor Select_Role(String login, String password)
    {
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        String[] Col=new String[]{login,password};
        return sqLiteDatabase.rawQuery("Select role from Users where login=? and password=?",Col);
    }
    public Cursor GetNews ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from News", null);
    }
    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserInfo", null);
    }
}



