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
     * get the item at specific index
     * @param index index to be get item at
     * @return
     */
    public T getItem(int index) {
        return itemList.get(index);
    }

    /**
     * remove the item at specific index
     * @param index index to remove item at
     */
    public void removeItem(int index) {
        itemList.remove(index);
    }

    /**
     * size of list
     * @return itemList.size
     */
    public int size() {
        return itemList.size();
    }
    public void sort(){
        Collections.sort(itemList);
    }



}
