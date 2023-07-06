package com.example.quizapp.Controller.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.quizapp.View.teacher.TeacherHomePageActivity;
import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.teacher.TeacherInformationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLoginController {


    public void login(String phone, String password, Context context, Activity activity) {
        Call<PostRetrofitModel> t_login = APICONTROLLER.getInstance().getAPI().t_login(phone, password);
        t_login.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().equals("1111")) {

                    SharedPreferences.Editor editor = context.getSharedPreferences(Constants.TEACHERLOGIN, Context.MODE_PRIVATE).edit();
                    editor.putBoolean("IS_LOGGED_IN", true);
                    editor.putString("phone", phone);
                    editor.putString("password", password);

                    editor.apply();
                    Toast.makeText(context, "Login Approved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, TeacherHomePageActivity.class);
                    intent.putExtra("phone", phone);
                    intent.putExtra("loginType", "0");
                    context.startActivity(intent);
                    activity.finish();






                } else {
                    Toast.makeText(context, "Check your credentials", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkIfLoggedIn(String phone, String password, Activity activity, Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.TEACHERLOGIN, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false);

        if (isLoggedIn) {
            Intent intent = new Intent(context, TeacherHomePageActivity.class);
            intent.putExtra("phone", sharedPreferences.getString("phone", ""));
            intent.putExtra("loginType", "1");
            context.startActivity(intent);
            activity.finish();
        } else {
            Toast.makeText(context, "Please Log in with your credentials", Toast.LENGTH_SHORT).show();
        }

    }

    public void getInformation(String phone, Context context){

        Call<List<TeacherInformationModel>> t_information = APICONTROLLER.getInstance().getAPI().t_information(phone);
        t_information.enqueue(new Callback<List<TeacherInformationModel>>() {
            @Override
            public void onResponse(Call<List<TeacherInformationModel>> call, Response<List<TeacherInformationModel>> response) {
               SharedPreferences.Editor editor = context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).edit();
               editor.putString("t_uniquecode", "");
               editor.apply();

            }

            @Override
            public void onFailure(Call<List<TeacherInformationModel>> call, Throwable t) {

            }
        });

    }

}
