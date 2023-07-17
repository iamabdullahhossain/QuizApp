package com.example.quizapp.View.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.TeacherCheckResultController;
import com.example.quizapp.databinding.ActivityTeacherCheckResultBinding;
import com.example.quizapp.model.teacher.StudentAcceptedListModel;

public class TeacherCheckResultActivity extends AppCompatActivity {
    ActivityTeacherCheckResultBinding binding;
    StudentAcceptedListModel model;
    TeacherCheckResultController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTeacherCheckResultBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        model = (StudentAcceptedListModel) getIntent().getSerializableExtra("model");

        controller = new TeacherCheckResultController();

        controller.checkResult(model.getrCode(), model.getsId(), binding.totalQuestionTV, binding.solvedQuestionTV,
                binding.correctAnswerTV, binding.wrongAnswerTV, binding.totalMarksTV, binding.obtainedMarksTV);

        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }
}