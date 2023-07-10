package com.example.quizapp.View.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quizapp.databinding.ActivityStudentResultBinding;

public class StudentResultActivity extends AppCompatActivity {

    ActivityStudentResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityStudentResultBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());






    }
}