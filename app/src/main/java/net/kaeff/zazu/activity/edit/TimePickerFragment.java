package net.kaeff.zazu.activity.edit;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import net.kaeff.zazu.DateTimeFormats;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import lombok.Setter;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Setter
    private Runnable onDialogDismiss;

    public static final String VIEW_ID = "viewId";
//    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.shortTime();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String str = getBackingView().getText().toString();
        LocalTime currentTime = DateTimeFormats.parseTime(str);
        return new TimePickerDialog(getActivity(), this,
                currentTime.getHourOfDay(), currentTime.getMinuteOfHour(),
                DateFormat.is24HourFormat(getActivity()));
    }

    private TextView getBackingView() {
        return (TextView) getActivity().findViewById(getArguments().getInt(VIEW_ID));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime selectedTime = new LocalTime(hourOfDay, minute);
        getBackingView().setText(DATE_TIME_FORMATTER.print(selectedTime));
    }

    @Override
    public void onDestroyView() {
        if (onDialogDismiss != null) {
            onDialogDismiss.run();
        }
        super.onDestroyView();

    }
}
