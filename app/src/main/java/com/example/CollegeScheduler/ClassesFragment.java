package com.example.CollegeScheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.CollegeScheduler.databinding.FragmentClassesBinding;

public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;
    private MainActivity classActivity;
    private int currentPage = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        classActivity = (MainActivity)getActivity();
        if(savedInstanceState == null) {
            binding = FragmentClassesBinding.inflate(inflater, container, false);
        }
        binding = FragmentClassesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.simpleListView.setAdapter(classActivity.getClassAdapter());
        binding.simpleListView.setAdapter(classActivity.getClassAdapter());
        binding.addplaceholder.setOnClickListener(new View.OnClickListener() {
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
        binding.editContent.setOnClickListener(view1 -> NavHostFragment.findNavController(ClassesFragment.this)
                .navigate(R.id.action_FirstFragment_to_editClass));
        setCurrentPage(0);

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
    private void buttonNamesOfClass () {
        binding.sort1.setText("Sort by Class of Task");
        binding.sort2.setText("Sort by Time of Task");
        binding.addplaceholder.setText("Add Task");
    }
    private void buttonNamesOfTasks () {
        binding.sort1.setText("Sort by Class Name");
        binding.sort2.setText("Sort by Class Time");
        binding.addplaceholder.setText("Add Class");
    }
    private void buttonNamesOfCompletedTasks () {
        binding.sort1.setText("Sort by Class of Task");
        binding.sort2.setText("Sort by Time of Task");
        binding.addplaceholder.setText("Add Completed Task");
    }
        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if (currentPage == 0) {
            buttonNamesOfTasks();
        } else if (currentPage == 1) {
            buttonNamesOfClass();
        } else {
            buttonNamesOfCompletedTasks();
        }
    }
}