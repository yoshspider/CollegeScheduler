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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classes);
        simpleList = findViewById(R.id.simpleListView);
        ClassAdapter classAdapter = new ClassAdapter(getApplicationContext(), classList);
        simpleList.setAdapter(classAdapter);


        Button button = findViewById(R.id.addplaceholder);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View buttonView) {
                classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900));
                classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000));
                Class pedro = new Class("Objects and Design", "Pedro", new boolean[]{false, true, false, true, false}, 1230,1430);
                classList.addItem(pedro);
                System.out.println("hi");
                Calendar ab = new GregorianCalendar(2003,3,5,5,30);
               classList.addItem(new Assignment("Project 1",pedro, ab ));
               System.out.println("hi");
               Calendar a = new GregorianCalendar(2023, 1, 25, 6, 30);
               classList.addItem(new Exam( pedro, "Quiz 1", "quiz on bits and stuff", "IC 211", a));
                classAdapter.updateValues();
            }
        });

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