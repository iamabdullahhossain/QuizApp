package com.example.quizapp.View.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quizapp.databinding.ActivityStudentSubmitBinding;

public class StudentSubmitActivity extends AppCompatActivity {
ActivityStudentSubmitBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentSubmitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }
}