package com.example.quizapp.View.teacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.TeacherHomePageController;
import com.example.quizapp.databinding.ActivityTeacherHomePageBinding;
import com.example.quizapp.model.constants.Constants;

public class TeacherHomePageActivity extends AppCompatActivity {
    ActivityTeacherHomePageBinding binding;
    String phone, uniqueCode, loginType;
    TeacherHomePageController controller;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new TeacherHomePageController();
        phone = getIntent().getStringExtra("phone");
        loginType = getIntent().getStringExtra("loginType");

        controller.teacherInformation(phone, binding.teacherNameTV, binding.designationTV);
        uniqueCode = controller.teacherInformation(phone, binding.getRoot().getContext());


        if (loginType.equals("0")){
            controller.newLoginFetchRooms(binding.recyclerView, binding.animationView, binding.noDataTV, binding.getRoot().getContext(), phone);



        } else if (loginType.equals("1")) {

            controller.fetchRooms(binding.recyclerView, binding.animationView, binding.noDataTV, binding.getRoot().getContext(), getSharedPreferences(Constants.TEACHERINFO, MODE_PRIVATE).getString("t_uniquecode", ""));

        }


        editor = getSharedPreferences(Constants.TEACHERINFO, MODE_PRIVATE).edit();
        editor.putString("phone", phone);
        editor.commit();


        binding.logoutBTN.setOnClickListener(view -> {
            editor.clear().commit();
            SharedPreferences.Editor editor1 = getSharedPreferences(Constants.TEACHERLOGIN, MODE_PRIVATE).edit();
            editor1.clear().apply();
            startActivity(new Intent(this, TeacherLoginActivity.class));
            finish();
        });


        binding.addBTN.setOnClickListener(view -> {

            startActivity(new Intent(TeacherHomePageActivity.this, CreateRoomActivity.class));


        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        controller.teacherInformation(phone, binding.teacherNameTV, binding.designationTV);
        uniqueCode = controller.teacherInformation(phone, binding.getRoot().getContext());
        controller.fetchRooms(binding.recyclerView, binding.animationView, binding.noDataTV, binding.getRoot().getContext(), getSharedPreferences(Constants.TEACHERINFO, MODE_PRIVATE).getString("t_uniquecode", ""));
        controller.newLoginFetchRooms(binding.recyclerView, binding.animationView, binding.noDataTV, binding.getRoot().getContext(), phone);



    }
}