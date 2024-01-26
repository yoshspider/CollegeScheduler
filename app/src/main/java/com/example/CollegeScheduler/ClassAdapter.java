package com.example.CollegeScheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ClassAdapter extends BaseAdapter {
    Context context;
    CollegeObjectList<ListItem> itemsList;
    LayoutInflater inflater;

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
    }
    /**
     * Getter for size of list
     * @return the size of the list
     */
    @Override
    public int getCount() {
        return itemsList.size();
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
        return itemsList.getItem(i);
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
       return itemsList.getItem(i).drawScreen(view, inflater, i, this);
    }
    /**
     * simple updater to notify ClassAdapter to redraw objects
     */
    public void updateValues(){
        notifyDataSetChanged();
    }
    /**
     * remove an index from the list and update drawing
     * @param i the index of the object removed
     */
    public void remove(int i) {
        itemsList.removeItem(i);
        notifyDataSetChanged();
    }
}