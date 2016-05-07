package net.kaeff.zazu.activity;

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

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;
import net.kaeff.zazu.activity.edit.EditTimeLogsActivity;
import net.kaeff.zazu.model.TimeLogType;

import java.util.Date;
import java.util.GregorianCalendar;

import zazu.kaeff.net.zazu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkForCrashes();
        checkForUpdates();
    }

    private void checkForCrashes() {
        CrashManager.register(this, "d8d973e221bf47cc8b0218f1e7936944");
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this, "d8d973e221bf47cc8b0218f1e7936944");
    }

    @NonNull
    private Intent createLogTimeIntendForSelectedDate(TimeLogType type) {
        DatePicker datePicker = (DatePicker) findViewById(R.id.logTimeDatePicker);
        Date date = new GregorianCalendar(
                datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()).getTime();

        Intent intent = new Intent(this, LogTimeActivity.class);
        intent.putExtra(LogTimeActivity.EXTRA_DATE, date);
        intent.putExtra(LogTimeActivity.EXTRA_LOG_TYPE, type);

        return intent;
    }

    public void onLogMorningClick(View view) {
        startActivity(createLogTimeIntendForSelectedDate(TimeLogType.MORNING));
    }

    public void onLogEveningClick(View view) {
        startActivity(createLogTimeIntendForSelectedDate(TimeLogType.EVENING));
    }

    public void showNotification(View view) {

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LogTimeActivity.class);
        stackBuilder.addNextIntent(createLogTimeIntendForSelectedDate(TimeLogType.MORNING));
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

    public void onShowTimeLogsClick(View view) {
        startActivity(new Intent(this, ShowTimeLogsActivity.class));
    }

    public void onEditTimeLogsClick(View view) {
        startActivity(new Intent(this, EditTimeLogsActivity.class));
    }

}
