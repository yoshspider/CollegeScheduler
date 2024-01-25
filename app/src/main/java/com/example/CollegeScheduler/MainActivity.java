package com.example.CollegeScheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    ListView simpleList;
    CollegeObjectList<ListItem> classList = new CollegeObjectList<ListItem>();
    ClassAdapter classAdapter;

    /**
     * This method sets up all the functionality of CollegeObjectList, ClassAdapter,
     * and the main screen buttons to add and sort object
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classes);
        simpleList = findViewById(R.id.simpleListView);
        classAdapter = new ClassAdapter(getApplicationContext(), classList);
        simpleList.setAdapter(classAdapter);
        addButtonFunctionality();
        sortButtonFunctionality();
        

    }

    /**
     * this adds the functionality to the add button which will allow items to be added to the list
     */
    public void addButtonFunctionality() {
        Button button = findViewById(R.id.addplaceholder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900));
                classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000));
                Class pedro = new Class("Objects and Design", "Pedro", new boolean[]{false, true, false, true, false}, 1230,1430);
                classList.addItem(pedro);
                Calendar ab = new GregorianCalendar(2003,3,5,5,30);
               classList.addItem(new Assignment("Project 1",pedro, ab ));
               Calendar a = new GregorianCalendar(2023, 1, 25, 6, 30);
               classList.addItem(new Exam( pedro, "Quiz 1", "quiz on bits and stuff", "IC 211", a));
                classAdapter.updateValues();
            }
        });
    }

    /**
     * this adds functionality to the sort buttons which will sort the data accordingly
     */
    public void sortButtonFunctionality() {
        Button sort1 = findViewById((R.id.sort1));
        sort1.setOnClickListener(buttonView -> {
            ListItem.setSortingMethod(1);
            classList.sort();
            classAdapter.updateValues();
        });
        Button sort2 = findViewById((R.id.sort2));
        sort2.setOnClickListener(buttonView -> {
            ListItem.setSortingMethod(2);
            classList.sort();
            classAdapter.updateValues();
        });
    }

}