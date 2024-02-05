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

import com.example.CollegeScheduler.databinding.FragmentEditClassBinding;

import java.util.ArrayList;
import java.util.Calendar;


public class EditClass extends Fragment implements AdapterView.OnItemSelectedListener{
    private Context a;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a = getActivity().getApplicationContext();
    }

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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get the spinner from the xml.
        Spinner dropdown = getView().findViewById(R.id.spinner1);
        //create a list of items for the spinner.


        //Create a list of Class objects. Based on this list, create a list of strings w/ names of classes
        // Use this list as input for the spinner. When selecting item, get index of item

        //list_of_classes.add(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900, "CULC 250"));
        //list_of_classes.add(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000, "CULC 250"));
        //list_of_classes.add(new Class("Objects and Design", "Pedro", new boolean[]{false, true, false, true, false}, 1230,1430, "HOWEY A419"));

        ArrayList<String> list_of_class_names = new ArrayList<>();
        for (int i = 0 ; i < list_of_classes.size() ; i++) {
            ListItem tmp = list_of_classes.getItem(i);
            System.out.println(list_of_classes.getItem(i).getClass());
            list_of_class_names.add(list_of_classes.getItem(i).toString());
        }

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_of_class_names);
        //set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   itemIndex = position;

                   itemChanged = (Class) list_of_classes.getItem(position);
                   course_name = (EditText) getView().findViewById(R.id.course);
                   professor_name = (EditText) getView().findViewById(R.id.professor_entry);
                   location = (EditText) getView().findViewById(R.id.location_entry);
                   monday_toggle = (ToggleButton) getView().findViewById(R.id.monday_toggle);
                   tuesday_toggle = (ToggleButton) getView().findViewById(R.id.tuesday_toggle);
                   wednesday_toggle = (ToggleButton) getView().findViewById(R.id.wednesday_toggle);
                   thursday_toggle = (ToggleButton) getView().findViewById(R.id.thursday_toggle);
                   friday_toggle = (ToggleButton) getView().findViewById(R.id.friday_toggle);

                   final Button startTime = (Button) getView().findViewById(R.id.startTime);
                   final Button endTime = (Button) getView().findViewById(R.id.endTime);


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
                   startTime.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           showStartTimePickerDialog();
                       }
                   });
                   endTime.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           showEndTimePickerDialog();
                       }
                   });


               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditClass.this)
                        .navigate(R.id.action_editClass_to_FirstFragment);
            }
        });

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


                ((Class) classActivity.getClassList().getItem(itemIndex)).setClassName(course_name.getText().toString());
                ((Class) classActivity.getClassList().getItem(itemIndex)).setProfessorName(professor_name.getText().toString());
                ((Class) classActivity.getClassList().getItem(itemIndex)).setMeetingDates(daysOn);
                ((Class) classActivity.getClassList().getItem(itemIndex)).setStartTime(intStartTime);
                ((Class) classActivity.getClassList().getItem(itemIndex)).setStartTime(intEndTime);
                ((Class) classActivity.getClassList().getItem(itemIndex)).setLocation(location.getText().toString());

                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(EditClass.this)
                        .navigate(R.id.action_editClass_to_FirstFragment);
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
        timePickerDialog.updateTime(getIntStartTime()/100,getIntStartTime() % 60 );

        timePickerDialog.show();
    }

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
        timePickerDialog.updateTime(getIntStartTime()/100,getIntStartTime() % 60 );
        timePickerDialog.show();
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
                // Whatever you want to happen when the third item gets selected
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

    public int getIntStartTime() {
        return intStartTime;
    }

    public void setIntStartTime(int intStartTime) {
        this.intStartTime = intStartTime;
    }

    public int getIntEndTime() {
        return intEndTime;
    }

    public void setIntEndTime(int intEndTime) {
        this.intEndTime = intEndTime;
    }
}