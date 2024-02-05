package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;

public class Class extends ListItem  {
    private String className;
    private String professorName;
    private boolean[] meetingDates;
    private int startTime;
    private int endTime;
    private String location;

    /**
     * Constructor for the class object
     * @param className name of the class
     * @param professorName name of the professor
     * @param meetingDates days of the week the class meets as a 5 element boolean array
     * @param startTime integer start time
     * @param endTime integer end time
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
     * Getter for name of class
     * @return the name of the class
     */
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * Getter for the name of the professor
     * @return name of the professor
     */
    public String getProfessorName() {
        return professorName;
    }
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    /**
     * Getter for the meeting dates
     * @return 5 element boolean array of which days the class meets
     */
    public boolean[] getMeetingDates() {
        return meetingDates;
    }

    /**
     * Setter for meeting date array
     * @param meetingDates 5 element boolean array corresponding to weekdays when class meets
     */
    public void setMeetingDates(boolean[] meetingDates) {
        this.meetingDates = meetingDates;
    }
    /**
     * Getter for the start time of the class
     * @return start time as an int
     */
    public int getStartTime() {
        return startTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    /**
     * Getter for the end time of the class
     * @return end time as an int
     */
    public int getEndTime() {
        return endTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Draw method to show visual parts of the object
     * @param view view associated with list of classes
     * @param inflater inflater to display view
     * @param i iterator variable for drawing list item buttons
     * @param adapter adapter passed in from activity
     * @return view that was customized and inflated
     */
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, inflater, i, adapter);
        String[] values = {getClassName(), meetingString(), getProfessorName(), getLocation()};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }

    /**
     * Meeting string converter to give to view
     * @return corrected string
     */
    public String meetingString() {
        return daysOfTheWeekConverter(getMeetingDates()) + " " + timeConverter(getStartTime())
                + " - " + timeConverter(getEndTime());
    }

    /**
     * Convert boolean to abbreviations for days of the week
     * @param days boolean array corresponding to the class's meeting days
     * @return string representing abbreviations of selected days
     */
    public String daysOfTheWeekConverter(boolean[] days) {
        String week = (days[0] ? "M/":"") + (days[1] ? "T/":"") + (days[2] ? "W/":"") + (days[3] ? "TR/":"") + (days[4] ? "F/":"");
        return week.substring(0, week.length()-1);
    }

    /**
     * Return class name when toString() method called
     * @return name of class
     */
    @Override
    public String toString() {
        return className;
    }
}