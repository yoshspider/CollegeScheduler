package com.example.CollegeScheduler;

/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.03
 * Time Conversion defines a converter for an int
 * time in military time and converts to a string
 * that includes AM, PM and a normalized time format
 */
public interface TimeConversion {

    /**
     * Convert time to correct format for display on screen
     * @param time time in integer format
     * @return time as formatted string
     */
    default String timeConverter(int time) {
        return String.format("%01d:%02d", (time > 1200 ? time/100 - 11 : time/100), time%100)  + (time > 1200 ? " PM" : " AM");
    }

}
