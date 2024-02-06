package com.example.CollegeScheduler;

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
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.15
 * Classes Fragment defines the functionality for the main screen
 * and navigation buttons to different screens and lists
 * this is the home page and defines all the sorting,
 * navigation, and filtering buttons.
 *
 */
public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;
    private MainActivity classActivity;
    private int currentPage = 0;
    private Spinner filterSpinner;

    /**
     * Inflate view and associate with correct binding
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return view created
     */
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
    /**
     * Set up UI elements and button listeners once view has been set up
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.simpleListView.setAdapter(classActivity.getClassAdapter());

        setUpFilter(view);

        if(classActivity.getIsTaskList()) {
            setCurrentPage(1);
            buttonNamesOfTasks();
            classActivity.getClassAdapter().setFilter(-1);
        } else if (classActivity.getIsCompletedList()) {
            setCurrentPage(2);
            buttonNamesOfCompletedTasks();
            classActivity.getClassAdapter().setFilter(-1);
        }

        binding.addClass.setOnClickListener(new View.OnClickListener() {
            /**
             * Navigate to add screen for both classes and tasks
             * @param view The view that was clicked.
             */
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
            /**
             * Navigate to edit screen for both tasks and classes
             * @param view The view that was clicked.
             */
            @Override
                    public void onClick(View view) {
                        classActivity.getClassAdapter().updateValues();
                        System.out.println(binding.editContent.getText());
                        if (binding.editContent.getText().equals("Edit Class")) {
                            System.out.println("here!");
                            NavHostFragment.findNavController(ClassesFragment.this)
                                    .navigate(R.id.action_FirstFragment_to_editClass);
                        } else if (binding.editContent.getText().equals("Edit Task")){
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

    /**
     * Select different sorting method based on which sort button selected
     */
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

    /**
     * Sort list items
     */
    private void sortItems() {
        classActivity.getClassList().sort();
        classActivity.getTasksList().sort();
        classActivity.getClassAdapter().updateValues();
    }

    /**
     * Handle switching from one display screen to another
     */
    private void switchButton() {
        binding.toClassButton.setOnClickListener((View.OnClickListener) view13 -> {
            classActivity.swapToClass();
            setCurrentPage(0);
        });
        binding.toTaskButton.setOnClickListener((View.OnClickListener) view13 -> {
            classActivity.swapToTasks();
            setCurrentPage(1);
            classActivity.getClassAdapter().setFilter(-1);
        });
        binding.toCompletedTaskButton.setOnClickListener((View.OnClickListener) view13 -> {
            classActivity.swapToCompletedTasks();
            setCurrentPage(2);
            classActivity.getClassAdapter().setFilter(-1);
        });
    }

    /**
     * Set button labels and visibility for tasks screen
     */
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

    /**
     * Set button labels and visibility for class screen
     */
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

    /**
     * Set button labels and visibility for completed task screen
     */
    private void buttonNamesOfCompletedTasks () {
        binding.sort1.setText("Sort by Task Class");
        binding.sort2.setText("Sort by Task Time");
        binding.sort3.setVisibility(View.VISIBLE);
        binding.addClass.setVisibility(View.GONE);
        binding.editContent.setVisibility(View.GONE);
        binding.filterClasses.setVisibility(View.GONE);
    }

    /**
     * Handle destroy view during navigation
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Set buttons labels/visibility based on which page is shown
     * @param currentPage selected page (classes, tasks, or completed tasks)
     */
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