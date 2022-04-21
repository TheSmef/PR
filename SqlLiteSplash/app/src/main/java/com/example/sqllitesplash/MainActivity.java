package com.example.sqllitesplash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import androidx.biometric.BiometricPrompt;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private Button authBut;
    private Button regBut;
    private EditText login,password;
    private DataBaseHelper dataBaseHelper;

    private Button scanBut;
    private Executor exec;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper=new DataBaseHelper(this);
        scanBut = findViewById(R.id.ButScan);
        exec = ContextCompat.getMainExecutor(this);
        authBut=findViewById(R.id.ButAuth);
        login=findViewById(R.id.LoginUserAuth);
        password=findViewById(R.id.PasswordUserAuth);
        regBut = findViewById(R.id.ButReg);
        Intent intent_admin=new Intent(this,AdminActivity.class);
        Intent intent_user=new Intent(this,NewsActivity.class);
        Intent intent_reg =new Intent(this,RegistrationActivity.class);
        biometricPrompt = new BiometricPrompt(MainActivity.this, exec, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.e("ErrorAuth", errString.toString());
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e("ErrorAuth", "Failed");
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
                startActivity(intent_user);
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Auth")
                .setSubtitle("Scan")
                .setNegativeButtonText("Back")
                .build();
        scanBut.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
        authBut.setOnClickListener(view -> {
            Cursor r= dataBaseHelper.Select_Role(login.getText().toString(),password.getText().toString());
            String role="";
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