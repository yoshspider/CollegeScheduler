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
    private int startTime;
    private int endTime;

    public Class(String className, String professorName, boolean[] meetingDates, int startTime, int endTime) {
        this.className = className;
        this.professorName = professorName;
        this.meetingDates = meetingDates;
        this.startTime = startTime;
        this.endTime = endTime;
        setNameSort(className);
        setTimeSort(startTime);
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
        view = inflater.inflate(R.layout.activity_listview, null);


        TextView topTextView = view.findViewById(R.id.topTextView);
        TextView middleTextView = view.findViewById(R.id.middleTextView);
        TextView bottomLeftTextView = view.findViewById(R.id.bottomLeftTextView);
        TextView bottomRightTextView = view.findViewById(R.id.bottomRightTextView);
        View background = view.findViewById(R.id.backgroundView);

        topTextView.setText(getClassName());
        middleTextView.setText(getProfessorName());
        bottomLeftTextView.setText(daysOfTheWeekConverter(getMeetingDates()));
        String time = timeConverter(getStartTime()) + " - "
                + timeConverter(getEndTime());
        bottomRightTextView.setText(time);


        background.setBackgroundColor(getColor());
        view = drawButtons(view, inflater, i, adapter);

        return view;
    }
}
