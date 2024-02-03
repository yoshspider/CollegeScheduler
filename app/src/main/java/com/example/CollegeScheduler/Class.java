package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class Class extends ListItem  {
    private String className;
    private String professorName;
    private boolean[] meetingDates;
    private int startTime;
    private int endTime;
    private String location;

    /**
     * Constructor for the class object
     * @param className
     * @param professorName
     * @param meetingDates
     * @param startTime
     * @param endTime
     */
    public Class(String className, String professorName, boolean[] meetingDates, int startTime, int endTime, String location) {
        this.className = className;
        this.professorName = professorName;
        this.meetingDates = meetingDates;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        setNameSort(className);
        setClockTime(startTime, endTime);
    }
    public Class() {
        this("Default Class", "John Doe", new boolean[]{true, false, true, false, true}, 930, 1030, "BUIDLING 100");
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

    public String getLocation() {
        return location;
    }

    /**
     * draw method to show visual parts of the object
     * @param view view it will be drawn on
     * @param inflater
     * @param i
     * @param adapter
     * @return
     */
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String[] values = {getClassName(), meetingString(), getProfessorName(), getLocation()};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

    /**
     * meeting string converter to give to view
     * @return corrected string
     */
    public String meetingString() {
        return daysOfTheWeekConverter(getMeetingDates()) + " " + timeConverter(getStartTime())
                + " - " + timeConverter(getEndTime());
    }
//    public String timeConverter(int time) {
//        return String.format("%01d:%02d", (time > 1200 ? time/100 - 11 : time/100), time%100)  + (time > 1200 ? " PM" : " AM");
//    }
    public String daysOfTheWeekConverter(boolean[] days) {
        String week = (days[0] ? "M/":"") + (days[1] ? "T/":"") + (days[2] ? "W/":"") + (days[3] ? "TR/":"") + (days[4] ? "F/":"");
        return week.substring(0, week.length()-1);
    }

    @Override
    public String toString() {
        return className;
    }
}