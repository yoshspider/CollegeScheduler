package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import java.util.Calendar;
/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.09
 * Class defines the behaviors of a Task object
 * including all parameters that would normally be associated with a task
 * all setters and getters, draw behavior for a listview to be able to
 * showcase all these details on a view
 */
public class Task extends ListItem{
    private String name;
    private Class classOfTask;
    private int priority;
    private String location;
    private int type;
    public static String[] priorities = {"High", "Medium", "Low"};
    public static String[] types = {"Assignment", "Exam", "Task"};

    /**
     * Base Constructor for Task
     * @param type the type of the task: assignment, exam, task
     * @param name name of the task
     * @param dueDate when the task is due
     * @param classOfTask the associated class of task
     * @param priority the priority of task: high, medium, low
     * @param location location of task that needs to be performed
     */
    public Task (int type, String name, Calendar dueDate, Class classOfTask, int priority, String location) {
        super(dueDate);
        this.name = name;
        this.classOfTask = classOfTask;
        setColor(this.classOfTask.getColor());
        if (classOfTask != null) {
            setNameSort(this.classOfTask.getClassName() + "1" + name);
        } else {
            setNameSort("!!!" + name);
        }
        this.priority = priority;
        this.type = type;
        this.location = location;
        setPriority(priority);
    }
    /**
     * instructions to draw the element of a task
     * @param view current view
     * @param inflater inflater to reset
     * @param i index of item
     * @param adapter adapter to add items
     * @return view updated with task information
     */
    @Override
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String date = getTime();
        String[] values = {getName(), classOfTask + " " + types[type], date, priorities[priority] + " Priority"};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;

    }

    /**
     * adds functionality to the buttons of the task
     * @param view view of item
     * @param inflater inflater to reset item
     * @param i index of item in adaptter
     * @param adapter adapter of list
     * @return
     */
    @Override
    public View drawButtons(View view, LayoutInflater inflater, int i, ClassAdapter adapter){
        view = super.drawButtons(view, inflater, i, adapter);
        ImageButton checkCompleteButton = view.findViewById(R.id.checkCompleteButton);
        checkCompleteButton.setVisibility(View.VISIBLE);
        checkCompleteButton.setOnClickListener(
                buttonView -> adapter.transferItem(i)
        );
        return view;
    }

    /**
     * getter for the name of task at which location
     * @return task name + at + location
     */
    public String getName() {
        if(location == null || location.equals("")) {
            return name;
        } else {
            return name + " at " + location;
        }

    }

    /**
     * getter for just the name of the task
     * @return name of task
     */
    public String getOnlyName() {
        return name;
    }

    /**
     * setter for the name of the task
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the class object
     * @return the class object of task
     */
    public Class getTheClass() {
        return classOfTask;
    }

    /**
     * setter for the class object
     * @param theClass new class
     */
    public void setTheClass(Class theClass) {
        this.classOfTask = theClass;
    }

    /**
     * getter for location of task
     * @return the location where the task may take place
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * setter for the location of a task
     * @param loc new location
     */
    public void setLocation(String loc) {
        this.location = loc;
    }

    /**
     * getter for the type of the task
     * @return task type
     */
    public int getType() {
        return this.type;
    }

    /**
     * setter for the type of task
     * @param type new type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * getter for the priority of task
     * @return task priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * setter for the priority
     * @param priority new priority value
     */
    public void setPriority(int priority){
        this.priority = priority;
        super.setPriority(priority);
    }

    /**
     * filters out data for any adapter
     * @param filter the tool to decide whether item shouldnt be filtered
     * @return whether the item shouldnt be filtered
     */
    @Override
    public boolean filterValue(int filter) {
        return (filter == type || filter == -1);
    }
}