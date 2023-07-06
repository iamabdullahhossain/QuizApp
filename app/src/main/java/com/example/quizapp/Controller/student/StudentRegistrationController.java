package com.example.quizapp.Controller.student;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRegistrationController {

    public void registration(String name, String batch, String id, String phone, String password, String email, Context context, Activity activity) {

        Call<PostRetrofitModel> s_reg = APICONTROLLER.getInstance().getAPI().s_reg(
                name, batch, id, phone, password, email
        );

        s_reg.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().contains("DONE")) {
                    Toast.makeText(context, "Registration Completed", Toast.LENGTH_SHORT).show();
                    activity.finish();
                } else {
                    Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });


    }


}
