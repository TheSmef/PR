package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static  final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int hasREadContactsPermition = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasREadContactsPermition == PackageManager.PERMISSION_GRANTED)
        {
            READ_CONTACTS_GRANTED = true;
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_CONTACTS);

            if(READ_CONTACTS_GRANTED)
            {
                getContacts();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_CONTACTS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                READ_CONTACTS_GRANTED = true;
            }
        }
        if(READ_CONTACTS_GRANTED){
            getContacts();
        }
        else
        {
            Toast.makeText(this, "Требуеться установить разрешение",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void getContacts() {
//        ContentResolver contentResolver = getContentResolver(); // у меня не получилось из этого сделать это
//        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//        ArrayList<String> contacts = new ArrayList<>();
//        if (cursor != null){
//            while (cursor.moveToNext()){
//                @SuppressLint("Range")
//                 String contact = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                contacts.add(contact);
//            }
//            cursor.close();
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
//        ListView contentList = findViewById(R.id.listview);
//        contentList.setAdapter(adapter);

        File root = Environment.getRootDirectory();
        ArrayList<String> contacts = new ArrayList<>();
        String name = new String();
        for (File f : root.listFiles()) {
                name = f.getName();
            contacts.add(name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        ListView contentList = findViewById(R.id.listview);
        contentList.setAdapter(adapter);
    }


}