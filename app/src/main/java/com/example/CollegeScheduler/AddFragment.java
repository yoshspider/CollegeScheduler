package com.example.CollegeScheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.CollegeScheduler.databinding.FragmentModifyBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddFragment extends Fragment {

    private FragmentModifyBinding binding;
    private MainActivity classActivity;

    private EditText courseInput;
    private EditText professorInput;
    private EditText locationInput;
    private boolean[] daysOn;

    private ToggleButton mon;
    private ToggleButton tues;
    private ToggleButton wed;
    private ToggleButton thurs;
    private ToggleButton fri;

    private int intStartTime;
    private int intEndTime;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();

        binding = FragmentModifyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        professorInput = (EditText)view.findViewById(R.id.professor_entry);
        locationInput = (EditText)view.findViewById(R.id.location_entry);
        courseInput = (EditText)view.findViewById(R.id.course_entry);
        mon = (ToggleButton)view.findViewById(R.id.monday_toggle);
        tues = (ToggleButton)view.findViewById(R.id.tuesday_toggle);
        wed = (ToggleButton)view.findViewById(R.id.wednesday_toggle);
        thurs = (ToggleButton)view.findViewById(R.id.thursday_toggle);
        fri = (ToggleButton)view.findViewById(R.id.friday_toggle);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartTimePickerDialog();
            }
        });
        binding.endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndTimePickerDialog();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daysOn = new boolean[]{
                        mon.isChecked(),
                        tues.isChecked(),
                        wed.isChecked(),
                        thurs.isChecked(),
                        fri.isChecked()
                };

//                //parse time
//                DateFormat formatter = new SimpleDateFormat("hh:mm");
//                String startFormatted;
//                String endFormatted;
//                try {
//                    startFormatted = formatter.format(formatter.parse(startTime.getText().toString()));
//                    endFormatted = formatter.format(formatter.parse(endTime.getText().toString()));
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }


                classActivity.getClassList().addItem(new Class(courseInput.getText().toString(), professorInput.getText().toString(), daysOn, intStartTime, intEndTime, locationInput.getText().toString()));
                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(AddFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
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
    public void showEndTimePickerDialog() {
        // Get the current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minuteOfDay) -> intEndTime = 100 * hourOfDay + minuteOfDay,
                hour,
                minute,
                false);

        timePickerDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}