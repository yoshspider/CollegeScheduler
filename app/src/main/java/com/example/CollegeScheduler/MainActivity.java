package com.example.CollegeScheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
               classList.addItem(new Assignment("Project 1",pedro, 2023, 3, 5, 5,30 ));
               System.out.println("hi");
                classAdapter.updateValues();
            }
        });

        Button sort1 = findViewById((R.id.sort1));
        sort1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View buttonView) {
                ListItem.setSortingMethod(1);
                classList.sort();
                classAdapter.updateValues();
            }
        });
        Button sort2 = findViewById((R.id.sort2));
        sort2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View buttonView) {
                ListItem.setSortingMethod(2);
                classList.sort();
                classAdapter.updateValues();
            }
        });

    }

}