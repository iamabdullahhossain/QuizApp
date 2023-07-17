package com.example.quizapp.Controller.student;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.StudentAdapter.StudentQuestionAdapter;
import com.example.quizapp.View.student.StudentSubmitActivity;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.example.quizapp.model.student.StudentQuestionModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamFragmentController implements StudentQuestionAdapter.ExamInterface {

    List<StudentQuestionModel> studentQuestionModelList;


    public void showQuestions(RecyclerView recyclerView, Context context, String r_code, TextView noExamTV, Button submitBTN) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<StudentQuestionModel>> question = APICONTROLLER.getInstance().getAPI().question(r_code);
        question.enqueue(new Callback<List<StudentQuestionModel>>() {
            @Override
            public void onResponse(Call<List<StudentQuestionModel>> call, Response<List<StudentQuestionModel>> response) {
                studentQuestionModelList = response.body();


                if (response.body().size() > 0) {
                    noExamTV.setVisibility(View.GONE);
                    StudentQuestionAdapter adapter = new StudentQuestionAdapter(context, response.body(), new StudentQuestionAdapter.ExamInterface() {

                        @Override
                        public void getAnswer(String answer, StudentQuestionModel model, List<StudentQuestionModel> list, int position) {
                            model.setS_answer(answer);
                        }


                    });

                    recyclerView.setAdapter(adapter);
                } else {
                    noExamTV.setVisibility(View.VISIBLE);
                    submitBTN.setVisibility(View.GONE);

                }


            }

            @Override
            public void onFailure(Call<List<StudentQuestionModel>> call, Throwable t) {

            }
        });


    }

    public void submitAnswer(Context context, List<StudentQuestionModel> studentQuestionModelList, StudentJoinedRoomModel model, Button button) {
        for (int i = 0; i < studentQuestionModelList.size(); i++) {
            Call<PostRetrofitModel> answer = APICONTROLLER.getInstance().getAPI().answer(model.getsName(), model.getsId(), model.getsUniquecode(), model.getrBatch(), studentQuestionModelList.get(i).gettName(), studentQuestionModelList.get(i).gettUniquecode(), studentQuestionModelList.get(i).getrCode(), model.getrName(), studentQuestionModelList.get(i).getqQuestion(), studentQuestionModelList.get(i).getId(), studentQuestionModelList.get(i).getqAns(), studentQuestionModelList.get(i).getS_answer(), studentQuestionModelList.get(i).getqMarks()

            );
            answer.enqueue(new Callback<PostRetrofitModel>() {
                @Override
                public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

                }

                @Override
                public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                }
            });
        }

        button.setVisibility(View.GONE);
        context.startActivity(new Intent(context, StudentSubmitActivity.class));


    }

//    private void insertItems(List<StudentQuestionModel> list, int i, StudentJoinedRoomModel stuList) {
//
//
//        Call<PostRetrofitModel> answer = APICONTROLLER.getInstance().getAPI().answer(
//
//                stuList.getsName(),
//                stuList.getsId(),
//                stuList.getsUniquecode(),
//                stuList.getrBatch(),
//                list.get(i).gettName(),
//                list.get(i).gettUniquecode(),
//                list.get(i).getrCode(),
//                stuList.getrName(),
//                list.get(i).getqQuestion(),
//                list.get(i).getId(),
//                list.get(i).getqAns(),
//                list.get(i).getqMarks()
//
//        );
//        answer.enqueue(new Callback<PostRetrofitModel>() {
//            @Override
//            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
//
//            }
//        });
//
//
//    }

    public String GetList() {

        String list = new Gson().toJson(studentQuestionModelList);
        return list;
    }


    @Override
    public void getAnswer(String answer, StudentQuestionModel model, List<StudentQuestionModel> list, int position) {


    }


}
