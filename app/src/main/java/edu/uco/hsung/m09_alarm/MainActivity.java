package edu.uco.hsung.m09_alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    private Chronometer chronometer;
    private Button setAlarmButton;

    private int ALARM_DURATION = 7;
    private int REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        setAlarmButton = (Button) findViewById(R.id.button_start);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

                // ALARM_DURATION seconds later
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, ALARM_DURATION);

                Intent intent = new Intent(MainActivity.this, MyAlarmReceiver.class);
                PendingIntent pendingIntent =
                        PendingIntent.getBroadcast(MainActivity.this,
                                REQUEST_ID, intent, PendingIntent.FLAG_ONE_SHOT);

                AlarmManager alarmManager =
                        (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                // Android version check
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(), pendingIntent);
                } else {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(), pendingIntent);
                }

                Toast.makeText(MainActivity.this,
                        "Alarm set for 7 sec!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
