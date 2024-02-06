package com.example.CollegeScheduler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.CollegeScheduler.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.12
 * 
 */

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ListView simpleList;

    //make private with getters & setters?
    private final CollegeObjectList<ListItem> classList = new CollegeObjectList<>();
    private final CollegeObjectList<ListItem> tasksList = new CollegeObjectList<>();
    public static final CollegeObjectList<ListItem> transferList = new CollegeObjectList<>();
    private ClassAdapter classAdapter;
    private boolean isTaskList;
    private boolean isCompletedList;

    /**
     * Inflate layout and initialize UI elements
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        simpleList = findViewById(R.id.simpleListView);
        classAdapter = new ClassAdapter(getApplicationContext(), classList);
        simpleList.setAdapter(classAdapter);
    }

    /**
     * getter for classAdapter
     * @return currentClassAdapter
     */
    public ClassAdapter getClassAdapter() {
        return classAdapter;
    }

    /**
     * getter for classList
     * @return currentClassList
     */
    public CollegeObjectList<ListItem> getClassList() {
        return classList;
    }

    /**
     * getter for taskslist
     * @return currentTasksList
     */
    public CollegeObjectList<ListItem> getTasksList() {
        return tasksList;
    }

    /**
     * Switch the adapter's list to classList
     */
    public void swapToClass(){
        getClassAdapter().setItemsList(getClassList());
        isTaskList = false;
        isCompletedList = false;
    }

    /**
     * Switch the adapter's list to tasksList
     */
    public void swapToTasks() {
        getClassAdapter().setItemsList(getTasksList());
        isTaskList = true;
        isCompletedList = false;
    }

    /**
     * Switch the adapter's list to completed tasks
     */
    public void swapToCompletedTasks() {
        getClassAdapter().setItemsList(transferList);
        isTaskList = false;
        isCompletedList = true;
    }

    /**
     * Check if screen is on tasks list for updating button visibility/labels
     * @return true if tasks list is shown
     */
    public boolean getIsTaskList() {
        return isTaskList;
    }

    /**
     * Check if screen is on completed tasks list for updating button visibility/labels
     * @return true if completed tasks list is shown
     */
    public boolean getIsCompletedList() {
        return isCompletedList;
    }

}