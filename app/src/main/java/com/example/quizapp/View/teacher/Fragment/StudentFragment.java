package com.example.quizapp.View.teacher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.quizapp.Controller.teacher.StudentFragmentController;
import com.example.quizapp.View.teacher.TeacherAcceptRequestActivity;
import com.example.quizapp.databinding.FragmentStudentBinding;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;


public class StudentFragment extends Fragment {

    FragmentStudentBinding binding;
    TeacherRoomFetchModel model;
    StudentFragmentController controller;

    public StudentFragment(TeacherRoomFetchModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(getLayoutInflater());
        controller = new StudentFragmentController();

        controller.getRequestCount(model.getrCode(), binding.waitingTV);
        controller.getAcceptedStudents(model.getrCode(), binding.recyclerView, binding.getRoot().getContext());

        binding.card1.setOnClickListener(view -> {
            Intent intent = new Intent(binding.getRoot().getContext(), TeacherAcceptRequestActivity.class);
            intent.putExtra("r_code", model.getrCode());
            startActivity(intent);

        });


        return binding.getRoot();
    }
}