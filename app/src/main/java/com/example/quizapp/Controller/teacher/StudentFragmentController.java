package com.example.quizapp.Controller.teacher;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.TeacherAdapter.StudentAcceptedListAdapter;
import com.example.quizapp.View.teacher.StudentResultActivity;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.teacher.StudentAcceptedListModel;
import com.example.quizapp.model.teacher.StudentRequestCountModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentFragmentController {

    public void getRequestCount(String r_code, TextView text) {
        Call<StudentRequestCountModel> r_count = APICONTROLLER.getInstance().getAPI().req_count(r_code);
        r_count.enqueue(new Callback<StudentRequestCountModel>() {
            @Override
            public void onResponse(Call<StudentRequestCountModel> call, Response<StudentRequestCountModel> response) {
                text.setText("Waiting Room (" + response.body().getCount() + ")");
            }

            @Override
            public void onFailure(Call<StudentRequestCountModel> call, Throwable t) {

            }
        });
    }

    public void getAcceptedStudents(String r_code, RecyclerView recyclerView, Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<StudentAcceptedListModel>> accepted = APICONTROLLER.getInstance().getAPI().accepted(r_code);
        accepted.enqueue(new Callback<List<StudentAcceptedListModel>>() {
            @Override
            public void onResponse(Call<List<StudentAcceptedListModel>> call, Response<List<StudentAcceptedListModel>> response) {
                StudentAcceptedListAdapter adapter = new StudentAcceptedListAdapter(response.body(), context, new StudentAcceptedListAdapter.StudentAcceptInterface() {
                    @Override
                    public void onClick(StudentAcceptedListModel model) {

                        Intent intent = new Intent(context, StudentResultActivity.class);
                        intent.putExtra("model", model);
                        context.startActivity(intent);

                    }

                });
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<StudentAcceptedListModel>> call, Throwable t) {

            }
        });


    }


}
