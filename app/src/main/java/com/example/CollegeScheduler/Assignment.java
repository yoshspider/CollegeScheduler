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

    /**
     * Base Constructor for Assignment
     * @param assignmentName Name of Assignment
     * @param classOfAssignment Associated Class Object of Assignment
     * @param yearDue Year the Assignment is Due
     * @param monthDue Month the Assignment is Due
     * @param dayDue Day the Assignment is Due
     * @param hourDue Hour the Assignment is Due
     * @param minuteDue Minute the Assignment is Due
     */
    public Assignment(String assignmentName, Class classOfAssignment, Calendar time) {
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
     * @param viewGroup ViewGroup that View belongs to
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
        String[] values = {getAssignmentName(), getClassOfAssignment().getClassName(),
                date.substring(0, splitIndex), date.substring(splitIndex + 1)};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

}
