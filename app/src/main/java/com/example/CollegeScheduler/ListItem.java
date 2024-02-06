package com.example.CollegeScheduler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.12
 * ListItem is an abstract class that defines the methods
 * that subclasses should utilize in order to be drawn and
 * to store all object details for sorting and filtering.
 * Future classes can extend this in order to be able to be
 * used in our CollegeObjectList and ListView
 */
public abstract class ListItem implements Comparable<ListItem>, Filtering, ColorSetup, TimeConversion {
    private static int[] colors;
    private int color;
    private static int currentID  = 0;
    private int ID;
    private static int sortingMethod;
    private String nameSort;
    private int priority;
    private Calendar calendarDate;

    /**
     * Default Constructor Assuming CalendarDate is Today
     */
    public ListItem() {
        ID = currentID;
        if(currentID == 0) {
            colors = setColors();
        }
        currentID++;
        color = colors[ID % colors.length];
        sortingMethod = 1;
        calendarDate = Calendar.getInstance();
    }
    /**
     * Sets up list item with correct time
     * @param time time as calendar object
     */
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
     * Setter for priority
     * @param priority new priority value
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Getter for priority
     * @return current priority value
     */
    public int getPriority() {
        return priority;
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

    /**
     * setter for calendar object
     * @param d new calender
     */
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
    /**
     * draw screen method called to draw all components of this list item
     * @param view current view
     * @param inflater inflater to reset
     * @param i index of item
     * @param adapter adapter to add items
     * @return corrected view
     */
    public View drawScreen(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = inflater.inflate(R.layout.activity_listview, null);
        View background = view.findViewById(R.id.backgroundView);
        background.setBackgroundColor(getColor());
        return view;
    }
    /**
     * Text information of ListItem
     * @param view view to draw on
     * @param values the items to draw
     * @return corrected view with items
     */
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
    /**
     * Draws all the buttons and adds functionality
     * @param view view of item
     * @param inflater inflater to reset item
     * @param i index of item in adaptter
     * @param adapter adapter of list
     * @return corrected view with buttons
     */
    public View drawButtons(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        ImageButton deleteButton = view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(buttonView -> adapter.remove(i));
        ImageButton checkCompleteButton = view.findViewById(R.id.checkCompleteButton);
        checkCompleteButton.setVisibility(View.GONE);
        return view;
    }
    /**
     * Compare method for sorting
     * @param o the object to be compared.
     * @return integer value to determine whether greater or lesser
     */
    @Override
    public int compareTo(ListItem o) {
        if (getSortingMethod() == 1) {
            return nameSort.compareTo(o.getNameSort());
        } else if(getSortingMethod() == 2) {
            return calendarDate.compareTo(o.getCalendar());
        } else {
            return Integer.compare(priority, o.getPriority());
        }
    }
}