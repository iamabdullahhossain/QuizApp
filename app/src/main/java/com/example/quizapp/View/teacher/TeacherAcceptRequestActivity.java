package com.example.quizapp.View.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.TeacherAcceptRequestController;
import com.example.quizapp.databinding.ActivityTeacherAcceptRequestBinding;

public class TeacherAcceptRequestActivity extends AppCompatActivity {
    ActivityTeacherAcceptRequestBinding binding;
    TeacherAcceptRequestController controller;
    String r_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherAcceptRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new TeacherAcceptRequestController();

        r_code = getIntent().getStringExtra("r_code");

        controller.showRequests(binding.recyclerView, r_code, binding.getRoot().getContext(), binding.noRequestTV);

        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }
}