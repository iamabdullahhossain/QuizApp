package com.example.quizapp.Controller.teacher;

import android.widget.TextView;

import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.teacher.StudentRequestCountModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentFragmentController {

    public void getRequestCount(String r_code, TextView text) {
        Call<StudentRequestCountModel> r_count = APICONTROLLER.getInstance().getAPI().req_count(r_code);
        r_count.enqueue(new Callback<StudentRequestCountModel>() {
            @Override
            public void onResponse(Call<StudentRequestCountModel> call, Response<StudentRequestCountModel> response) {
                text.setText("Waiting Room ("+response.body().getCount()+")");
            }

            @Override
            public void onFailure(Call<StudentRequestCountModel> call, Throwable t) {

            }
        });


    }
}
