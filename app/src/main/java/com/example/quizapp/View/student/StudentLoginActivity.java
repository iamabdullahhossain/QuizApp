package com.example.quizapp.View.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.student.StudentLoginController;
import com.example.quizapp.databinding.ActivityStudentLoginBinding;

public class StudentLoginActivity extends AppCompatActivity {
    ActivityStudentLoginBinding binding;
    StudentLoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new StudentLoginController();

        controller.checkIfLoggedIn(binding.getRoot().getContext(), this);

        binding.createNewAccountBTN.setOnClickListener(view -> {
            startActivity(new Intent(StudentLoginActivity.this, StudentRegistrationActivity.class));
        });

        binding.loginBTN.setOnClickListener(view -> {
            controller.logIn(binding.credential1.getText().toString(), binding.credential2.getText().toString(), binding.getRoot().getContext(), this);
        });


    }
}