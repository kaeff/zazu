package net.kaeff.zazu.activity.edit;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final String VIEW_ID = "viewId";
//    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.shortTime();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String str = getBackingView().getText().toString();
        LocalTime currentTime = parseTime(str);
        return new TimePickerDialog(getActivity(), this, currentTime.getHourOfDay(), currentTime.getMinuteOfHour(),
                DateFormat.is24HourFormat(getActivity()));
    }

    private LocalTime parseTime(String str) {
        LocalTime currentTime = LocalTime.now();
        try {
            return DATE_TIME_FORMATTER.parseLocalTime(str);
        } catch (Exception e) {
            try {
                return LocalTime.parse(str);
            } catch (Exception e1) {
                Log.e(getClass().getCanonicalName(), "Can't understand this time value: " + str, e);
            }
        }
        return currentTime;
    }

    private TextView getBackingView() {
        return (TextView) getActivity().findViewById(getArguments().getInt(VIEW_ID));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime selectedTime = new LocalTime(hourOfDay, minute);
        getBackingView().setText(DATE_TIME_FORMATTER.print(selectedTime));
    }
}
