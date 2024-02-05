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


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
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
     * switch the adapter's list to classList
     */
    public void swapToClass(){
        getClassAdapter().setItemsList(getClassList());
        isTaskList = false;
        isCompletedList = false;
    }

    /**
     * switch the adapter's list to tasksList
     */
    public void swapToTasks() {
        getClassAdapter().setItemsList(getTasksList());
        isTaskList = true;
        isCompletedList = false;
    }

    public void swapToCompletedTasks() {
        getClassAdapter().setItemsList(transferList);
        isTaskList = false;
        isCompletedList = true;
    }

    public boolean getIsTaskList() {
        return isTaskList;
    }

    public boolean getIsCompletedList() {
        return isCompletedList;
    }

}