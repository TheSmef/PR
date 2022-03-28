package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,phone,dateOfBirth;
    Button insert, select,delete,update;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.txtName);
        phone=findViewById(R.id.txtNumber);
        dateOfBirth=findViewById(R.id.txtDate);
        insert=findViewById(R.id.btnInsert);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        select=findViewById(R.id.btnSelect);
        databaseHelper=new DatabaseHelper(this);
        insert.setOnClickListener(view ->{
            Boolean checkInsertData=databaseHelper.Insert(name.getText().toString(),phone.getText().toString(),
                    dateOfBirth.getText().toString());
            if (checkInsertData)
            {
                Toast.makeText(getApplicationContext(),"Данные успешно добавлены", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });
        update.setOnClickListener(view ->{
            Boolean checkUpdateData=databaseHelper.Update(name.getText().toString(),phone.getText().toString(),
                    dateOfBirth.getText().toString());
            if (checkUpdateData)
            {
                Toast.makeText(getApplicationContext(),"Данные успешно обновлены", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });
        delete.setOnClickListener(view ->{
            Boolean checkDeleteData=databaseHelper.Delete(phone.getText().toString());
            if (checkDeleteData)
            {
                Toast.makeText(getApplicationContext(),"Данные успешно удалены", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });
        select.setOnClickListener(view ->{
            Cursor res=databaseHelper.getData();
            if(res.getCount()==0)
            {
                Toast.makeText(MainActivity.this,"Нет данных",Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder buffer=new StringBuilder();
            while (res.moveToNext())
            {
                buffer.append("Имя: ").append(res.getString(0)).append("\n");
                buffer.append("Тел. Номер: ").append(res.getString(1)).append("\n");
                buffer.append("Дата рождения: ").append(res.getString(2)).append("\n\n");
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Данные пользователя");
            builder.setMessage(buffer.toString());
            builder.show();
        });
    }
}