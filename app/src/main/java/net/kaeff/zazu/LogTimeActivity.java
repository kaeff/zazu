package net.kaeff.zazu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

import zazu.kaeff.net.zazu.R;

public class LogTimeActivity extends AppCompatActivity {

    public static final String EXTRA_DATE = "date";
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        date = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
        String dateText = DateFormat.getDateFormat(this).format(date);

        TextView textHeadline = (TextView) findViewById(R.id.textHeadline);
        textHeadline.setText(dateText);
    }

    public void onLogTimeClick(View view) {
        saveEntry();
        printConfirmation();
        finish();
    }

    private LocalDateTime getSelectedTime() {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        return LocalDateTime.fromDateFields(date)
                .withHourOfDay(timePicker.getCurrentHour())
                .withMinuteOfHour(timePicker.getCurrentMinute());
    }

    private void saveEntry() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        TimeLog timeLog = new TimeLog(getSelectedTime(), TimeLog.Type.MORNING);
        databaseHelper.insert(timeLog);
    }

    private void printConfirmation() {
        String confirmation = "Arbeitsbeginn um " +
                DateTimeFormat.shortTime().print(getSelectedTime()) +
                " eingetragen";
        Toast.makeText(getApplicationContext(), confirmation, Toast.LENGTH_SHORT).show();
    }
}
