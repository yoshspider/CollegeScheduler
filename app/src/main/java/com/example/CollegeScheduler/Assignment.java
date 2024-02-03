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
    private int priority;

    /**
     *
     * Base constructor for assignment
     * @param assignmentName name of assignment
     * @param classOfAssignment class of assignment
     * @param time time assignment is due
     */
    public Assignment(String assignmentName, Class classOfAssignment, Calendar time, int priortiy) {
        super(time);
        this.assignmentName = assignmentName;
        this.classOfAssignment = classOfAssignment;
        setNameSort(classOfAssignment.getClassName() + "1" + assignmentName);
        setColor(classOfAssignment.getColor());
    }
    /**
     * Getter for Assignment Name
     * @return the name of the Assignment
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Getter for class of Assignment
     * @return the class object of Assignment
     */
    public Class getClassOfAssignment() {
        return classOfAssignment;
    }

    /**
     * Setter for Assignment Name
     * @param assignmentName New Name for Assignment
     */
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    /**
     * Setter for Class of Assignment
     * @param classOfAssignment New Class Object for Assignment
     */
    public void setClassOfAssignment(Class classOfAssignment) {
        this.classOfAssignment = classOfAssignment;
    }

    /**
     * Draw Instructions for Assignment
     * @param view View of which the assignment will be draw
     * @param inflater LayoutInflater that clears View
     * @param i Index of Assignment Object in
     * @param adapter the ClassAdapter Object that the item is being drawn with
     * @return the modified View object
     */
    @Override
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String date = getTime();
        int splitIndex = date.indexOf(" ");
        String[] values = {getAssignmentName(), date,
                getClassOfAssignment().getClassName(), getPriorities()[priority]};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

}