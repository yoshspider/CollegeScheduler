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

    public ClassAdapter(Context applicationContext, CollegeObjectList<ListItem> itemsList) {
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
       return itemsList.getItem(i).drawScreen(view, viewGroup, inflater, i, this);
    }
    public void updateValues(){
        notifyDataSetChanged();
    }

    public void remove(int i) {
        itemsList.removeItem(i);
        notifyDataSetChanged();
    }
}