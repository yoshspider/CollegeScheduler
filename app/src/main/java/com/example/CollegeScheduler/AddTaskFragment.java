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

import java.util.ArrayList;
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
     * @  view associated with fragment
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        classActivity = (MainActivity)getActivity();
        binding = FragmentAddAssignmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Initialize fields and navigation for the fragment
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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
            classActivity.swapToTasks();
        });
    }

    /**
     * Assigns view references and button actions
     * @param view view that holds the buttons
     */
    public void buttonSetUp(View view) {
        name_of_assignment = view.findViewById(R.id.name_of_task);
        location_of_assignment = view.findViewById(R.id.location_of_task);
        binding.backButton.setOnClickListener(view1 -> NavHostFragment.findNavController(AddTaskFragment.this)
                .navigate(R.id.action_addAssignmentFragment_to_FirstFragment));
        binding.timePickerButtonTask.setOnClickListener(v -> showEndTimePickerDialog());
        binding.datePickerButtonTask.setOnClickListener(v -> showDatePickerDialog());
    }

    /**
     * Initializes task selection components
     * @param view view that holds components
     */
    public void adapterSetUp(View view) {
        Spinner dropdownType = view.findViewById(R.id.taskTypeSpinner);
        adapterParts(dropdownType, Task.types);
        dropdownType.setOnItemSelectedListener(new AdapterTypeSelector());
        Spinner dropdownClass = view.findViewById(R.id.classSpinnerTask);

        //adding an empty class object to front
        String[] list_of_names = new String[classActivity.getClassList().size() + 1];
        list_of_names[0] = "No Associated Class";
        for (int i = 1 ; i < list_of_names.length ; i++) {
            list_of_names[i] = ((Class)classActivity.getClassList().getItem(i-1)).getClassName();
        }

        adapterParts(dropdownClass, list_of_names);
        dropdownClass.setOnItemSelectedListener(new AdapterClassSelector());
        Spinner dropdownPriority = view.findViewById(R.id.prioritySpinnerTask);
        adapterParts(dropdownPriority, Task.priorities);
        dropdownPriority.setOnItemSelectedListener(new AdapterPrioritySelector());
    }

    //TODO: give this a better name?
    /**
     *
     * @param spinner
     * @param items
     */
    private void adapterParts(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterPriority);
    }

    /**
     * Shows time picker widget for task due time
     */
    public void showEndTimePickerDialog() {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minuteOfDay) -> {endHour = hourOfDay; endMinute = minuteOfDay;},
                hour, minute, false);
        timePickerDialog.show();
    }

    /**
     * Shows date picker widget for task due date
     */
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

    /**
     * Removes unneeded binding reference when view destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class AdapterClassSelector implements AdapterView.OnItemSelectedListener {
        /**
         * Associates task with selected class
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                theClass = new Class();
            } else {
                theClass = (Class) classActivity.getClassList().getItem(position-1);
            }
        }

        /**
         * Provides default class when no option selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            theClass = (Class) classActivity.getClassList().getItem(0);
        }
    }
    private class AdapterPrioritySelector implements AdapterView.OnItemSelectedListener {
        /**
         * Associates task with selected priority
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            priority = position;
        }

        /**
         * Provides default priority when no option selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            priority = 0;
        }
    }
    private class AdapterTypeSelector implements AdapterView.OnItemSelectedListener {
        /**
         * Associates task with selected type
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            type = position;
        }

        /**
         * Provides default type when no option selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            type = 0;
        }
    }
}