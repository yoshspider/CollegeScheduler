package com.example.CollegeScheduler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.CollegeScheduler.databinding.FragmentEditClassBinding;
import com.example.CollegeScheduler.databinding.FragmentEditTaskBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.05
 * Edit Task defines the functionality for editing any
 * specific task object to remove/change details
 * as in the future, many times these details can change
 * It will then change such features within the CollegeObjectList
 * and update the ListView osn the home page
 */
public class EditTask extends Fragment {

    private FragmentEditTaskBinding binding;
    private MainActivity classActivity;
    Context thiscontext;

    private EditText name;
    private Class associatedClass;
    private Calendar calendar;
    private int priority;
    private EditText location;
    private int type;

    private int endHour;
    private int endMinute;
    private int endYear;
    private int endMonth;
    private int endDay;

    private Task toChange;
    private int typeOfTODO;
    private int itemIndex;
    private final Calendar c = Calendar.getInstance();

    private Spinner className;
    private Spinner taskType;
    private Spinner taskPriority;
    private ArrayAdapter<String> nameAdapter;

    CollegeObjectList<ListItem> taskList;
    CollegeObjectList<ListItem> classList;

    /**
     * Inflate view and assign activity variable
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
        taskList = classActivity.getTasksList();
        classList = classActivity.getClassList();

        thiscontext = container.getContext();
        binding = FragmentEditTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Set up UI elements and interactivity with buttons and spinners
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> taskNamesList = new ArrayList<>();
        ArrayList<String> namesFromClassList = new ArrayList<>();
        listNames(taskNamesList, namesFromClassList);

        initSpinners(namesFromClassList, taskNamesList);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Navigate back to previous screen
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTask.this)
                        .navigate(R.id.action_editTask_to_FirstFragment);
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Updates task list with new data
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                saveNewData();
                NavHostFragment.findNavController(EditTask.this)
                        .navigate(R.id.action_editTask_to_FirstFragment);
            }
        });

        binding.timesPickerButtonTask.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows editing task time
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                showEndTimePickerDialog();
            }
        });

        binding.datesPickerButtonTask.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows editing task date
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    /**
     * Gets names of tasks and classes for dropdown menus
     * @param taskNames task ArrayList to populate
     * @param classNames class ArrayList to populate
     */
    private void listNames(ArrayList<String> taskNames, ArrayList<String> classNames) {
        for (int i = 0; i < taskList.size() ; i++) {
            taskNames.add(((Task) taskList.getItem(i)).getOnlyName());
        }
        for (int i = 0; i < classList.size() ; i++) {
            classNames.add( ((Class) classList.getItem(i)).getClassName() );
        }
        classNames.add(0, "");
    }

    /**
     * Initialize spinners and assign adapters
     * @param namesFromClassList list for class spinner options
     * @param taskNamesList list for task spinner options
     */
    private void initSpinners(ArrayList<String> namesFromClassList, ArrayList<String> taskNamesList) {
        taskType = getView().findViewById(R.id.taskTypeSpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Task.types);
        setUpSpinner(taskType, typeAdapter, new AdapterTypeSelector());

        className = getView().findViewById(R.id.classSpinnerTask);
        nameAdapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, namesFromClassList);
        setUpSpinner(className, nameAdapter, new AdapterClassNameSelector());

        taskPriority = getView().findViewById(R.id.prioritySpinnerTask);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Task.priorities);
        setUpSpinner(taskPriority, priorityAdapter, new AdapterPrioritySelector());

        Spinner specificTask = getView().findViewById(R.id.choose_task);
        ArrayAdapter<String> specificAdapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, taskNamesList);
        setUpSpinner(specificTask, specificAdapter, new AdapterSpecificType());
    }

    /**
     * Assigns adapater and listener to spinner
     * @param spinner spinner to set up
     * @param adapter adapter to set to spinner
     * @param listener listener for interactivity with spinner
     */
    private void setUpSpinner(Spinner spinner, ArrayAdapter<String> adapter, AdapterView.OnItemSelectedListener listener) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);
    }

    /**
     * Update task list with data from edit screen
     */
    private void saveNewData() {
        ((Task) taskList.getItem(itemIndex)).setName(name.getText().toString());
        ((Task) taskList.getItem(itemIndex)).setLocation(location.getText().toString());
        ((Task) taskList.getItem(itemIndex)).setTheClass(associatedClass);
        ((Task) taskList.getItem(itemIndex)).setPriority(priority);
        ((Task) taskList.getItem(itemIndex)).setType(type);
        Calendar calendar = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMinute);
        ((Task) taskList.getItem(itemIndex)).setCalendar(calendar);
        classActivity.getClassAdapter().updateValues();
    }

    /**
     * Open time picker for task time input
     */
    public void showEndTimePickerDialog() {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minuteOfDay) -> {endHour = hourOfDay; endMinute = minuteOfDay;},
                ((GregorianCalendar) toChange.getCalendar()).get(Calendar.HOUR_OF_DAY), ((GregorianCalendar) toChange.getCalendar()).get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    /**
     * Open time picker for task date input
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
        }, ((GregorianCalendar) toChange.getCalendar()).get(Calendar.YEAR), ((GregorianCalendar) toChange.getCalendar()).get(Calendar.MONTH), ((GregorianCalendar) toChange.getCalendar()).get(Calendar.DAY_OF_MONTH));
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

    private class AdapterTypeSelector implements AdapterView.OnItemSelectedListener {
        /**
         * Assigns new type to task
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
         * Required method for no item selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private class AdapterClassNameSelector implements AdapterView.OnItemSelectedListener {
        /**
         * Assigns new associated class to task
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                associatedClass = new Class();
            } else {
                associatedClass = (Class) classActivity.getClassList().getItem(position-1);
            }
        }
        /**
         * Required method for no item selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private class AdapterPrioritySelector implements AdapterView.OnItemSelectedListener {
        /**
         * Assigns new priority to task
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
         * Required method for no item selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private class AdapterSpecificType implements AdapterView.OnItemSelectedListener {
        /**
         * Updates edit screen for new task selected to edit
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            itemIndex = position;

            toChange = (Task) taskList.getItem(itemIndex);
            name = (EditText) getView().findViewById(R.id.name_of_task);
            location = (EditText) getView().findViewById(R.id.location_of_task);
            name.setText(toChange.getOnlyName());
            if (toChange.getLocation() != null) {
                location.setText(toChange.getLocation());
            }
            className.setSelection(nameAdapter.getPosition(toChange.getTheClass().getClassName()));
            taskType.setSelection(toChange.getType());
            taskPriority.setSelection(toChange.getPriority());
        }
        /**
         * Required method for no item selected
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }
}