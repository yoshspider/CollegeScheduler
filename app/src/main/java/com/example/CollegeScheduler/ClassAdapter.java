package com.example.CollegeScheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * Authors: Yash Agrawal, Rishi Borra, Abby Martin
 * Version 1.12
 * ClassAdapter defines the ListView behavior for tasks and classes
 * but in theory this could take any type of object as long as it
 * extends the ListItem class, making it extendable for different
 * types of College items like labs, interviews, etc in the future
 * This also makes use of a boolean array in order to determine
 * which objects in the itemsList to show
 */
public class ClassAdapter extends BaseAdapter {
    private Context context;
    private CollegeObjectList<ListItem> itemsList;
    private LayoutInflater inflater;
    private int filter;
    private ArrayList<Boolean> visible;
    /**
     * Constructor for ClassAdapter Object
     * @param applicationContext context of App given by Main Activity
     * @param itemsList the CollegeObjectList which contains a number of ListItem objects
     *                  that will be drawn by ClassAdapter
     */
    public ClassAdapter(Context applicationContext, CollegeObjectList<ListItem> itemsList) {
        this.context = applicationContext;
        this.itemsList = itemsList;
        inflater = (LayoutInflater.from(applicationContext));
        this.filter = -1;
        visible = new ArrayList<Boolean>(itemsList.size());
        for (int i = 0; i < itemsList.size(); i++) {
            visible.add(true);
        }
    }
    /**
     * Getter for size of list
     * @return the size of the list
     */
    @Override
    public int getCount() {
        int sum = 0;
        for (int i = 0; i < visible.size(); i++) {
            if(visible.get(i)) {
                sum++;
            }
        }
        return sum;
    }
    /**
     *
     * gets the ListItem object at index i
     * @param i Position of the item whose data we want within the adapter's
     * data set.
     * @return ListItem object at index i
     */
    @Override
    public Object getItem(int i) {
        int index = -1;
        int numTrue = 0;
        while (numTrue <= i) {
            index++;
            if (visible.get(index)) {
                numTrue++;
            }

        }
        return itemsList.getItem(index);
    }
    /**
     * getter for the id of associated List Item
     * @param i The position of the item within the adapter's data set whose row id we want.
     * @return the ID of ListItem object at index i
     */
    @Override
    public long getItemId(int i) {
        return itemsList.getItem(i).getID();
    }

    /**
     * Setter for filter value
     * @param filter new value to filter by
     */
    public void setFilter(int filter) {
        this.filter = filter;
        for (int i = 0; i < visible.size(); i++) {
            visible.set(i, itemsList.getItem(i).filterValue(filter));
        }
    }

    /**
     * Creates the View and Draws it Accordingly for a given index in the list
     * @param i The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param view The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param viewGroup The parent that this view will eventually be attached to
     * @return modified view with correct features
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = ((ListItem) getItem(i)).drawScreen(view, inflater, i, this);
        return view;
    }
    /**
     * simple updater to notify ClassAdapter to redraw objects
     */
    public void updateValues(){

        visible = new ArrayList<Boolean>(itemsList.size());
        for (int i = 0; i < itemsList.size(); i++) {
            visible.add(true);
        }
        setFilter(filter);
        notifyDataSetChanged();
    }
    /**
     * remove an index from the list and update drawing
     * @param i the index of the object removed
     */
    public void remove(int i) {
        itemsList.removeItem(i);
        updateValues();
    }
    /**
     * setter for items list
     * @param itemsList new ItemsList
     */
    public void setItemsList(CollegeObjectList<ListItem> itemsList) {
        this.itemsList = itemsList;
        updateValues();
    }

    /**
     * removes item located at index i
     * @param i
     */
    public void transferItem(int i) {
        MainActivity.transferList.addItem(itemsList.removeItem(i));
        updateValues();
    }

}