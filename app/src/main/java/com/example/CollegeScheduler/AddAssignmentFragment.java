package com.example.CollegeScheduler;

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
import android.widget.ToggleButton;

import com.example.CollegeScheduler.databinding.FragmentAddAssignmentBinding;
import com.example.CollegeScheduler.databinding.FragmentModifyBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddAssignmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentAddAssignmentBinding binding;
    private MainActivity classActivity;

    private EditText name_of_assignment;
    private EditText class_name;

    private int intDueDate;

    Class theClass;



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

        name_of_assignment = (EditText)view.findViewById(R.id.name_of_assignment);
        //get the spinner from the xml.
        Spinner dropdown = getView().findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "three"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(classActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddAssignmentFragment.this)
                        .navigate(R.id.action_addAssignmentFragment_to_FirstFragment);
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


                classActivity.getClassList().addItem(new Assignment(name_of_assignment.getText().toString(),
                        theClass,
                        new GregorianCalendar()));

                classActivity.getClassAdapter().updateValues();
                NavHostFragment.findNavController(AddAssignmentFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
    public void showEndTimePickerDialog() {
        // Get the current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minuteOfDay) -> intDueDate = 100 * hourOfDay + minuteOfDay,
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}