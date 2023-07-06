package com.example.quizapp.Controller.teacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quizapp.Controller.TeacherAdapter.TeacherRoomFetchAdapter;
import com.example.quizapp.View.teacher.AddExamsActivity;
import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.teacher.TeacherInformationModel;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherHomePageController {

    String uniqueID;

    public void teacherInformation(String phone, TextView teacherNameTV, TextView designationTV) {
        Call<List<TeacherInformationModel>> t_information = APICONTROLLER.getInstance().getAPI().t_information(phone);
        t_information.enqueue(new Callback<List<TeacherInformationModel>>() {
            @Override
            public void onResponse(Call<List<TeacherInformationModel>> call, Response<List<TeacherInformationModel>> response) {
                uniqueID = response.body().get(0).gettUniquecode();
                teacherNameTV.setText(response.body().get(0).gettName());
                designationTV.setText(response.body().get(0).gettDesignation());
            }

            @Override
            public void onFailure(Call<List<TeacherInformationModel>> call, Throwable t) {

            }
        });

    }

    public String teacherInformation(String phone, Context context) {
        Call<List<TeacherInformationModel>> t_information = APICONTROLLER.getInstance().getAPI().t_information(phone);
        t_information.enqueue(new Callback<List<TeacherInformationModel>>() {
            @Override
            public void onResponse(Call<List<TeacherInformationModel>> call, Response<List<TeacherInformationModel>> response) {
                uniqueID = response.body().get(0).gettUniquecode();
                SharedPreferences.Editor editor = context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).edit();
                editor.putString("t_name", response.body().get(0).gettName());
                editor.putString("t_designation", response.body().get(0).gettDesignation());
                editor.putString("t_uniquecode", response.body().get(0).gettUniquecode());
                editor.apply();


            }

            @Override
            public void onFailure(Call<List<TeacherInformationModel>> call, Throwable t) {

            }
        });
        return uniqueID;
    }

    public void fetchRooms(RecyclerView recyclerView, LottieAnimationView animationView, TextView text, Context context, String uniquecode) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<TeacherRoomFetchModel>> roomfetch = APICONTROLLER.getInstance().getAPI().t_fetchroom(uniquecode, "", "");
        roomfetch.enqueue(new Callback<List<TeacherRoomFetchModel>>() {
            @Override
            public void onResponse(Call<List<TeacherRoomFetchModel>> call, Response<List<TeacherRoomFetchModel>> response) {
                if (response.body().size() > 0) {
                    animationView.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    TeacherRoomFetchAdapter adapter = new TeacherRoomFetchAdapter(response.body(), context, new TeacherRoomFetchAdapter.onClick() {
                        @Override
                        public void onClick(TeacherRoomFetchModel model) {
                            Intent intent = new Intent(context, AddExamsActivity.class);
                            intent.putExtra("model", model);
                            context.startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);


                }

                else {
                    recyclerView.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onFailure(Call<List<TeacherRoomFetchModel>> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                animationView.setVisibility(View.VISIBLE);
                text.setText("CHECK YOUR INTERNET CONNECTION");
            }
        });


    }

    public void newLoginFetchRooms(RecyclerView recyclerView, LottieAnimationView animationView, TextView text, Context context, String phone) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<TeacherRoomFetchModel>> roomfetch = APICONTROLLER.getInstance().getAPI().t_fetchroom("", phone, "");
        roomfetch.enqueue(new Callback<List<TeacherRoomFetchModel>>() {
            @Override
            public void onResponse(Call<List<TeacherRoomFetchModel>> call, Response<List<TeacherRoomFetchModel>> response) {
                if (response.body().size() > 0) {
                    animationView.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    TeacherRoomFetchAdapter adapter = new TeacherRoomFetchAdapter(response.body(), context, new TeacherRoomFetchAdapter.onClick() {
                        @Override
                        public void onClick(TeacherRoomFetchModel model) {
                            Intent intent = new Intent(context, AddExamsActivity.class);
                            intent.putExtra("model", model);
                            context.startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);


                }

                else {
                    recyclerView.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onFailure(Call<List<TeacherRoomFetchModel>> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                animationView.setVisibility(View.VISIBLE);
                text.setText("CHECK YOUR INTERNET CONNECTION");
            }
        });


    }




}
