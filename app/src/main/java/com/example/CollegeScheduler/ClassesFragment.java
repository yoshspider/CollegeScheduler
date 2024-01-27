package com.example.CollegeScheduler;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassesFragment extends Fragment {

    public MainActivity classActivity;
    //private ClassesFragment binding;

    public ClassesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClassesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassesFragment newInstance() {
        ClassesFragment fragment = new ClassesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        classActivity = (MainActivity)getActivity();
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // binding = FragmentFirstBinding.inflate(inflater, container, false);

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classes, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.addplaceholder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                classActivity.classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900));
                classActivity.classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000));
                classActivity.classAdapter.updateValues();
            }
        });

        Button sort1 = view.findViewById(R.id.sort1);
        sort1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                ListItem.setSortingMethod(1);
                classActivity.classList.sort();
                classActivity.classAdapter.updateValues();
            }
        });

        Button sort2 = view.findViewById(R.id.sort2);
        sort2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View buttonView) {
                ListItem.setSortingMethod(2);
                classActivity.classList.sort();
                classActivity.classAdapter.updateValues();
            }
        });
    }
}