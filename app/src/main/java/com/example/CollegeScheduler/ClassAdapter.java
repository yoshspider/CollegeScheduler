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
    CollegeObjectList<Class> itemsList;

    LayoutInflater inflater;

    public ClassAdapter(Context applicationContext, CollegeObjectList<Class> itemsList) {
        this.context = applicationContext;
        this.itemsList = itemsList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

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
    }
}