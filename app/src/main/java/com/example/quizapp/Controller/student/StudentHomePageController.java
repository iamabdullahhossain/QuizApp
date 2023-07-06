package com.example.quizapp.Controller.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quizapp.Controller.StudentAdapter.StudentJoinedRoomAdapter;
import com.example.quizapp.R;
import com.example.quizapp.View.student.StudentExamRoomActivity;
import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.student.StudentInformationModel;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentHomePageController {

    List<TeacherRoomFetchModel> list;
    List<StudentInformationModel> studentInfoList;


    public void getJoinedRoom(RecyclerView recyclerView, LottieAnimationView animationView, TextView text, String id, Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<StudentJoinedRoomModel>> s_joined = APICONTROLLER.getInstance().getAPI().s_joinedRoom(id);

        s_joined.enqueue(new Callback<List<StudentJoinedRoomModel>>() {
            @Override
            public void onResponse(Call<List<StudentJoinedRoomModel>> call, Response<List<StudentJoinedRoomModel>> response) {

                if (response.body().size() > 0) {
                    animationView.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    StudentJoinedRoomAdapter adapter = new StudentJoinedRoomAdapter(response.body(), context, new StudentJoinedRoomAdapter.optionClick() {
                        @Override
                        public void onClick(StudentJoinedRoomModel model) {
                            Intent intent = new Intent(context, StudentExamRoomActivity.class);
                            intent.putExtra("model", model);
                            context.startActivity(intent);


                        }
                    });

                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<List<StudentJoinedRoomModel>> call, Throwable t) {

            }
        });


    }


    public void getInformation(String id, TextView studentName, TextView batch, Context context) {

        Call<List<StudentInformationModel>> s_info = APICONTROLLER.getInstance().getAPI().s_information(id);
        s_info.enqueue(new Callback<List<StudentInformationModel>>() {
            @Override
            public void onResponse(Call<List<StudentInformationModel>> call, Response<List<StudentInformationModel>> response) {
                studentName.setText(response.body().get(0).getsName());
                batch.setText(response.body().get(0).getsId() + "(" + response.body().get(0).getsBatch() + ")");

                studentInfoList = response.body();

                SharedPreferences.Editor editor = context.getSharedPreferences(Constants.STUDENTINFO, Context.MODE_PRIVATE).edit();
                editor.putString("s_uniquecode", response.body().get(0).getsUniquecode());
                editor.apply();

            }

            @Override
            public void onFailure(Call<List<StudentInformationModel>> call, Throwable t) {

            }
        });


    }

    public void getInformation(String id) {
        Call<List<StudentInformationModel>> s_info = APICONTROLLER.getInstance().getAPI().s_information(id);
        s_info.enqueue(new Callback<List<StudentInformationModel>>() {
            @Override
            public void onResponse(Call<List<StudentInformationModel>> call, Response<List<StudentInformationModel>> response) {


                studentInfoList = response.body();


            }

            @Override
            public void onFailure(Call<List<StudentInformationModel>> call, Throwable t) {

            }
        });

    }


    public void searchRoom(String roomCode, Context context, Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View customeLayout = activity.getLayoutInflater().inflate(R.layout.row_showroom_layout, null);
        builder.setView(customeLayout);
        AlertDialog dialog = builder.create();

        TextView roomName = customeLayout.findViewById(R.id.roomNameTV);
        TextView details = customeLayout.findViewById(R.id.detailsTV);
        Button joinBTN = customeLayout.findViewById(R.id.joinBTN);


        joinBTN.setOnClickListener(view -> {

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String requestDate = format.format(date);


            Call<PostRetrofitModel> s_joinroom = APICONTROLLER.getInstance().getAPI().s_joinroom(
                    list.get(0).gettName(),
                    list.get(0).gettUniquecode(),
                    list.get(0).getrCode(),
                    list.get(0).getrName(),
                    list.get(0).getrBatch(),
                    list.get(0).getrDescription(),
                    list.get(0).getrTime(),
                    studentInfoList.get(0).getsName(),
                    studentInfoList.get(0).getsId(),
                    studentInfoList.get(0).getsUniquecode(),
                    requestDate,
                    studentInfoList.get(0).getsBatch()


            );

            s_joinroom.enqueue(new Callback<PostRetrofitModel>() {
                @Override
                public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

                    Toast.makeText(context, "" + response.body().getReply(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }

                @Override
                public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                    Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            });


        });


        Call<List<TeacherRoomFetchModel>> s_roomfetch = APICONTROLLER.getInstance().getAPI().s_roomfetch(roomCode);
        s_roomfetch.enqueue(new Callback<List<TeacherRoomFetchModel>>() {
            @Override
            public void onResponse(Call<List<TeacherRoomFetchModel>> call, Response<List<TeacherRoomFetchModel>> response) {

                if (response.body().size() > 0) {
                    roomName.setText(response.body().get(0).getrName());
                    details.setText("Assigned By: " + response.body().get(0).gettName());
                    list = response.body();
                } else {
                    dialog.dismiss();
                }


            }

            @Override
            public void onFailure(Call<List<TeacherRoomFetchModel>> call, Throwable t) {

            }
        });


        dialog.show();


    }


}
