package com.example.quizapp.Controller.teacher;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.TeacherAdapter.TeacherAcceptRequestAdapter;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.teacher.StudentRequestModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherAcceptRequestController {
    TeacherAcceptRequestAdapter adapter;


    public void showRequests(RecyclerView recyclerView, String r_code, Context context, TextView noReqTV) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        Call<List<StudentRequestModel>> s_req = APICONTROLLER.getInstance().getAPI().s_request(r_code);
        s_req.enqueue(new Callback<List<StudentRequestModel>>() {
            @Override
            public void onResponse(Call<List<StudentRequestModel>> call, Response<List<StudentRequestModel>> response) {
                if (response.body().size() > 0) {

                    noReqTV.setVisibility(View.GONE);
                    adapter = new TeacherAcceptRequestAdapter(response.body(), context, new TeacherAcceptRequestAdapter.onClick() {
                        @Override
                        public void itemClick(StudentRequestModel model) {

                            Call<PostRetrofitModel> accept = APICONTROLLER.getInstance().getAPI().accept_request(model.getsId(), model.getrCode());
                            accept.enqueue(new Callback<PostRetrofitModel>() {
                                @Override
                                public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                                    Toast.makeText(context, "Request accepted", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                                }
                            });


                        }

                        @Override
                        public void removeItem(List<StudentRequestModel> list, int position, TeacherAcceptRequestAdapter.ViewHolder holder) {
                            list.remove(holder.getAdapterPosition());
                            adapter.notifyItemRemoved(holder.getAdapterPosition());

                            if (list.size() < 1) {
                                noReqTV.setVisibility(View.VISIBLE);
                            }
                        }


                    });

                    recyclerView.setAdapter(adapter);


                } else {
                    recyclerView.setVisibility(View.GONE);
                    noReqTV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<StudentRequestModel>> call, Throwable t) {
                Toast.makeText(context, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
