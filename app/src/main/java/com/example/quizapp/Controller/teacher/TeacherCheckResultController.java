package com.example.quizapp.Controller.teacher;

import android.widget.TextView;

import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.teacher.TeacherResultBoradModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherCheckResultController {


    int correctAnswer = 0;
    int wrongAnswer = 0;
    int totalMarks = 0;

    int obtainedMarks = 0;

    public void checkResult(String r_code, String s_id, TextView totalQuestionTV, TextView solvedQuestionTV, TextView correctTV, TextView wrongAnswerTV, TextView totalMarksTV, TextView obtainedMarksTV) {

        Call<List<TeacherResultBoradModel>> result = APICONTROLLER.getInstance().getAPI().result(r_code, s_id);
        result.enqueue(new Callback<List<TeacherResultBoradModel>>() {
            @Override
            public void onResponse(Call<List<TeacherResultBoradModel>> call, Response<List<TeacherResultBoradModel>> response) {
                if (response.body().size() > 0) {


                    totalQuestionTV.setText(response.body().size() + "");
                    solvedQuestionTV.setText(response.body().size() + "");

                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).gettMarking().contains("1")) {
                            correctAnswer = correctAnswer + 1;
                        }
                    }

                    correctTV.setText(correctAnswer+"");

                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).gettMarking().contains("0")) {
                            wrongAnswer = wrongAnswer + 1;
                        }
                    }

                    wrongAnswerTV.setText(wrongAnswer+"");


                    for (int i = 0; i<response.body().size(); i++){

                        totalMarks = totalMarks+Integer.parseInt(response.body().get(i).getqMarks());


                    }

                    totalMarksTV.setText(totalMarks+"");

                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).gettMarking().contains("1")) {
                            obtainedMarks = obtainedMarks+Integer.parseInt(response.body().get(i).getqMarks());
                        }
                    }

                    obtainedMarksTV.setText(obtainedMarks+"");






                }
            }

            @Override
            public void onFailure(Call<List<TeacherResultBoradModel>> call, Throwable t) {

            }
        });


    }


}
