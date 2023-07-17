package com.example.quizapp.View.teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.StudentResultController;
import com.example.quizapp.databinding.ActivityStudentResultBinding;
import com.example.quizapp.model.teacher.StudentAcceptedListModel;

public class StudentResultActivity extends AppCompatActivity {

    ActivityStudentResultBinding binding;
    StudentAcceptedListModel model;
    StudentResultController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityStudentResultBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        model = (StudentAcceptedListModel) getIntent().getSerializableExtra("model");

        binding.batchTV.setText(model.getsBatch());
        binding.idTV.setText(model.getsId());
        binding.nameTV.setText(model.getsName());

        controller = new StudentResultController();

        controller.getAnswerSheet(binding.recyclerView, model.getrCode(), model.getsId(), binding.getRoot().getContext(), binding.checkResultBTN);


        binding.checkResultBTN.setOnClickListener(view -> {

            Intent intent = new Intent(StudentResultActivity.this, TeacherCheckResultActivity.class);
            intent.putExtra("model", model);
            startActivity(intent);

        });


        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

    }
}