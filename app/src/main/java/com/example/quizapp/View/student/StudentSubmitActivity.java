package com.example.quizapp.View.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.databinding.ActivityStudentSubmitBinding;

public class StudentSubmitActivity extends AppCompatActivity {
    ActivityStudentSubmitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentSubmitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(view -> {

            finish();
        });


    }
}