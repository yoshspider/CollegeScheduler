package com.example.CollegeScheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
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
//                classActivity.classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900, "CULC 250"));
//                classActivity.classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000, "CULC 250"));
//                classActivity.classAdapter.updateValues();
//                classActivity.classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900, "CLOUGH 302"));
//                classActivity.classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000, "SKILES 272"));
//                classActivity.classList.addItem(new Class("Objects and Design", "Pedro", new boolean[]{false, true, false, true, false}, 1230,1430, "HOWEY A419"));
//
//
//                Calendar ab = new GregorianCalendar(2003,3,5,5,30);
//                classActivity.tasksList.addItem(new Assignment("Project 1",(Class)classActivity.classList.getItem(0), ab ));
//                Calendar a = new GregorianCalendar(2023, 1, 25, 6, 30);
//                classActivity.tasksList.addItem(new Exam((Class) classActivity.classList.getItem(1) , "Quiz 1", "quiz on bits and stuff", "IC 211", a));
//
//                classActivity.classAdapter.updateValues();

                NavHostFragment.findNavController(ClassesFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        sortButtons();
        switchButton();
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
        binding.switchItems.setOnClickListener((View.OnClickListener) view13 -> {
            if(currentPage == 0) {
                classActivity.swapToTasks();
                buttonNamesOfClass();
                currentPage++;
            } else if (currentPage == 1){
                classActivity.swapToCompletedTasks();
                buttonNamesOfCompletedTasks();
                currentPage++;
            } else {
                classActivity.swapToClass();
                buttonNamesOfTasks();
                currentPage = 0;
            }
        });
    }
    private void buttonNamesOfClass () {
        binding.switchItems.setText("Switch to Completed Task List");
        binding.sort1.setText("Sort by Class of Task");
        binding.sort2.setText("Sort by Time of Task");
        binding.addplaceholder.setText("Add Task");
    }
    private void buttonNamesOfTasks () {
        binding.switchItems.setText("Switch to Task List");
        binding.sort1.setText("Sort by Class Name");
        binding.sort2.setText("Sort by Class Time");
        binding.addplaceholder.setText("Add Class");
    }
    private void buttonNamesOfCompletedTasks () {
        binding.switchItems.setText("Switch to Class List");
        binding.sort1.setText("Sort by Class of Task");
        binding.sort2.setText("Sort by Time of Task");
        binding.addplaceholder.setText("Add Completed Task");
    }
        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}