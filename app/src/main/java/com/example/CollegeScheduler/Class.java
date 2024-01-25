package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Class extends ListItem  {
    private String className;
    private String professorName;
    private boolean[] meetingDates;

    private int dayOfWeek;
    private int startTime;
    private int endTime;

    /**
     * Constructor for the class object
     * @param className
     * @param professorName
     * @param meetingDates
     * @param startTime
     * @param endTime
     */
    public Class(String className, String professorName, boolean[] meetingDates, int startTime, int endTime) {
        this.className = className;
        this.professorName = professorName;
        this.meetingDates = meetingDates;
        this.startTime = startTime;
        this.endTime = endTime;
        setNameSort(className);
        setClockTime(startTime, endTime);
    }


    /**
     * getter for name of class
     * @return the name of the class
     */
    public String getClassName() {
        return className;
    }

    /**
     * getter for the name of the professor
     * @return name of the professor
     */
    public String getProfessorName() {
        return professorName;
    }

    /**
     * getter for the meeting dates
     * @return 5 length boolean array of which days we meet
     */
    public boolean[] getMeetingDates() {
        return meetingDates;
    }

    /**
     * getter for the start time of the class
     * @return
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * getter for the end time of the class
     * @return
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * draw method to show visual parts of the object
     * @param view view it will be drawn on
     * @param viewGroup
     * @param inflater
     * @param i
     * @param adapter
     * @return
     */
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String[] values = {getClassName(), getProfessorName(), daysOfTheWeekConverter(getMeetingDates()),
                (timeConverter(getStartTime()) + " - " + timeConverter(getEndTime()))};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }
}
