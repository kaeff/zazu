package net.kaeff.zazu.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.kaeff.zazu.persistence.DatabaseHelper;
import net.kaeff.zazu.model.TimeLog;

import java.util.List;

import zazu.kaeff.net.zazu.R;

public class ShowTimeLogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time_logs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        printTimeLogs(databaseHelper.queryRecords());
    }

    private void printTimeLogs(List<TimeLog> timeLogs) {
        LinearLayout timeLogsContainer = (LinearLayout) findViewById(R.id.timeLogsContainer);
        for (TimeLog timeLog : timeLogs) {
            TextView textView = new TextView(this);
            textView.setText(printTimeLog(timeLog));
            timeLogsContainer.addView(textView);
        }
    }

    private String printTimeLog(TimeLog timeLog) {
        return timeLog.toString();
    }
}
