package com.example.quizapp.View.student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.StudentAdapter.StudentViewAdapter;
import com.example.quizapp.databinding.ActivityStudentExamRoomBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.google.android.material.tabs.TabLayout;

public class StudentExamRoomActivity extends AppCompatActivity {
    ActivityStudentExamRoomBinding binding;
    StudentJoinedRoomModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentExamRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        model = (StudentJoinedRoomModel) getIntent().getSerializableExtra("model");

        binding.toolbar.setTitle(model.getrName());
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Exam"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Results"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        StudentViewAdapter adapter = new StudentViewAdapter(binding.getRoot().getContext(), getSupportFragmentManager(), binding.tabLayout.getTabCount(), model);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}