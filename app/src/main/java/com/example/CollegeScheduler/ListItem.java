package com.example.CollegeScheduler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    public abstract View drawScreen(View view, ViewGroup viewGroup, LayoutInflater inflater, int i, ClassAdapter adapter);
    public View drawButtons(View view, LayoutInflater inflater, int i, ClassAdapter adapter) {
        Button button = view.findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View buttonView) {
                adapter.remove(i);
            }
        });
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
}
