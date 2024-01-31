package com.example.CollegeScheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import java.util.Date;

public class AddFragment extends Fragment {

    private FragmentModifyBinding binding;
    private MainActivity classActivity;

    private EditText courseInput;
    private EditText professorInput;
    private EditText locationInput;
    private EditText startTime;
    private EditText endTime;
    private boolean[] daysOn;

    private ToggleButton mon;
    private ToggleButton tues;
    private ToggleButton wed;
    private ToggleButton thurs;
    private ToggleButton fri;




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
        startTime = (EditText)view.findViewById(R.id.start_entry);
        endTime = (EditText)view.findViewById(R.id.end_entry);
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

                //parse time
                DateFormat formatter = new SimpleDateFormat("hh:mm");
                String startFormatted;
                String endFormatted;
                try {
                    startFormatted = formatter.format(formatter.parse(startTime.getText().toString()));
                    endFormatted = formatter.format(formatter.parse(endTime.getText().toString()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


                classActivity.getClassList().addItem(new Class(courseInput.getText().toString(), professorInput.getText().toString(), daysOn, startFormatted, endFormatted, locationInput.getText().toString()));
                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(AddFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}