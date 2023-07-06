package com.example.quizapp.View.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.TeacherRegistrationController;
import com.example.quizapp.databinding.ActivityTeacherRegistrationBinding;

public class TeacherRegistrationActivity extends AppCompatActivity {

    ActivityTeacherRegistrationBinding binding;
    TeacherRegistrationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new TeacherRegistrationController();

        binding.confirmBTN.setOnClickListener(view -> {
            controller.getRegistered(
                    binding.nameET.getText().toString(),
                    binding.designationET.getText().toString(),
                    binding.phoneET.getText().toString(),
                    binding.passwordET.getText().toString(),
                    binding.confirmPasswordET.getText().toString(),
                    binding.getRoot().getContext(),
                    TeacherRegistrationActivity.this
            );


        });

        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }


}