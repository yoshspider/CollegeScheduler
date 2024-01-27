package com.example.CollegeScheduler;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.CollegeScheduler.databinding.FragmentClassesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassesFragment extends Fragment {

    public MainActivity classActivity;
    private FragmentClassesBinding binding;

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
        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View classesLayout = inflater.inflate(R.layout.fragment_classes, container, false);
        return classesLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addplaceholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                classActivity.classList.addItem(new Class("Math", "Mcfadden", new boolean[]{true, false, true, false, true}, 700, 900));
//                classActivity.classList.addItem(new Class("Chemistry", "Allshouse", new boolean[]{false, true, false, true, false}, 800, 1000));
//                classActivity.classAdapter.updateValues();
                System.out.println("test");
                NavHostFragment.findNavController(ClassesFragment.this)
                        .navigate(R.id.action_classesFragment_to_modifyFragment);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}