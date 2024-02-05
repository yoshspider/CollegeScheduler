package com.example.CollegeScheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.CollegeScheduler.databinding.FragmentClassesBinding;

/**
 * Home page for scheduler
 */
public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;
    private MainActivity classActivity;
    private int currentPage = 0;
    private Spinner filterSpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        classActivity = (MainActivity)getActivity();
        if(savedInstanceState == null) {
            binding = FragmentClassesBinding.inflate(inflater, container, false);
        }
        binding = FragmentClassesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    private void setUpFilter(View view) {
        filterSpinner = (Spinner) view.findViewById(R.id.filter_classes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                classActivity.getApplicationContext(),
                R.array.filter_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.simpleListView.setAdapter(classActivity.getClassAdapter());

        setUpFilter(view);

        if(classActivity.getIsTaskList()) {
            setCurrentPage(1);
            buttonNamesOfTasks();
        } else if (classActivity.getIsCompletedList()) {
            setCurrentPage(2);
            buttonNamesOfCompletedTasks();
        }

        binding.addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage == 0) {
                    NavHostFragment.findNavController(ClassesFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                } else {
                    NavHostFragment.findNavController(ClassesFragment.this)
                            .navigate(R.id.action_FirstFragment_to_addAssignmentFragment);
                }
            }
        });
        sortButtons();
        switchButton();
        binding.editContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        classActivity.getClassAdapter().updateValues();
                        if (binding.editContent.getText().equals("Edit Class")) {
                            NavHostFragment.findNavController(ClassesFragment.this)
                                    .navigate(R.id.action_FirstFragment_to_editClass);
                        } else {
                            NavHostFragment.findNavController(ClassesFragment.this)
                                    .navigate(R.id.action_FirstFragment_to_editTask);
                        }
                    }
                });


        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                classActivity.getClassAdapter().setFilter(position - 1);
                classActivity.getClassAdapter().updateValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                classActivity.getClassAdapter().setFilter(-1);
            }

        });

    }
    private void sortButtons() {
        binding.sort1.setOnClickListener((View.OnClickListener) view12 -> {
            ListItem.setSortingMethod(1);
            sortItems();
        });

        binding.sort2.setOnClickListener((View.OnClickListener) view1 -> {
            ListItem.setSortingMethod(2);
            sortItems();
        });
        binding.sort3.setOnClickListener((View.OnClickListener) view1 -> {
            ListItem.setSortingMethod(3);
            sortItems();
        });
    }
    private void sortItems() {
        classActivity.getClassList().sort();
        classActivity.getTasksList().sort();
        classActivity.getClassAdapter().updateValues();
    }
    private void switchButton() {
        binding.toClassButton.setOnClickListener((View.OnClickListener) view13 -> {
            classActivity.swapToClass();
            setCurrentPage(0);
        });
        binding.toTaskButton.setOnClickListener((View.OnClickListener) view13 -> {
            classActivity.swapToTasks();
            setCurrentPage(1);
        });
        binding.toCompletedTaskButton.setOnClickListener((View.OnClickListener) view13 -> {
            classActivity.swapToCompletedTasks();
            setCurrentPage(2);
        });
    }
    private void buttonNamesOfTasks () {
        binding.sort1.setText("Sort by Task Class");
        binding.sort2.setText("Sort by Task Time");
        binding.addClass.setText("Add Task");
        binding.editContent.setText("Edit Task");
        binding.sort3.setVisibility(View.VISIBLE);
        binding.addClass.setVisibility(View.VISIBLE);
        binding.editContent.setVisibility(View.VISIBLE);
        binding.filterClasses.setVisibility(View.VISIBLE);
    }
    private void buttonNamesOfClass () {
        binding.sort1.setText("Sort by Class Name");
        binding.sort2.setText("Sort by Class Time");
        binding.addClass.setText("Add Class");
        binding.editContent.setText("Edit Class");
        binding.sort3.setVisibility(View.GONE);
        binding.addClass.setVisibility(View.VISIBLE);
        binding.editContent.setVisibility(View.VISIBLE);
        binding.filterClasses.setVisibility(View.GONE);
    }
    private void buttonNamesOfCompletedTasks () {
        binding.sort1.setText("Sort by Task Class");
        binding.sort2.setText("Sort by Task Time");
        binding.sort3.setVisibility(View.VISIBLE);
        binding.addClass.setVisibility(View.GONE);
        binding.editContent.setVisibility(View.GONE);
        binding.filterClasses.setVisibility(View.GONE);
    }
        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if (currentPage == 0) {
            buttonNamesOfClass();
        } else if (currentPage == 1) {
            buttonNamesOfTasks();
        } else {
            buttonNamesOfCompletedTasks();
        }
    }
}