package net.kaeff.zazu;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;

import zazu.kaeff.net.zazu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @NonNull
    private Intent createLogTimeIntendForSelectedDate() {
        DatePicker datePicker = (DatePicker) findViewById(R.id.logTimeDatePicker);
        Date date = new Date(datePicker.getCalendarView().getDate());

        Intent intent = new Intent(this, LogTimeActivity.class);
        intent.putExtra(LogTimeActivity.EXTRA_DATE, date);
        return intent;
    }

    public void pickDate(View view) {
        startActivity(createLogTimeIntendForSelectedDate());
    }

    public void showNotification(View view) {

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LogTimeActivity.class);
        stackBuilder.addNextIntent(createLogTimeIntendForSelectedDate());
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle("Zazu")
                .setContentText("Tap to log beginning of day")
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
