package com.example.quizapp.View.student.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Controller.student.ExamFragmentController;
import com.example.quizapp.R;
import com.example.quizapp.databinding.FragmentExamTwoBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;

public class ExamFragmentTwo extends Fragment {
    FragmentExamTwoBinding binding;
    StudentJoinedRoomModel model;
    ExamFragmentController controller;
    int number = 0;

    public ExamFragmentTwo(StudentJoinedRoomModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExamTwoBinding.inflate(getLayoutInflater());

        controller = new ExamFragmentController();
        controller.showSeparateQuestions(binding.getRoot().getContext(),
                model.getrCode(),
                model.getsId(),
                binding.notStartTV,
                number,
                binding);

        number++;
        binding.nextBTN.setOnClickListener(view -> {
            controller.nextQuestion(binding, model);

            number++;
        });



        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}