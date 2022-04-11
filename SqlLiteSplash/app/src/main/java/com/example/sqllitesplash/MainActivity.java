package com.example.sqllitesplash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button authBut;
    Button regBut;
    EditText login,password;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper=new DataBaseHelper(this);
        authBut=findViewById(R.id.ButAuth);
        login=findViewById(R.id.LoginUserAuth);
        password=findViewById(R.id.PasswordUserAuth);
        regBut = findViewById(R.id.ButReg);
        Intent intent_admin=new Intent(this,AdminActivity.class);
        Intent intent_user=new Intent(this,NewsActivity.class);
        Intent intent_reg =new Intent(this,RegistrationActivity.class);
        authBut.setOnClickListener(view -> {
            Cursor r= dataBaseHelper.Select_Role(login.getText().toString(),password.getText().toString());
            String role="";
            StringBuilder buffer=new StringBuilder();
            r.moveToNext();
            role=r.getString(0);
            if(role.equals("Administrator"))
            {
                startActivity(intent_admin);
            }
            if(role.equals("User"))
            {
                startActivity(intent_user);
            }
        });
        regBut.setOnClickListener( view -> {
            startActivity(intent_reg);
        });
    }
}