package net.kaeff.zazu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import zazu.kaeff.net.zazu.R;

public class LogTimeActivity extends AppCompatActivity {

    public static final String EXTRA_DATE = "date";

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

        Date date = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
        String dateText = DateFormat.getDateFormat(this).format(date);

        TextView textHeadline = (TextView) findViewById(R.id.textHeadline);
        textHeadline.setText(dateText);
    }

    public void onLogTimeClick(View view) {
        finish();
    }
}
