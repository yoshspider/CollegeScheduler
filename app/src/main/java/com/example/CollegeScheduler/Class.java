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

    public Class(String className, String professorName, boolean[] meetingDates, int startTime, int endTime) {
        this.className = className;
        this.professorName = professorName;
        this.meetingDates = meetingDates;
        this.startTime = startTime;
        this.endTime = endTime;
        setNameSort(className);
        setClockTime(startTime, endTime);
    }



    public String getClassName() {
        return className;
    }
    public String getProfessorName() {
        return professorName;
    }
    public boolean[] getMeetingDates() {
        return meetingDates;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getEndTime() {
        return endTime;
    }



    public View drawScreen(View view, ViewGroup viewGroup, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, viewGroup, inflater, i, adapter);

        String[] values = {getClassName(), getProfessorName(), daysOfTheWeekConverter(getMeetingDates()),
                (timeConverter(getStartTime()) + " - " + timeConverter(getEndTime()))};


        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);

        return view;
    }
}
