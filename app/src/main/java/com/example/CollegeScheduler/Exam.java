package com.example.CollegeScheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

public class Exam extends ListItem{
    private Class theClass;
    private String examName;
    private String details;
    private String location;
    private Calendar calendar;

    public Exam(Class theClass, String examName, String details, String location, Calendar calendar) {
        this.calendar = calendar;
        this.theClass = theClass;
        this.examName = examName;
        this.details = details;
        this.location = location;
        setColor(this.theClass.getColor());
        setNameSort(this.theClass.getClassName() + "1" + examName);
    }


    @Override
    public View drawScreen(View view, ViewGroup viewGroup, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = super.drawScreen(view, viewGroup, inflater, i, adapter);
        String date = super.getTime();
        int splitIndex = date.indexOf(" ");
        String[] values = {getExamName(), getClassName().getClassName(),
                date.substring(0, splitIndex), date.substring(splitIndex + 1)};
        view = drawInformation(view, values);
        view = drawButtons(view, inflater, i, adapter);
        return view;
    }


    //--------------------Creating getters and setters--------------------\\
    public Class getClassName() {
        return this.theClass;
    }

    public void setClassName(Class c) {
        this.theClass = c;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
