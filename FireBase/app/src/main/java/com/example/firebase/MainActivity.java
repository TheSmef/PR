package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtPhone = findViewById(R.id.phone);
        EditText edtEmail = findViewById(R.id.email);
        EditText edtPassword = findViewById(R.id.password);
        EditText edtPasswordRepeat = findViewById(R.id.passwordRepeat);
        Button btn = findViewById(R.id.signup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordRepeat = edtPasswordRepeat.getText().toString();
                database = FirebaseDatabase.getInstance("https://fir-13ec3-default-rtdb.firebaseio.com/").getReference().child("CHELOVIKI");
                if (Utilits.checkEmailForValidity(email, password, passwordRepeat, phone)){
                    User user = new User(phone, email, password);
                    Log.println(Log.ASSERT, "1", "Прошёл");
                    database.child(phone).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            edtEmail.getText().clear();
                            edtPhone.getText().clear();
                            edtPassword.getText().clear();
                            edtPasswordRepeat.getText().clear();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}