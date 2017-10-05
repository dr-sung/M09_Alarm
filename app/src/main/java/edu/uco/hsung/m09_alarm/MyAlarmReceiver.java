package edu.uco.hsung.m09_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
     in ManifestFile:
     <receiver android:name=".AlarmReceiver" android:process=":remote" />

     android:process=":remote" means this receiver will run in a different process
     i.e., in a different VM
     By doing this, this process can keep running in its own process
     after the main application is shutdown.
 */

public class MyAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context.getApplicationContext(), NewActivity.class);
        context.getApplicationContext().startActivity(i);

    }
}
