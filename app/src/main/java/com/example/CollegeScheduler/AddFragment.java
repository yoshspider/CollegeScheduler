package com.example.CollegeScheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.CollegeScheduler.databinding.FragmentModifyBinding;

import java.util.Calendar;

/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.21
 * AddFragment defines the behavior of the Add Class Screen
 * which intakes user input for all Class parameters
 * in order to create a new Class Object that will be added
 * to the ListView
 */
public class AddFragment extends Fragment {

    private FragmentModifyBinding binding;
    private MainActivity classActivity;
    private EditText courseInput;
    private EditText courseSection;
    private EditText professorInput;
    private EditText locationInput;
    private EditText roomNumber;
    private boolean[] daysOn;

    private ToggleButton mon;
    private ToggleButton tues;
    private ToggleButton wed;
    private ToggleButton thurs;
    private ToggleButton fri;

    private int intStartTime;
    private int intEndTime;

    /**
     * Initial view setup
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return inflated view associated with fragment
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();

        binding = FragmentModifyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Helper method to initialize fields to store input data
     * @param view view that holds input fields
     */
    private void initInputs(View view) {
        this.professorInput = (EditText)view.findViewById(R.id.professor_entry);
        this.locationInput = (EditText)view.findViewById(R.id.location_entry);
        this.courseInput = (EditText)view.findViewById(R.id.name_of_task);
        this.courseSection = (EditText)view.findViewById(R.id.class_name);
        this.roomNumber = (EditText)view.findViewById(R.id.room_number);
        this.mon = (ToggleButton)view.findViewById(R.id.monday_toggle);
        this.tues = (ToggleButton)view.findViewById(R.id.tuesday_toggle);
        this.wed = (ToggleButton)view.findViewById(R.id.wednesday_toggle);
        this.thurs = (ToggleButton)view.findViewById(R.id.thursday_toggle);
        this.fri = (ToggleButton)view.findViewById(R.id.friday_toggle);
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initInputs(view);

        binding.backButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Navigates from add screen to class screen
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });


        binding.startTime.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows user to select start time
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                showStartTimePickerDialog();
            }
        });

        binding.endTime.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows user to select end time
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                showEndTimePickerDialog();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Updates class list with user-inputted data
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                daysOn = new boolean[]{
                        mon.isChecked(),
                        tues.isChecked(),
                        wed.isChecked(),
                        thurs.isChecked(),
                        fri.isChecked()
                };

                classActivity.getClassList().addItem(new Class(courseInput.getText().toString() + " " + courseSection.getText().toString(),
                        professorInput.getText().toString(),
                        daysOn, intStartTime, intEndTime,
                        locationInput.getText().toString() + " " + roomNumber.getText().toString()));

                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(AddFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    /**
     * Shows time picker widgets to update start time
     */
    public void showStartTimePickerDialog() {
        // Get the current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minuteOfDay) -> intStartTime = 100 * hourOfDay + minuteOfDay,
                hour,
                minute,
                false);
        timePickerDialog.show();
    }

    /**
     * Shows time picker widgets to update end time
     * Requires separate method due to lambda invocation
     */
    public void showEndTimePickerDialog() {
        // Get the current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minuteOfDay) -> intEndTime = 100 * (hourOfDay) + minuteOfDay,
                hour,
                minute,
                false);
        timePickerDialog.show();
    }

    /**
     * Removes unneeded binding reference when view destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}