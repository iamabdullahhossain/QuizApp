package com.example.quizapp.View.student.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.quizapp.Controller.student.ExamFragmentController;
import com.example.quizapp.databinding.FragmentExamBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;

public class ExamFragment extends Fragment {


    FragmentExamBinding binding;
    StudentJoinedRoomModel model;
    ExamFragmentController controller;

    public ExamFragment(StudentJoinedRoomModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExamBinding.inflate(getLayoutInflater());

        controller = new ExamFragmentController();
        controller.showQuestions(binding.recyclerView, binding.getRoot().getContext(), model.getrCode(), binding.notStartTV, binding.submitBTN);




        return binding.getRoot();
    }
}