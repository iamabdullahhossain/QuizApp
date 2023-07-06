package com.example.quizapp.Controller.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.quizapp.View.student.StudentHomePageActivity;
import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentLoginController {

    public void logIn(String batchID, String password, Context context, Activity activity) {

        Call<PostRetrofitModel> s_login = APICONTROLLER.getInstance().getAPI().s_login(batchID, password);
        s_login.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().contains("DONE")) {

                    SharedPreferences.Editor editor = context.getSharedPreferences(Constants.STUDENTLOGIN, Context.MODE_PRIVATE).edit();
                    editor.putBoolean("IS_LOGGED_IN", true);
                    editor.putString("s_id", batchID);
                    editor.putString("s_password", password);
                    editor.apply();
                    Toast.makeText(context, "Login Approved", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, StudentHomePageActivity.class);
                    intent.putExtra("s_id", batchID);
                    intent.putExtra("loginType", "0");
                    context.startActivity(intent);
                    activity.finish();

                } else {

                    Toast.makeText(context, "Please provide right credentials", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: ID - " + batchID + " password - " + password + " response - "+response.body().getReply());

                }
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void checkIfLoggedIn(Context context, Activity activity) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.STUDENTLOGIN, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false);

        if (isLoggedIn) {
            Intent intent = new Intent(context, StudentHomePageActivity.class);
            intent.putExtra("s_id", sharedPreferences.getString("s_id", ""));
            intent.putExtra("loginType", "1");
            context.startActivity(intent);
            activity.finish();
        } else {
            Toast.makeText(context, "Please login with your credentials", Toast.LENGTH_SHORT).show();
        }


    }


}
