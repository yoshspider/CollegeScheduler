package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;

import java.util.Calendar;

public class Task extends ListItem{
    private String name;
    private Class classOfTask;
    private int priority;

    public Task (String name, Calendar dueDate, Class classOfTask, int priority) {
        super(dueDate);
        this.name = name;
        this.classOfTask = classOfTask;
        setColor(this.classOfTask.getColor());
        setNameSort(this.classOfTask.getClassName() + "1" + name);
        this.priority = priority;
    }

    @Override
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String date = super.getTime();
        int splitIndex = date.indexOf(" ");
        String[] values = {getName(), date,
                getTheClass().getClassName(), getPriorities()[priority]};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

    //--------------------Creating getters and setters--------------------\\
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getTheClass() {
        return classOfTask;
    }

    public void setTheClass(Class theClass) {
        this.classOfTask = theClass;
    }

}