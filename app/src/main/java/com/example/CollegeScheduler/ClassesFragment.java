package com.example.CollegeScheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.CollegeScheduler.databinding.FragmentClassesBinding;

public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;
    private MainActivity classActivity;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        classActivity = (MainActivity)getActivity();

        binding = FragmentClassesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addplaceholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classActivity.classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900));
                classActivity.classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000));
                classActivity.classAdapter.updateValues();
                NavHostFragment.findNavController(ClassesFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.sort1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListItem.setSortingMethod(1);
                classActivity.classList.sort();
                classActivity.classAdapter.updateValues();
            }
        });

        binding.sort2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListItem.setSortingMethod(2);
                classActivity.classList.sort();
                classActivity.classAdapter.updateValues();
            }
        });
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}