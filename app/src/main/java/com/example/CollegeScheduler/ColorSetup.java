package com.example.CollegeScheduler;

/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.02
 * ColorSetup is an interface that defines the list
 * of colors to choose from for items. This can be
 * overriden and implemented in different ways
 */
public interface ColorSetup {
    /**
     * default color setup method in which future programmers can implement
     * their own set of colors if they do not like the ones that this implements
     * @return set of colors to be used for listitems
     */
    default int[] setColors() {
        int[] colors = new int[20];
        for (int i = 0; i < 20; i++) {
            colors[i] =  100*16^4 + 40 * i * 16^3 + (255-i*40) * 16^2 + 150;
        }
        return colors;
    }
}
