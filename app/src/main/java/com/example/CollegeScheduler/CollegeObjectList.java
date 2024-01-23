package com.example.CollegeScheduler;

import java.util.ArrayList;
import java.util.Collections;

public class CollegeObjectList<T extends Comparable<? super T>> {
    private ArrayList<T> itemList;
    public CollegeObjectList() {
        itemList = new ArrayList<>();
    }
    public ArrayList<T> getItemList() {
        return itemList;
    }

    public void addItem(T item, int index) {
        itemList.add(index, item);
    }
    public void addItem(T item) {
        itemList.add(item);
    }
    public T getItem(int index) {
        return itemList.get(index);
    }
    public void removeItem(int index) {
        itemList.remove(index);
    }
    public int size() {
        return itemList.size();
    }
    public void sort(){
        Collections.sort(itemList);
    }



}
