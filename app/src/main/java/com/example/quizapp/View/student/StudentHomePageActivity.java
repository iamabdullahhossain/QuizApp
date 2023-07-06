package com.example.quizapp.View.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.student.StudentHomePageController;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityStudentHomePageBinding;
import com.example.quizapp.model.constants.Constants;

public class StudentHomePageActivity extends AppCompatActivity {

    ActivityStudentHomePageBinding binding;
    StudentHomePageController controller;
    String s_id, loginType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new StudentHomePageController();

        s_id = getIntent().getStringExtra("s_id"); //if loginType == 0
        loginType = getIntent().getStringExtra("loginType");

        controller.getInformation(s_id, binding.studentNameTV, binding.batchTV, binding.getRoot().getContext());
        controller.getJoinedRoom(binding.recyclerView,
                binding.animationView,
                binding.noDataTV,
                s_id,
                binding.getRoot().getContext());
//
//        if (loginType == "0"){
//
//        }
//        else if (loginType == "1"){
//            controller.getInformation(getSharedPreferences(Constants.STUDENTLOGIN, MODE_PRIVATE).getString("s_id", ""),
//                    binding.studentNameTV, binding.batchTV, binding.getRoot().getContext());
//        }


        binding.logoutBTN.setOnClickListener(view -> {
            SharedPreferences.Editor editor = getSharedPreferences(Constants.STUDENTLOGIN, MODE_PRIVATE).edit();
            editor.clear().apply();
            SharedPreferences.Editor editor1 = getSharedPreferences(Constants.STUDENTINFO, MODE_PRIVATE).edit();
            editor1.clear().apply();
            startActivity(new Intent(this, StudentLoginActivity.class));
            finish();
        });

        binding.searchBTN.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
            final View customLayout = getLayoutInflater().inflate(R.layout.row_searchroom_layout, null);
            builder.setView(customLayout);
            AlertDialog dialog = builder.create();


            EditText roomCodeET = customLayout.findViewById(R.id.codeET);
            Button doneBTN = customLayout.findViewById(R.id.doneBTN);

            doneBTN.setOnClickListener(view1 -> {


                if (roomCodeET.getText().toString().length()<=0){
                    dialog.dismiss();
                }
                else {
                    controller.searchRoom(roomCodeET.getText().toString(), binding.getRoot().getContext(), this);
                }





            });

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    controller.getJoinedRoom(binding.recyclerView,
                            binding.animationView,
                            binding.noDataTV,
                            s_id,
                            binding.getRoot().getContext());
                }
            });


            dialog.show();


        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.getJoinedRoom(binding.recyclerView,
                binding.animationView,
                binding.noDataTV,
                s_id,
                binding.getRoot().getContext());
    }
}