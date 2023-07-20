package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.View.student.StudentLoginActivity;
import com.example.quizapp.View.teacher.TeacherLoginActivity;
import com.example.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.teacherBTN.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TeacherLoginActivity.class));
        });

        binding.studentBTN.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, StudentLoginActivity.class));
        });



    }
}

