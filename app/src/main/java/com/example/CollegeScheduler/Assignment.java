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
    }

//comment
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
        view = inflater.inflate(R.layout.activity_listview, null);


        TextView topTextView = view.findViewById(R.id.topTextView);
        TextView middleTextView = view.findViewById(R.id.middleTextView);
        TextView bottomLeftTextView = view.findViewById(R.id.bottomLeftTextView);
        TextView bottomRightTextView = view.findViewById(R.id.bottomRightTextView);
        View background = view.findViewById(R.id.backgroundView);

        topTextView.setText(getAssignmentName());
        middleTextView.setText(getClassOfAssignment().getClassName());
        bottomLeftTextView.setText(Integer.toString(getMonthDue()));

        bottomRightTextView.setText(Integer.toString(getDayDue()));


        background.setBackgroundColor(getColor());
        view = drawButtons(view, inflater, i, adapter);

        return view;
    }
}
