package com.example.CollegeScheduler;

import android.graphics.Color;

public abstract class ListItem implements Comparable<ListItem>{
    private static final int[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};
    private int color;
    private static int currentID  = 0;
    private int ID;
    private static int sortingMethod;
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

    public static void setSortingMethod(int sortingMethod) {
        ListItem.sortingMethod = sortingMethod;
    }
}
