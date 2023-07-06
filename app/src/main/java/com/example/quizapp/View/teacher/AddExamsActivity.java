package com.example.quizapp.View.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.TeacherAdapter.MyAdapter;
import com.example.quizapp.databinding.ActivityAddExamsBinding;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;
import com.google.android.material.tabs.TabLayout;

public class AddExamsActivity extends AppCompatActivity {
    ActivityAddExamsBinding binding;
    TeacherRoomFetchModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddExamsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = (TeacherRoomFetchModel) getIntent().getSerializableExtra("model");

        binding.toolbar.setTitle(model.getrName());
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Quiz"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Students"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        MyAdapter adapter = new MyAdapter(this, getSupportFragmentManager(), binding.tabLayout.getTabCount(), model);
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