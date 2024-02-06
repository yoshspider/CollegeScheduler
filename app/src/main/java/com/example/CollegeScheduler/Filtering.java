package com.example.CollegeScheduler;

/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.02
 * Filtering defines for an object to filter out of
 * a ListView as it does not meet the conditions
 * of a filter. On default, this method returns
 * true as the filters should be defined by extending
 * programs and by default should tell that it should
 * not be filtered. Task overrides the filter by
 * type. This is good for extension as future
 * classes like Lab and Interview can implement
 * this feature
 */
public interface Filtering {
    /**
     * filters out data for any adapter
     * @param filter the tool to decide whether item shouldnt be filtered
     * @return whether the item shouldnt be filtered
     */
    default boolean filterValue(int filter) {
        return true;
    }

}
