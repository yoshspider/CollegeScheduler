package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Assignment extends ListItem {
    private String assignmentName;
    private Class classOfAssignment;
    private int monthDue;
    private int dayDue;

    public Assignment(String assignmentName, Class classOfAssignment, int monthDue, int dayDue) {
        this.assignmentName = assignmentName;
        this.classOfAssignment = classOfAssignment;
        this.dayDue = dayDue;
        this.monthDue = monthDue;
        setNameSort(classOfAssignment.getClassName() + "1" + assignmentName);
        setTimeSort(dayDue);
        setColor(classOfAssignment.getColor());
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public Class getClassOfAssignment() {
        return classOfAssignment;
    }

    public int getDayDue() {
        return dayDue;
    }

    public int getMonthDue() {
        return monthDue;
    }


    @Override
    public View drawScreen(View view, ViewGroup viewGroup, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, viewGroup, inflater, i, adapter);
        String[] values = {getAssignmentName(), getClassOfAssignment().getClassName(), Integer.toString(getMonthDue()), Integer.toString(getDayDue())};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

}
