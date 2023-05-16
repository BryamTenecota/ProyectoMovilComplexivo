package Notificaciones;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.movilprepracticasprofesional.R;

public class RebootServiceClass extends IntentService {

    public RebootServiceClass(String name) {
        super(name);
        startForeground(1, new Notification());
    }

    public RebootServiceClass() {
        super("RebootServiceClass");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String intentType = intent.getExtras().getString("caller");
        if (intentType != null && intentType.equals("RebootReceiver")) {
            SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            Utils.setAlarm(settings.getInt("alarmID", 0), settings.getLong("alarmTime", 0), this);
        }

        String intentType2 = intent.getExtras().getString("caller2");
        if (intentType2 != null && intentType2.equals("RebootReceiver2")) {
            SharedPreferences settings2 = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            Utils.setAlarm(settings2.getInt("alarmID2", 0), settings2.getLong("alarmTime2", 0), this);
        }
    }
}
