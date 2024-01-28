package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;

import java.util.Calendar;

public class Task extends ListItem{
    private String name;
    private Class theClass;
    private boolean complete;

    public Task (String name, Calendar dueDate, Class theClass, boolean complete) {
        super(dueDate);
        this.name = name;
        this.theClass = theClass;
        this.complete = complete;
        setColor(this.theClass.getColor());
        setNameSort(this.theClass.getClassName() + "1" + name);
    }

    @Override
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String date = super.getTime();
        int splitIndex = date.indexOf(" ");
        String[] values = {getName(), getTheClass().getClassName(),
                date.substring(0, splitIndex), date.substring(splitIndex + 1)};
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
        return theClass;
    }

    public void setTheClass(Class theClass) {
        this.theClass = theClass;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}