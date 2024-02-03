package com.example.CollegeScheduler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.CollegeScheduler.databinding.FragmentAddAssignmentBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddAssignmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentAddAssignmentBinding binding;
    private MainActivity classActivity;

    private EditText name_of_assignment;
    private EditText class_name;
    private Calendar calendarDueDate;
    private Class theClass;
    private int endHour;
    private int endMinute;
    private int endYear;
    private int endMonth;
    private int endDay;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();

        binding = FragmentAddAssignmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name_of_assignment = view.findViewById(R.id.name_of_assignment);
        Spinner dropdown = getView().findViewById(R.id.classSpinnerAssignment);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, classActivity.getClassList().names());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        binding.backButton.setOnClickListener((View.OnClickListener) view1 -> NavHostFragment.findNavController(AddAssignmentFragment.this)
                .navigate(R.id.action_addAssignmentFragment_to_FirstFragment));
        binding.timePickerButtonAssignment.setOnClickListener((View.OnClickListener) v -> showEndTimePickerDialog());
        binding.datePickerButtonAssignment.setOnClickListener((View.OnClickListener) v -> showDatePickerDialog());

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarDueDate = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);
                classActivity.getTasksList().addItem(new Assignment(name_of_assignment.getText().toString(),
                        theClass,
                        calendarDueDate));
                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(AddAssignmentFragment.this)
                        .navigate(R.id.action_addAssignmentFragment_to_FirstFragment);
            }
        });
    }
    public void showEndTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minuteOfDay) -> {endHour = hourOfDay; endMinute = minuteOfDay;},
                hour, minute, false);
        timePickerDialog.show();
    }

    public void showDatePickerDialog() {
        // Get the current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endYear = year;
                        endMonth = month;
                        endDay = dayOfMonth;
                    }
                },
                year,
                month,
                dayOfMonth);

        datePickerDialog.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        theClass = (Class) classActivity.getClassList().getItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}