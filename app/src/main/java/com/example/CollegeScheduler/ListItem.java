package com.example.CollegeScheduler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public abstract class ListItem implements Comparable<ListItem>{
    private static final int[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};
    private int color;
    private static int currentID  = 0;
    private int ID;
    private static int sortingMethod;
    private int timeSort;
    private String nameSort;
    public ListItem() {
        ID = currentID;
        currentID++;
        color = colors[ID % colors.length];
        sortingMethod = 1;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public static int getSortingMethod() {
        return sortingMethod;
    }

    public void setTimeSort(int timeSort) {
        this.timeSort = timeSort;
    }

    public void setNameSort(String nameSort) {
        this.nameSort = nameSort;
    }

    public int getTimeSort() {
        return timeSort;
    }

    public String getNameSort() {
        return nameSort;
    }

    public static void setSortingMethod(int sortingMethod) {
        ListItem.sortingMethod = sortingMethod;
    }
    public String timeConverter(int time) {
        return String.format("%01d:%02d", time/100, time%100)  + (time > 1200 ? " PM" : " AM");
    }
    public String daysOfTheWeekConverter(boolean[] days) {
        String week = (days[0] ? "M/":"") + (days[1] ? "T/":"") + (days[2] ? "W/":"") + (days[3] ? "TR/":"") + (days[4] ? "F/":"");
        return week.substring(0, week.length()-1);
    }
    public View drawScreen(View view, ViewGroup viewGroup, LayoutInflater inflater, int i, ClassAdapter adapter) {
        view = inflater.inflate(R.layout.activity_listview, null);
        View background = view.findViewById(R.id.backgroundView);
        background.setBackgroundColor(getColor());
        return view;
    }
    public View drawButtons(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        Button deleteButton = view.findViewById(R.id.delete);
        Button modifyButton = view.findViewById(R.id.modify);
        deleteButton.setOnClickListener(buttonView -> adapter.remove(i));
        return view;
    }
    @Override
    public int compareTo(ListItem o) {
        if (getSortingMethod() == 1) {
            return nameSort.compareTo(o.getNameSort());
        } else {
            return timeSort - o.getTimeSort();
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
