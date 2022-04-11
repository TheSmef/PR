package com.example.sqllitesplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity  extends AppCompatActivity {
    EditText login,password;
    DataBaseHelper databaseHelper;
    CheckBox check;
    Button reg;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        databaseHelper=new DataBaseHelper(this);
        Intent intent_back=new Intent(this,MainActivity.class);
        login=findViewById(R.id.LoginUser);
        password=findViewById(R.id.PasswordUser);
        check=findViewById(R.id.roleBox);
        reg=findViewById(R.id.ButReg);
        back = findViewById(R.id.ButBack);
        reg.setOnClickListener(view -> {
            Boolean check_db;
            if (check.isChecked())
            {
                check_db=databaseHelper.Insert_Users(login.getText().toString(),password.getText().toString(),"Administrator");
            }
            else
            {
                check_db=databaseHelper.Insert_Users(login.getText().toString(),password.getText().toString(),"User");
            }

            if (check_db)
            {
                Toast.makeText(getApplicationContext(),"Registration complete",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(getApplicationContext(),"Account with this login already exists",Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(view -> {
            startActivity(intent_back);
        });
    }
}