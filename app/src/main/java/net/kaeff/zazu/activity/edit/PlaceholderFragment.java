package net.kaeff.zazu.activity.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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
        View rootView = inflater.inflate(R.layout.fragment_edit_time_logs, container, false);

        registerTimePickerDialog(rootView.findViewById(R.id.morningTimeDisplay));
        registerTimePickerDialog(rootView.findViewById(R.id.eveningTimeDisplay));

        return rootView;
    }

    private void registerTimePickerDialog(final View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch
                    (View v, MotionEvent event) {
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
                timePickerFragment.show(getFragmentManager(), "timePicker"+view.getId());
            }
        });
    }

}
