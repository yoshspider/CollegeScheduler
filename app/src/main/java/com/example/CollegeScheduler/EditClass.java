package com.example.CollegeScheduler;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.CollegeScheduler.MainActivity;
import com.example.CollegeScheduler.databinding.FragmentEditClassBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.03
 * Edit Class defines the functionality for editing any
 * specific class object to remove/change details
 * as in the future, many times these details can change
 * It will then change such features within the CollegeObjectList
 * and update the ListView on the home page
 */
public class EditClass extends Fragment {
    private FragmentEditClassBinding binding;
    private MainActivity classActivity;
    Context thiscontext;
    private EditText course_name;
    private EditText professor_name;
    private EditText location;
    private ToggleButton monday_toggle;
    private ToggleButton tuesday_toggle;
    private ToggleButton wednesday_toggle;
    private ToggleButton thursday_toggle;
    private ToggleButton friday_toggle;
    private int intStartTime;
    private int intEndTime;

    private Class itemChanged;
    private int itemIndex;

    private boolean[] daysOn;
    CollegeObjectList<ListItem> list_of_classes;

    private int endHour;
    private int endMinute;

    private int startHour;
    private int startMinute;

    /**
     * Identify activity/context and inflate view
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the view created
     */
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();
        list_of_classes = classActivity.getClassList();

        thiscontext = container.getContext();
        binding = FragmentEditClassBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Set up UI and button actions
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpDropdown(getView().findViewById(R.id.spinner1));

        binding.backButton.setOnClickListener(view1 -> NavHostFragment.findNavController(EditClass.this)
                .navigate(R.id.action_editClass_to_FirstFragment));

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daysOn = new boolean[]{
                        monday_toggle.isChecked(),
                        tuesday_toggle.isChecked(),
                        wednesday_toggle.isChecked(),
                        thursday_toggle.isChecked(),
                        friday_toggle.isChecked()
                };

                saveChanges(
                        course_name.getText().toString(),
                        professor_name.getText().toString(),
                        location.getText().toString()
                );

                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(EditClass.this)
                        .navigate(R.id.action_editClass_to_FirstFragment);
            }
        });

        binding.startTime.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows editing task time
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                showStartTimePickerDialog();
            }
        });

        binding.endTime.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows editing task date
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                showEndTimePickerDialog();
            }
        });

    }

    /**
     * Assign list of classes to edit as spinner options
     * @param spinner spinner view to set up
     */
    private void setUpDropdown(Spinner spinner) {
        Spinner dropdown = spinner;
        //create a list of items for the spinner.
        ArrayList<String> list_of_class_names = new ArrayList<>();
        for (int i = 0 ; i < list_of_classes.size() ; i++) {
            ListItem tmp = list_of_classes.getItem(i);
            System.out.println(list_of_classes.getItem(i).getClass());
            list_of_class_names.add(list_of_classes.getItem(i).toString());
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_of_class_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterClassSelect());
    }

    /**
     * Save edits to list of classes
     * @param className new class name
     * @param profName new professor name
     * @param loc new location
     */
    private void saveChanges(String className, String profName, String loc) {
        ((Class) classActivity.getClassList().getItem(itemIndex)).setClassName(className);
        ((Class) classActivity.getClassList().getItem(itemIndex)).setProfessorName(profName);
        ((Class) classActivity.getClassList().getItem(itemIndex)).setMeetingDates(daysOn);
        ((Class) classActivity.getClassList().getItem(itemIndex)).setStartTime(intStartTime);
        ((Class) classActivity.getClassList().getItem(itemIndex)).setEndTime(intEndTime);
        ((Class) classActivity.getClassList().getItem(itemIndex)).setLocation(loc);
    }

    /**
     * Open time picker for start time input
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
        timePickerDialog.updateTime(getIntStartTime()/100,getIntStartTime() % 100 );

        timePickerDialog.show();
    }

    /**
     * Open time picker for end time input
     */
    public void showEndTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minuteOfDay) -> intEndTime = 100 * hourOfDay + minuteOfDay,
                hour,
                minute,
                false);
        timePickerDialog.updateTime(getIntEndTime()/100,getIntEndTime() % 100 );
        timePickerDialog.show();
    }

    /**
     * Getter for start time
     * @return start time as an integer
     */
    public int getIntStartTime() {
        return intStartTime;
    }

    /**
     * Setter for start time
     * @param intStartTime the new start time
     */
    public void setIntStartTime(int intStartTime) {
        this.intStartTime = intStartTime;
    }

    /**
     * Getter for end time
     * @return end time as an integer
     */
    public int getIntEndTime() {
        return intEndTime;
    }

    /**
     * Setter for end time
     * @param intEndTime the new end time
     */
    public void setIntEndTime(int intEndTime) {
        this.intEndTime = intEndTime;
    }

    /**
     * Assign button variables from UI elements
     */
    private void initializeButtons() {
        course_name = (EditText) getView().findViewById(R.id.course);
        professor_name = (EditText) getView().findViewById(R.id.professor_entry);
        location = (EditText) getView().findViewById(R.id.location_entry);
        monday_toggle = (ToggleButton) getView().findViewById(R.id.monday_toggle);
        tuesday_toggle = (ToggleButton) getView().findViewById(R.id.tuesday_toggle);
        wednesday_toggle = (ToggleButton) getView().findViewById(R.id.wednesday_toggle);
        thursday_toggle = (ToggleButton) getView().findViewById(R.id.thursday_toggle);
        friday_toggle = (ToggleButton) getView().findViewById(R.id.friday_toggle);
    }

    /**
     * Fill existing names and settings on edit screen
     */
    private void populateFields() {
        course_name.setText(itemChanged.getClassName());
        professor_name.setText(itemChanged.getProfessorName());
        location.setText(itemChanged.getLocation());
        monday_toggle.setChecked(itemChanged.getMeetingDates()[0]);
        tuesday_toggle.setChecked(itemChanged.getMeetingDates()[1]);
        wednesday_toggle.setChecked(itemChanged.getMeetingDates()[2]);
        thursday_toggle.setChecked(itemChanged.getMeetingDates()[3]);
        friday_toggle.setChecked(itemChanged.getMeetingDates()[4]);

        setIntStartTime(itemChanged.getStartTime());
        setIntEndTime(itemChanged.getEndTime());
    }

    /**
     * Removes unneeded binding reference when view destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class AdapterClassSelect implements AdapterView.OnItemSelectedListener {
        /**
         * Handle class selected to edit
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            itemIndex = position;
            itemChanged = (Class) list_of_classes.getItem(position);
            final Button startTime = (Button) getView().findViewById(R.id.startTime);
            final Button endTime = (Button) getView().findViewById(R.id.endTime);

            initializeButtons();
            populateFields();

            //startTime.setOnClickListener(v -> showStartTimePickerDialog());
            //endTime.setOnClickListener(v -> showEndTimePickerDialog());
        }

        /**
         * Required method for no item selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
