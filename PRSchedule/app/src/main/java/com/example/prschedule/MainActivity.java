package com.example.prschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> model = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(this, model);
        recyclerView.setAdapter(adapter);
    }
    public  void setData(){
        model.add(new Model("Понедельник", "Программирование", "none", "Особое программирование", "none", "Очень особое программирование", "Не очень особое программирование", "-", "none", "-", "none"));
        model.add(new Model("Вторник", "Программирование", "Не программирование", "Физ-ра", "none", "-", "none", "-", "none", "-", "none"));
        model.add(new Model("Среда", "-", "none", "-", "none", "-", "none", "-", "none", "РМП", "РМП"));
        model.add(new Model("Четверг", "-", "none", "-", "none", "-", "none", "Веб", "Отсутствие Веба", "-", "-"));
        model.add(new Model("Пятница", "Программирование", "Физ-ра", "Численные методы", "Дискретная математика", "Базы данных", "Технологии разработки ПО", "Веб", "Отсутствие Веба", "Системное программирование", "Базы данных"));
    }
}