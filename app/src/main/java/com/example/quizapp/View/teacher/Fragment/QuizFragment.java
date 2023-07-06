package com.example.quizapp.View.teacher.Fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.quizapp.Controller.teacher.QuizFragmentController;
import com.example.quizapp.R;
import com.example.quizapp.databinding.FragmentQuizBinding;
import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;
import com.google.android.material.chip.Chip;


public class QuizFragment extends Fragment {
    TeacherRoomFetchModel model;
    FragmentQuizBinding binding;
    QuizFragmentController controller;

    boolean examStart = true;

    SharedPreferences.Editor editor;
    //  String time;

    public QuizFragment(TeacherRoomFetchModel model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQuizBinding.inflate(getLayoutInflater());
        controller = new QuizFragmentController();
        binding.roomCodeTV.setText(model.getrCode());
        binding.timeTV.setText(model.getrTime());
        // time = model.getrTime();


        boolean examStart2 = binding.getRoot().getContext().getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getBoolean("condition", false);
        if (examStart2) {
            binding.examStartBTN.setImageResource(R.drawable.ic_action_play);
            controller.stopTimer(binding.getRoot().getContext(), model.getrCode(), binding.timeTV);

        }


        binding.copyBTN.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) binding.getRoot().getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied Text", binding.roomCodeTV.getText());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(binding.getRoot().getContext(), "Room Code Copied!", Toast.LENGTH_SHORT).show();

        });

        binding.editTimeBTN.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
            final View customLayout = getLayoutInflater().inflate(R.layout.row_selecttime_layout, null);
            builder.setView(customLayout);
            AlertDialog dialog = builder.create();

            EditText minutePicker = customLayout.findViewById(R.id.minPicker);
            EditText secondPicker = customLayout.findViewById(R.id.secPicker);
            Button doneBTN = customLayout.findViewById(R.id.doneBTN);


            doneBTN.setOnClickListener(view1 -> {
                controller.updateTime(minutePicker.getText().toString(), secondPicker.getText().toString(), model, binding.timeTV);

                dialog.dismiss();
            });


            dialog.show();
        });


        binding.examStartBTN.setOnClickListener(view -> {

            try {
                examStart = !examStart;


                if (examStart) {
                    binding.examStartBTN.setImageResource(R.drawable.ic_action_stop);
                    controller.startExam(binding.getRoot().getContext(), model.getrCode(), binding.timeHeadTV, binding.timeTV);
                    controller.startTimer(binding.getRoot().getContext(), model.getrCode(), binding.timeTV.getText().toString(), binding.timeTV,
                            binding.editTimeBTN, binding.examStartBTN

                    );

                    editor = binding.getRoot().getContext().getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).edit();
                    editor.putBoolean("condition", examStart);
                    editor.apply();


                } else {
                    binding.examStartBTN.setImageResource(R.drawable.ic_action_play);

                    controller.stopExam(binding.getRoot().getContext(), model.getrCode(), binding.timeTV);
                    controller.stopTimer(binding.getRoot().getContext(), model.getrCode(), binding.timeTV);
                    // controller.startTimer(binding.getRoot().getContext(), binding.timeTV.getText().toString(), binding.timeTV, "NO");

                }
            }
            catch (Exception e){
                Toast.makeText(binding.getRoot().getContext(), "Set Time First", Toast.LENGTH_SHORT).show();
            }

        });

        binding.addQuizBTN.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
            final View customLayout = getLayoutInflater().inflate(R.layout.row_questiontype_layout, null);
            builder.setView(customLayout);
            AlertDialog dialog = builder.create();

            Chip mcqChip = customLayout.findViewById(R.id.mcqChip);
            Chip trueFalseChip = customLayout.findViewById(R.id.truefalseChip);
            Chip shortChip = customLayout.findViewById(R.id.shortChip);

            Button doneBTN = customLayout.findViewById(R.id.doneBTN);

            doneBTN.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            mcqChip.setOnClickListener(view1 -> {

                controller.mcqQuestion(binding.getRoot().getContext(), QuizFragment.this, model);

            });

            trueFalseChip.setOnClickListener(view1 -> {
                controller.trueFalse(binding.getRoot().getContext(), QuizFragment.this, model);
            });

            shortChip.setOnClickListener(view1 -> {
                controller.shortAnswer(binding.getRoot().getContext(), QuizFragment.this, model);
            });


            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    controller.fetchQuestions(model.gettUniquecode(), model.getrCode(), binding.recyclerView, binding.getRoot().getContext());
                }
            });

            dialog.show();


        });

        controller.fetchQuestions(model.gettUniquecode(), model.getrCode(), binding.recyclerView, binding.getRoot().getContext());


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.fetchQuestions(model.gettUniquecode(), model.getrCode(), binding.recyclerView, binding.getRoot().getContext());
        controller.fetchTime(model.getrCode(), binding.timeTV);

    }


}