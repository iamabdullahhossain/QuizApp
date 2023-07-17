package com.example.quizapp.View.student.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.quizapp.Controller.teacher.TeacherCheckResultController;
import com.example.quizapp.databinding.FragmentResultBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;


public class ResultFragment extends Fragment {

    FragmentResultBinding binding;
    StudentJoinedRoomModel model;
    TeacherCheckResultController controller;

    public ResultFragment(StudentJoinedRoomModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(getLayoutInflater());

        controller = new TeacherCheckResultController();

        controller.checkResult(model.getrCode(), model.getsId(), binding.totalQuestionTV, binding.solvedQuestionTV,
                binding.correctAnswerTV, binding.wrongAnswerTV, binding.totalMarksTV, binding.obtainedMarksTV);



        return binding.getRoot();
    }
}