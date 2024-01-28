package com.example.CollegeScheduler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class ListItem implements Comparable<ListItem>{
    private static final int[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA, Color.CYAN};
    private int color;
    private static int currentID  = 0;
    private int ID;
    private static int sortingMethod;
    private String nameSort;
    private Calendar calendarDate;

    /**
     * Default Constructor Assuming CalendarDate is Today
     */
    public ListItem() {
        ID = currentID;
        currentID++;
        color = colors[ID % colors.length];
        sortingMethod = 1;
        calendarDate = Calendar.getInstance();
    }

    public ListItem(Calendar time) {
        this();
        this.calendarDate = time;
    }

    /**
     * Creates Calendar Object with given hour and minute
     * @param hour new hour
     * @param minute new minute
     */
    public void setClockTime(int hour, int minute) {
        calendarDate.set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH, Calendar.DATE, hour, minute);
    }

    /**
     * shift the Calender Day by a number of days
     * @param dayOfWeek shift days
     */
    public void shiftDate(int dayOfWeek) {
        while (calendarDate.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            calendarDate.add(Calendar.DATE, 1);
        }
    }

    /**
     * Getter for Color
     * @return the color of the ListItem object
     */
    public int getColor() {
        return color;
    }

    /**
     * Setter for Color
     * @param color new Color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Getter for current Sorting Method
     * @return the sortingMethod
     */
    public static int getSortingMethod() {
        return sortingMethod;
    }

    /**
     * Setter for NameSort
     * @param nameSort new NameSort
     */
    public void setNameSort(String nameSort) {
        this.nameSort = nameSort;
    }

    /**
     * Getter for NameSort
     * @return current String that is used for sorting by name
     */
    public String getNameSort() {
        return nameSort;
    }

    /**
     * getter for ID
     * @return the id of ListItem object
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter for time
     * @return a formatted String time based on calendar
     */
    public String getTime() {
        Date fullTime = calendarDate.getTime();
        DateFormat format = new SimpleDateFormat("M/d/yyyy h:mm a");
        return format.format(fullTime);

    }

    /**
     * Getter for the Calendar object
     * @return the Calendar object that has the set time for ListItem
     */
    public Calendar getCalendar() {
        return calendarDate;
    }
    public void setCalendar(Calendar d) {
        this.calendarDate = d;
    }

    /**
     * Setter for the sorting method currently used by all ListItem objects
     * @param sortingMethod the new sorting method we want to use
     */
    public static void setSortingMethod(int sortingMethod) {
        ListItem.sortingMethod = sortingMethod;
    }


    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = inflater.inflate(R.layout.activity_listview, null);
        View background = view.findViewById(R.id.backgroundView);
        background.setBackgroundColor(getColor());
        return view;
    }
    public View drawButtons(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        ImageButton deleteButton = view.findViewById(R.id.delete);
        ImageButton modifyButton = view.findViewById(R.id.modify);
        deleteButton.setOnClickListener(buttonView -> adapter.remove(i));
        return view;
    }
    @Override
    public int compareTo(ListItem o) {
        if (getSortingMethod() == 1) {
            return nameSort.compareTo(o.getNameSort());
        } else {
            return calendarDate.compareTo(o.getCalendar());
        }
    }

    public View drawInformation(View view, String[] values) {
        TextView topTextView = view.findViewById(R.id.topTextView);
        TextView middleTextView = view.findViewById(R.id.middleTextView);
        TextView bottomLeftTextView = view.findViewById(R.id.bottomLeftTextView);
        TextView bottomRightTextView = view.findViewById(R.id.bottomRightTextView);

        topTextView.setText(values[0]);
        middleTextView.setText(values[1]);
        bottomLeftTextView.setText(values[2]);
        bottomRightTextView.setText(values[3]);
        return view;
    }
}