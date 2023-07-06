package com.example.quizapp.View.student;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.student.StudentRegistrationController;
import com.example.quizapp.databinding.ActivityStudentRegistrationBinding;

public class StudentRegistrationActivity extends AppCompatActivity {

    ActivityStudentRegistrationBinding binding;
    StudentRegistrationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        controller = new StudentRegistrationController();
        binding.confirmBTN.setOnClickListener(view -> {

            if (binding.nameET.getText().toString().length() != 0 ||
                    binding.batchET.getText().toString().length() != 0 ||
                    binding.idET.getText().toString().length() != 0 ||
                    binding.phoneET.getText().toString().length() != 0 ||
                    binding.passwordET.getText().toString().length() != 0 ||
                    binding.emailET.getText().toString().length() != 0) {


                if (binding.passwordET.getText().toString().contains(binding.confirmPasswordET.getText().toString())) {
                    controller.registration(
                            binding.nameET.getText().toString(),
                            binding.batchET.getText().toString(),
                            binding.idET.getText().toString(),
                            binding.phoneET.getText().toString(),
                            binding.passwordET.getText().toString(),
                            binding.emailET.getText().toString(),
                            binding.getRoot().getContext(),
                            this
                    );

                } else {
                    Toast.makeText(this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "Please fill up all the fields!", Toast.LENGTH_SHORT).show();
            }


        });


    }
}