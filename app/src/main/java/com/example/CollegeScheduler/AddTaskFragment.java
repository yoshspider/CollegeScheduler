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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.CollegeScheduler.databinding.FragmentAddAssignmentBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTaskFragment extends Fragment {

    private FragmentAddAssignmentBinding binding;
    private MainActivity classActivity;
    private EditText name_of_assignment;
    private EditText location_of_assignment;
    private Calendar calendarDueDate;
    private Class theClass;
    private int endHour;
    private int endMinute;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int priority;
    private int type;
    private final Calendar c = Calendar.getInstance();
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
        adapterSetUp(view);
        buttonSetUp(view);
        binding.saveButton.setOnClickListener(view12 -> {
            calendarDueDate = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);
            Task newTask = new Task(type, name_of_assignment.getText().toString(), calendarDueDate, theClass, priority, location_of_assignment.getText().toString());
            classActivity.getTasksList().addItem(newTask);
            classActivity.getClassAdapter().updateValues();
            NavHostFragment.findNavController(AddTaskFragment.this)
                    .navigate(R.id.action_addAssignmentFragment_to_FirstFragment);
        });
    }
    public void buttonSetUp(View view) {
        name_of_assignment = view.findViewById(R.id.name_of_task);
        location_of_assignment = view.findViewById(R.id.location_of_task);
        binding.backButton.setOnClickListener(view1 -> NavHostFragment.findNavController(AddTaskFragment.this)
                .navigate(R.id.action_addAssignmentFragment_to_FirstFragment));
        binding.timePickerButtonTask.setOnClickListener(v -> showEndTimePickerDialog());
        binding.datePickerButtonTask.setOnClickListener(v -> showDatePickerDialog());
    }
    public void adapterSetUp(View view) {
        Spinner dropdownType = getView().findViewById(R.id.taskTypeSpinner);
        adapterParts(dropdownType, Task.types);
        dropdownType.setOnItemSelectedListener(new AdapterTypeSelector());
        Spinner dropdownClass = getView().findViewById(R.id.classSpinnerTask);
        adapterParts(dropdownClass, classActivity.getClassList().names());
        dropdownClass.setOnItemSelectedListener(new AdapterClassSelector());
        Spinner dropdownPriority = getView().findViewById(R.id.prioritySpinnerTask);
        adapterParts(dropdownPriority, Task.priorities);
        dropdownPriority.setOnItemSelectedListener(new AdapterPrioritySelector());
    }
    private void adapterParts(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterPriority);
    }
    public void showEndTimePickerDialog() {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minuteOfDay) -> {endHour = hourOfDay; endMinute = minuteOfDay;},
                hour, minute, false);
        timePickerDialog.show();
    }

    public void showDatePickerDialog() {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), (view, year1, month1, dayOfMonth1) -> {
                    endYear = year1;
                    endMonth = month1;
                    endDay = dayOfMonth1;
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class AdapterClassSelector implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            theClass = (Class) classActivity.getClassList().getItem(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            theClass = (Class) classActivity.getClassList().getItem(0);
        }
    }
    private class AdapterPrioritySelector implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            priority = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            priority = 0;
        }
    }
    private class AdapterTypeSelector implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            type = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            type = 0;
        }
    }
}