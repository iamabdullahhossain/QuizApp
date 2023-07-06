package com.example.quizapp.Controller.student;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.StudentAdapter.StudentQuestionAdapter;
import com.example.quizapp.View.student.Fragment.ExamFragment;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.example.quizapp.model.student.StudentQuestionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamFragmentController implements StudentQuestionAdapter.ExamInterface {

    StudentQuestionModel model;



    public void showQuestions(RecyclerView recyclerView, Context context,  String r_code, TextView noExamTV, Button submitBTN) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<StudentQuestionModel>> question = APICONTROLLER.getInstance().getAPI().question(r_code);
        question.enqueue(new Callback<List<StudentQuestionModel>>() {
            @Override
            public void onResponse(Call<List<StudentQuestionModel>> call, Response<List<StudentQuestionModel>> response) {

                if (response.body().size() > 0) {
                    noExamTV.setVisibility(View.GONE);
                    StudentQuestionAdapter adapter = new StudentQuestionAdapter(context, response.body(), new StudentQuestionAdapter.ExamInterface() {
                        @Override
                        public void onClick(StudentQuestionModel model) {

                        }

                        @Override
                        public void getAnswer(String answer, StudentQuestionModel model) {


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

    public void submitAnswer(Context context, StudentJoinedRoomModel model){




    }


    @Override
    public void onClick(StudentQuestionModel model) {

    }

    @Override
    public void getAnswer(String answer, StudentQuestionModel model) {



    }


}
