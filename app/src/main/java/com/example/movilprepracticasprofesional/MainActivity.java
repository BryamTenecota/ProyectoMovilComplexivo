package com.example.movilprepracticasprofesional;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Notificaciones.AlarmReceiver;

@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity {
    private TextView notificationsTime;
    private int alarmID = 1;
    private SharedPreferences settings;
    private boolean estadoAceptacionPPP = true;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(getString(R.string.title), Context.MODE_PRIVATE);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, alarmID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (estadoAceptacionPPP) {
            Calendar today = Calendar.getInstance();
            int hour = today.get(Calendar.HOUR_OF_DAY);
            int minute = today.get(Calendar.MINUTE) + 1;
            if (minute >= 60) {
                minute = 0;
                hour += 1;
            }
            today.set(Calendar.HOUR_OF_DAY, hour);
            today.set(Calendar.MINUTE, minute);
            today.set(Calendar.SECOND, 0);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), alarmIntent);
        }

    }





























    public void logueado(View view) {
        Intent vntmenustudiante=new Intent(MainActivity.this,bienvenida.class);
        startActivity(vntmenustudiante);
    }





}