package com.example.quizapp.View.teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.TeacherLoginController;
import com.example.quizapp.databinding.ActivityTeacherLoginBinding;

public class TeacherLoginActivity extends AppCompatActivity {
    ActivityTeacherLoginBinding binding;
    TeacherLoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new TeacherLoginController();
        controller.checkIfLoggedIn(binding.credential1.getText().toString(), binding.credential2.getText().toString(), this, this);
        binding.createNewAccountBTN.setOnClickListener(view -> {
            startActivity(new Intent(TeacherLoginActivity.this, TeacherRegistrationActivity.class));
        });

        binding.loginBTN.setOnClickListener(view -> {
            controller.login(binding.credential1.getText().toString(), binding.credential2.getText().toString(), binding.getRoot().getContext(), TeacherLoginActivity.this);
        });


    }
}