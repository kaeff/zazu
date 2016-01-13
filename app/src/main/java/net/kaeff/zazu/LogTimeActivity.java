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
    public static final String EXTRA_LOG_TYPE = "log_type";
    private Date date;
    private TimeLog.Type type;

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
        type = (TimeLog.Type) getIntent().getSerializableExtra(EXTRA_LOG_TYPE);
        String dateText = DateFormat.getDateFormat(this).format(this.date);

        TextView textHeadline = (TextView) findViewById(R.id.textHeadline);
        textHeadline.setText(dateText);

        TextView textSubheadline = (TextView) findViewById(R.id.textSubheadline);
        textSubheadline.setText(type.asHumanQuestion());
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
        TimeLog timeLog = new TimeLog(getSelectedTime(), type);
        databaseHelper.insert(timeLog);
    }

    private void printConfirmation() {
        String confirmation = type.asHumanNoun() + " um " +
                DateTimeFormat.shortTime().print(getSelectedTime()) +
                " eingetragen";
        Toast.makeText(getApplicationContext(), confirmation, Toast.LENGTH_SHORT).show();
    }
}
