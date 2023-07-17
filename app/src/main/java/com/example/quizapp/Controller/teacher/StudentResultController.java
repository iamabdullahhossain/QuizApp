package com.example.quizapp.Controller.teacher;

import android.content.Context;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.TeacherAdapter.StudentAnswerSheetAdapter;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.teacher.StudentAnswerSheetModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentResultController {
    StudentAnswerSheetAdapter adapter;

    public void getAnswerSheet(RecyclerView recyclerView, String r_code, String s_id, Context context, CardView cardView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<StudentAnswerSheetModel>> answer = APICONTROLLER.getInstance().getAPI().answersheet(r_code, s_id);
        answer.enqueue(new Callback<List<StudentAnswerSheetModel>>() {
            @Override
            public void onResponse(Call<List<StudentAnswerSheetModel>> call, Response<List<StudentAnswerSheetModel>> response) {

                if (response.body().size() > 0) {
                    cardView.setVisibility(View.GONE);
                    adapter = new StudentAnswerSheetAdapter(response.body(), context, new StudentAnswerSheetAdapter.checkAnswer() {
                        @Override
                        public void accept(StudentAnswerSheetModel model) {
                            acceptAnswer(model);

                        }

                        @Override
                        public void decline(StudentAnswerSheetModel model) {
                            declineAnswer(model);
                        }

                        @Override
                        public void removeItem(List<StudentAnswerSheetModel> list, int position, StudentAnswerSheetAdapter.ViewHolder holder) {
                            list.remove(holder.getAdapterPosition());
                            adapter.notifyItemRemoved(holder.getAdapterPosition());

                            if (list.size() < 1) {
                                cardView.setVisibility(View.VISIBLE);
                            }
                        }




                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    cardView.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<List<StudentAnswerSheetModel>> call, Throwable t) {

            }
        });


    }

    private void declineAnswer(StudentAnswerSheetModel model) {

        Call<PostRetrofitModel> check = APICONTROLLER.getInstance().getAPI().check(model.getqId(), model.getsId(), "0");
        check.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });


    }

    private void acceptAnswer(StudentAnswerSheetModel model) {

        Call<PostRetrofitModel> check = APICONTROLLER.getInstance().getAPI().check(model.getqId(), model.getsId(), "1");
        check.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });


    }


}
