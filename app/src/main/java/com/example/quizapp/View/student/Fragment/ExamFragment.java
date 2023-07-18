package com.example.quizapp.View.student.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.quizapp.Controller.student.ExamFragmentController;
import com.example.quizapp.View.student.StudentSubmitActivity;
import com.example.quizapp.databinding.FragmentExamBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.example.quizapp.model.student.StudentQuestionModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExamFragment extends Fragment {


    FragmentExamBinding binding;
    StudentJoinedRoomModel model;
    ExamFragmentController controller;
    List<StudentQuestionModel> list = new ArrayList<>();


    public ExamFragment(StudentJoinedRoomModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExamBinding.inflate(getLayoutInflater());

        controller = new ExamFragmentController();
        controller.showQuestions(binding.recyclerView, binding.getRoot().getContext(), model.getrCode(), binding.notStartTV, binding.submitBTN, model.getsId());

        binding.submitBTN.setOnClickListener(view -> {

            Type type = new TypeToken<List<StudentQuestionModel>>() {
            }.getType();
            list = new Gson().fromJson(controller.GetList(), type);

            controller.submitAnswer(binding.getRoot().getContext(), list, model, binding.submitBTN, this);


        });


        return binding.getRoot();
    }


}