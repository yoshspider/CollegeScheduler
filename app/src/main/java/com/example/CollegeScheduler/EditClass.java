package com.example.CollegeScheduler;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.CollegeScheduler.databinding.FragmentEditClassBinding;

import java.util.ArrayList;


public class EditClass extends Fragment implements AdapterView.OnItemSelectedListener{
    private Context a;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a = getActivity().getApplicationContext();
    }

    private FragmentEditClassBinding binding;
    private MainActivity classActivity;
    Context thiscontext;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();
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
        ArrayList<Class> list_of_classes = new ArrayList<>();
        list_of_classes.add(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900, "CULC 250"));
        list_of_classes.add(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000, "CULC 250"));
        list_of_classes.add(new Class("Objects and Design", "Pedro", new boolean[]{false, true, false, true, false}, 1230,1430, "HOWEY A419"));

        ArrayList<String> list_of_class_names = new ArrayList<>();
        for (Class classObject : list_of_classes) {
            list_of_class_names.add(classObject.toString());
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
                   System.out.println(position);
                   Class item_changed = list_of_classes.get(position);
                   final EditText course_name = (EditText) getView().findViewById(R.id.course_entry);

                   //course_name.setHint(item_changed.getClassName());
                   course_name.setText(item_changed.getClassName());
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