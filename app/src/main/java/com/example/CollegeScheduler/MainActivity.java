package com.example.CollegeScheduler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.CollegeScheduler.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public ListView simpleList;
    public CollegeObjectList<Class> classList = new CollegeObjectList<Class>();
    public ClassAdapter classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up navigation
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        setContentView(R.layout.fragment_classes);
        simpleList = findViewById(R.id.simpleListView);
        classAdapter = new ClassAdapter(getApplicationContext(), classList);
        simpleList.setAdapter(classAdapter);

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//
////        NavController navController = Navigation.findNavController(this, R.id.nav_host);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

}