package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView editText;
    Button btnStartTimer;
    Button btnTime, btnDate;
    Calendar dateTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edtSeconds);
        btnStartTimer = findViewById(R.id.btnStartTimer);
        btnStartTimer.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Alarm.class);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0));

        });

        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        editText.setText(DateUtils.formatDateTime(this, dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDate, int minute) {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDate);
                dateTime.set(Calendar.MINUTE, minute);
                editText.setText(DateUtils.formatDateTime(getApplicationContext(), dateTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
            }
        };

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mounthOfYear, int dayOfMonth) {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, mounthOfYear);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                editText.setText(DateUtils.formatDateTime(getApplicationContext(),
                        dateTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR |
                                DateUtils.FORMAT_SHOW_TIME));
            }
        };

        btnDate.setOnClickListener(view -> new DatePickerDialog(MainActivity.this, d, dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show());

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this, t, dateTime.get(Calendar.HOUR_OF_DAY),
                        dateTime.get(Calendar.MINUTE), true).show();
            }
        });
    }
}