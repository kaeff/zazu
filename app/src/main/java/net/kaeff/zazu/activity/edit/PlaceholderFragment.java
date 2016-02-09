package net.kaeff.zazu.activity.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.kaeff.zazu.DateTimeFormats;
import net.kaeff.zazu.model.DayLog;

import org.joda.time.Duration;

import zazu.kaeff.net.zazu.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private View rootView;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_time_logs, container, false);

        registerTimePickerDialog(rootView.findViewById(R.id.morningTimeDisplay));
        registerTimePickerDialog(rootView.findViewById(R.id.eveningTimeDisplay));

        View breaksDisplay = rootView.findViewById(R.id.breaksDisplay);
        breaksDisplay.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                updateDayHours();
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        updateDayHours();
        super.onStart();
    }

    private void registerTimePickerDialog(final View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showTimePickerDialogForView();
                    return true;
                }
                return false;
            }

            private void showTimePickerDialogForView() {
                Bundle bundle = new Bundle();
                bundle.putInt(TimePickerFragment.VIEW_ID, view.getId());
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setArguments(bundle);
                timePickerFragment.show(getFragmentManager(), "timePicker" + view.getId());
                timePickerFragment.setOnDialogDismiss(new Runnable() {
                    @Override
                    public void run() {
                        updateDayHours();
                    }
                });
            }
        });
    }

    private void updateDayHours() {
        Duration minus = DayLog.parse(
                textFromView(R.id.morningTimeDisplay),
                textFromView(R.id.eveningTimeDisplay),
                textFromView(R.id.breaksDisplay)
        ).asWorkHours();

        TextView dailyHoursDisplay = (TextView) rootView.findViewById(R.id.dailyHoursDisplay);
        dailyHoursDisplay.setText(DateTimeFormats.PERIOD_FORMAT.print(minus.toPeriod()));
    }

    private String textFromView(int id) {
        return ((TextView) rootView.findViewById(id)).getText().toString();
    }

}
