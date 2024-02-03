package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import java.util.Calendar;

public class Task extends ListItem{
    private String name;
    private Class classOfTask;
    private int priority;
    private String location;
    private int type;
    public static String[] priorities = {"High", "Medium", "Low"};
    public static String[] types = {"Assignment", "Exam", "Task"};

    public Task (int type, String name, Calendar dueDate, Class classOfTask, int priority, String location) {
        super(dueDate);
        this.name = name;
        this.classOfTask = classOfTask;
        setColor(this.classOfTask.getColor());
        setNameSort(this.classOfTask.getClassName() + "1" + name);
        this.priority = priority;
        this.type = type;
        this.location = location;
    }

    @Override
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String date = getTime();
        String[] values = {getName(), classOfTask + " " + types[type], date, priorities[priority] + " Priority"};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

    @Override
    public View drawButtons(View view, LayoutInflater inflater, int i, ClassAdapter adapter){
        view = super.drawButtons(view, inflater, i, adapter);
        ImageButton checkCompleteButton = view.findViewById(R.id.checkCompleteButton);
        checkCompleteButton.setOnClickListener(buttonView -> adapter.transferItem(i));
        return view;
    }

    //--------------------Creating getters and setters--------------------\\
    public String getName() {
        if(location == null || location.equals("")) {
            return name;
        } else {
            return name + " at " + location;
        }

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