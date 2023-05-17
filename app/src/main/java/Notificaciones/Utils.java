package Notificaciones;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Utils {

    public static void setAlarm(int i, Long timestamp, Context ctx) {
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
        Intent alarmIntent1 = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent1;
        pendingIntent1 = PendingIntent.getBroadcast(ctx, i, alarmIntent1, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent1);
    }

    public static void setAlarm2(int i2, Long timestamp2, Context ctx2) {
        AlarmManager alarmManager2 = (AlarmManager) ctx2.getSystemService(ALARM_SERVICE);
        Intent alarmIntent2 = new Intent(ctx2, AlarmReceiver.class);
        PendingIntent pendingIntent2;
        pendingIntent2 = PendingIntent.getBroadcast(ctx2, i2 + 1, alarmIntent2, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent2.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager2.set(AlarmManager.RTC_WAKEUP, timestamp2, pendingIntent2);
    }
}