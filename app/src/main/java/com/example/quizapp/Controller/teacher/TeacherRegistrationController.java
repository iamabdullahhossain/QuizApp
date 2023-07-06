package com.example.quizapp.Controller.teacher;

import android.content.Context;
import android.widget.Toast;

import com.example.quizapp.View.teacher.TeacherRegistrationActivity;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherRegistrationController {
    String TAG = "TAG";

    public void getRegistered(String name, String designation, String phone, String password, String confirmPassword, Context context, TeacherRegistrationActivity teacherRegistrationActivity) {


        if (password.equals(confirmPassword)) {
            Call<PostRetrofitModel> t_register = APICONTROLLER.getInstance().getAPI().t_register(
                    name, designation, phone, password
            );


            t_register.enqueue(new Callback<PostRetrofitModel>() {
                @Override
                public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

                    if (response.body().getReply().contains("DONE")) {
                        Toast.makeText(context, "Registration Completed", Toast.LENGTH_SHORT).show();
                        teacherRegistrationActivity.finish();

                    } else {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                    Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "Password not matched!", Toast.LENGTH_SHORT).show();
        }

    }


}
