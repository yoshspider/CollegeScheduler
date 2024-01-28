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
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView topTextView = view.findViewById(R.id.topTextView);
        TextView middleTextView = view.findViewById(R.id.middleTextView);
        TextView bottomLeftTextView = view.findViewById(R.id.bottomLeftTextView);
        TextView bottomRightTextView = view.findViewById(R.id.bottomRightTextView);
        Button deleteButton = view.findViewById(R.id.delete);
        Button modifyButton = view.findViewById(R.id.modify);
        View background = view.findViewById(R.id.backgroundView);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                itemsList.removeItem(i);
                notifyDataSetChanged();
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Class currentItem = itemsList.getItem(i);
        topTextView.setText(currentItem.getClassName());
        middleTextView.setText(currentItem.getProfessorName());

        bottomLeftTextView.setText(daysOfTheWeekConverter(currentItem.getMeetingDates()));
        String time = timeConverter(currentItem.getStartTime()) + " - "
                + timeConverter(currentItem.getEndTIme());
        bottomRightTextView.setText(time);
        background.setBackgroundColor(currentItem.getColor());
        return view;
    }

    public void updateValues(){
        notifyDataSetChanged();
    }
    public String timeConverter(int time) {
        return String.format("%01d:%02d", time/100, time%100)  + (time > 1200 ? " PM" : " AM");
    }

    public String daysOfTheWeekConverter(boolean[] days) {
        String week = (days[0] ? "M/":"") + (days[1] ? "T/":"") + (days[2] ? "W/":"") + (days[3] ? "TR/":"") + (days[4] ? "F/":"");
        return week.substring(0, week.length()-1);
        //return itemsList.getItem(i).drawScreen(view, inflater, i, this);
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