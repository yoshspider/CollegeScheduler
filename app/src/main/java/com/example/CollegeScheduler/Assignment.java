package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;

public class Assignment extends ListItem {
    private String assignmentName;
    private Class classOfAssignment;



    public Assignment(String assignmentName, Class classOfAssignment, int yearDue, int monthDue, int dayDue, int hourDue, int minuteDue) {
        super(yearDue, monthDue, dayDue, hourDue, minuteDue);
        this.assignmentName = assignmentName;
        this.classOfAssignment = classOfAssignment;
        setNameSort(classOfAssignment.getClassName() + "1" + assignmentName);
        setColor(classOfAssignment.getColor());


    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public Class getClassOfAssignment() {
        return classOfAssignment;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public void setClassOfAssignment(Class classOfAssignment) {
        this.classOfAssignment = classOfAssignment;
    }


    @Override
    public View drawScreen(View view, ViewGroup viewGroup, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, viewGroup, inflater, i, adapter);
        String date = getTime();
        int splitIndex = date.indexOf(" ");
        String[] values = {getAssignmentName(), getClassOfAssignment().getClassName(),
                date.substring(0, splitIndex), date.substring(splitIndex + 1)};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

}
