package Notificaciones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class MyRebootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, RebootServiceClass.class);
        serviceIntent.putExtra("caller", "RebootReceiver");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }

        Intent serviceIntent2 = new Intent(context, RebootServiceClass.class);
        serviceIntent2.putExtra("caller2", "RebootReceiver2");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent2);
        } else {
            context.startService(serviceIntent2);
        }
    }
}
