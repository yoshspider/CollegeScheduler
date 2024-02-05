package com.example.CollegeScheduler;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.CollegeScheduler.databinding.FragmentEditClassBinding;
import com.example.CollegeScheduler.databinding.FragmentEditTaskBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class editTask extends Fragment implements AdapterView.OnItemSelectedListener{
    private Context a;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a = getActivity().getApplicationContext();
    }

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

    CollegeObjectList<ListItem> list_of_tasks;
    CollegeObjectList<ListItem> list_of_classes;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();
        list_of_tasks = classActivity.tasksList;
        list_of_classes = classActivity.classList;

        thiscontext = container.getContext();
        binding = FragmentEditTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ArrayList<String> list_of_names_tasks = new ArrayList<>();
        ArrayList<String> list_of_names_classes = new ArrayList<>();
        for (int i = 0 ; i < list_of_tasks.size() ; i++) {
            list_of_names_tasks.add(((Task) list_of_tasks.getItem(i)).getOnlyName());
            list_of_names_classes.add(((Task) list_of_tasks.getItem(i)).getTheClass().getClassName());
        }
        ArrayList<String> list_of_class_names_from_list_of_classes = new ArrayList<>();
        for (int i = 0 ; i < list_of_classes.size() ; i++) {
            list_of_class_names_from_list_of_classes.add( ((Class)list_of_classes.getItem(i)).getClassName() );
        }
        //System.out.println(list_of_names_tasks);
        //System.out.println(list_of_names_classes);

        Spinner type_of_task = getView().findViewById(R.id.taskTypeSpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Task.types);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_of_task.setAdapter(adapter2);
        type_of_task.setOnItemSelectedListener(this);

        type_of_task.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Spinner name_of_class = getView().findViewById(R.id.classSpinnerTask);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_of_class_names_from_list_of_classes);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name_of_class.setAdapter(adapter3);
        name_of_class.setOnItemSelectedListener(this);

        name_of_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                associatedClass = (Class) classActivity.getClassList().getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Spinner priority_of_task = getView().findViewById(R.id.prioritySpinnerTask);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Task.priorities);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority_of_task.setAdapter(adapter4);
        priority_of_task.setOnItemSelectedListener(this);

        priority_of_task.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priority = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        Spinner specificTask = getView().findViewById(R.id.choose_task);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_of_names_tasks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specificTask.setAdapter(adapter);
        specificTask.setOnItemSelectedListener(this);
        specificTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemIndex = position;

                toChange = (Task) list_of_tasks.getItem(itemIndex);
                name = (EditText) getView().findViewById(R.id.name_of_task);
                location = (EditText) getView().findViewById(R.id.location_of_task);
                name.setText(toChange.getOnlyName());
                if (toChange.getLocation() != null) {
                    location.setText(toChange.getLocation());
                }
                name_of_class.setSelection(adapter3.getPosition(toChange.getTheClass().getClassName()));
                type_of_task.setSelection(toChange.getType());
                priority_of_task.setSelection(toChange.getPriority());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(editTask.this)
                        .navigate(R.id.action_editTask_to_FirstFragment);
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((Task)list_of_tasks.getItem(itemIndex)).setName(name.getText().toString());
                ((Task)list_of_tasks.getItem(itemIndex)).setLocation(location.getText().toString());
                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(editTask.this)
                        .navigate(R.id.action_editTask_to_FirstFragment);
            }
        });

        binding.datePickerButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndTimePickerDialog();
            }
        });

        binding.timePickerButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


    }

    public void showEndTimePickerDialog() {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minuteOfDay) -> {endHour = hourOfDay; endMinute = minuteOfDay;},
                hour, minute, false);
        timePickerDialog.updateTime(((GregorianCalendar) toChange.getCalendar()).get(Calendar.HOUR_OF_DAY), ((GregorianCalendar) toChange.getCalendar()).get(Calendar.MINUTE));
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
        datePickerDialog.updateDate(((GregorianCalendar) toChange.getCalendar()).get(Calendar.YEAR), ((GregorianCalendar) toChange.getCalendar()).get(Calendar.MONTH), ((GregorianCalendar) toChange.getCalendar()).get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}