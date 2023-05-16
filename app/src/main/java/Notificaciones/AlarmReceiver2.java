package Notificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class AlarmReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Intent service2 = new Intent(context, NotificationServiceConvo.class);

            service2.setData(Uri.parse("custom://" + System.currentTimeMillis()));

            PendingIntent pendingIntent2 = PendingIntent.getService(context, 0, service2, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent2);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent2);
            } else {

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent2);
            }
        }
    }
