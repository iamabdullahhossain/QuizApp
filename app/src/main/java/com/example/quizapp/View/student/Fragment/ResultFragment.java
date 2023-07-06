package com.example.quizapp.View.student.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.quizapp.databinding.FragmentResultBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;


public class ResultFragment extends Fragment {

    FragmentResultBinding binding;
    StudentJoinedRoomModel model;

    public ResultFragment(StudentJoinedRoomModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }
}