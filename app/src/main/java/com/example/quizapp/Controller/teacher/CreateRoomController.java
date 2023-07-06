package com.example.quizapp.Controller.teacher;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomController {

    Random random = new Random();

    int randomNumber = random.nextInt(1000);

    String roomCode = "xY9" + randomNumber + "uiY";

    public void setCode(TextView randomCode) {

        randomCode.setText(roomCode);



    }

    public void createRoom(String uniqueCode, String t_phone, String roomName, String roomBatch, String roomDescription, Context context, Activity activity) {




        String t_name = context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_name", " ");
        String t_designation = context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_designation", " ");


        Log.d("TAG", "createRoom: "+uniqueCode+" "+roomBatch+" "+roomName+"  "+roomDescription+ " "+t_name+" "+t_designation+ " "+roomCode);


        Call<PostRetrofitModel> createroom = APICONTROLLER.getInstance().getAPI().t_createroom(t_name, t_designation,t_phone, uniqueCode, roomCode,
                roomName, roomBatch, roomDescription);

        createroom.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().contains("DONE")) {
                    Toast.makeText(context, "Room Created", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
                else {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
