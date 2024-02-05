package com.example.CollegeScheduler;

import java.util.ArrayList;
import java.util.Collections;

public class CollegeObjectList<T extends Comparable<? super T>> {
    private ArrayList<T> itemList;

    /**
     * Constructor for a new CollegeObjectList data structure
     */
    public CollegeObjectList() {
        itemList = new ArrayList<>();
    }

    /**
     * Add Item to the end of the list
     * @param item new item to be added to the end of the lsit
     */
    public void addItem(T item) {
        itemList.add(item);
    }

    /**
     * Get the item at specific index
     * @param index index to be get item at
     * @return
     */
    public T getItem(int index) {
        return itemList.get(index);
    }

    /**
     * Remove the item at specific index
     * @param index index to remove item at
     */
    public T removeItem(int index) {
        return itemList.remove(index);
    }

    /**
     * Size of list
     * @return list size
     */
    public int size() {
        return itemList.size();
    }

    /**
     * Sorts list of items
     */
    public void sort(){
        Collections.sort(itemList);
    }

    /**
     * Iterates through list to extract item names
     * @return String array of names
     */
    public String[] names() {
        String[] list = new String[itemList.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = itemList.get(i).toString();
        }
        return list;
    }



}