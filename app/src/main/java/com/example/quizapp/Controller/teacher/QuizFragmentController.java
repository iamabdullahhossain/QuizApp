package com.example.quizapp.Controller.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.TeacherAdapter.TeacherFetchQuestionAdapter;
import com.example.quizapp.R;
import com.example.quizapp.model.constants.Constants;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.teacher.TeacherFetchQuestionModel;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizFragmentController {
    String q_ans;


    public void startTimer(Context context, String r_code, String time, TextView timerTV, FloatingActionButton editTimeBTN, FloatingActionButton playBTN) {





        String[] timeComponents = time.split(":");
        String minutesString = timeComponents[0];
        String secondsString = timeComponents[1];

        int minutes = Integer.parseInt(minutesString);
        int seconds = Integer.parseInt(secondsString);

        long totalTimeInMillis = (minutes * 60 + seconds) * 1000;


        Intent serviceIntent = new Intent(context, TimeService.class);
        serviceIntent.putExtra("totalTimeInMillis", totalTimeInMillis);
        context.startService(serviceIntent);
        Toast.makeText(context, "Exam Started!", Toast.LENGTH_SHORT).show();


       CountDownTimer countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long l) {
                long minutes = (l / (60 * 1000));
                long seconds = (l % (60 * 1000)) / 1000;
                String timeLeftFormat = String.format("%02d:%02d", minutes, seconds);
                timerTV.setText(timeLeftFormat);
                editTimeBTN.setVisibility(View.GONE);
                playBTN.setVisibility(View.GONE);

            }

            @Override
            public void onFinish() {
                timerTV.setText("Time Up");

                editTimeBTN.setVisibility(View.VISIBLE);
                playBTN.setVisibility(View.VISIBLE);
                stopExam(context, r_code, timerTV );
            }
        };
        countDownTimer.start();

//        if (active == "YES") {
//
//        } else if (active == "NO") {
//
//
//            countDownTimer.cancel();
//
//            timerTV.setText("00:00");
//        }
        
        
        
    }

    public void stopTimer(Context context, String r_code, TextView timeTV) {
        Intent intent = new Intent(context, TimeService.class);
        context.stopService(intent);

        stopExam(context, r_code,timeTV );


    }


    public void startExam(Context context,  String r_code, TextView timeHeadTV, TextView timeTV) {


        //timeHeadTV.setVisibility(View.GONE);
//        timeTV.setText("Exam Started");

        Call<PostRetrofitModel> startExam = APICONTROLLER.getInstance().getAPI().exam(r_code,
                "1");

        startExam.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                Toast.makeText(context, "Exam Started. Please don't close the window untill it's done!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void stopExam(Context context, String r_code, TextView timeTV) {

//        timeTV.setText("Time Up!");

        Call<PostRetrofitModel> stopExam = APICONTROLLER.getInstance().getAPI().exam(r_code, "0");
        stopExam.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                Toast.makeText(context, "Exam ended here...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void mcqQuestion(Context context, Fragment fragment, TeacherRoomFetchModel model) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View customLayout = fragment.getLayoutInflater().inflate(R.layout.row_mcq_layout, null);

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        EditText question = customLayout.findViewById(R.id.questionET);
        EditText optionA = customLayout.findViewById(R.id.optionOneET);
        EditText optionB = customLayout.findViewById(R.id.optionTwoET);
        EditText optionC = customLayout.findViewById(R.id.optionThreeET);
        EditText optionD = customLayout.findViewById(R.id.optionFourET);
        EditText totalMarks = customLayout.findViewById(R.id.totalMarks);

        RadioGroup radioGroup = customLayout.findViewById(R.id.radioGroup);
        RadioButton option1 = customLayout.findViewById(R.id.optionARadio);
        RadioButton option2 = customLayout.findViewById(R.id.optionBRadio);
        RadioButton option3 = customLayout.findViewById(R.id.optionCRadio);
        RadioButton option4 = customLayout.findViewById(R.id.optionDRadio);

        Button pushBTN = customLayout.findViewById(R.id.pushBTN);


        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
            }
        });


        pushBTN.setOnClickListener(view -> {

            if (optionA.getText().toString().length() == 0 || optionB.getText().toString().length() == 0
                    || optionC.getText().toString().length() == 0 || optionD.getText().toString().length() == 0
                    || question.getText().toString().length() == 0 || totalMarks.getText().toString().length() == 0) {
                Toast.makeText(context, "Please Fill Up All The Fields", Toast.LENGTH_SHORT).show();
            } else {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                if (selectedID == -1) {
                    Toast.makeText(context, "No answer has been selected", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton radioButton = radioGroup.findViewById(selectedID);
                    q_ans = radioButton.getText().toString();

                    Call<PostRetrofitModel> t_mcq = APICONTROLLER.getInstance().getAPI().t_mcq(
                            context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_name", " "),
                            context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_uniquecode", " "),
                            question.getText().toString(),
                            "0",
                            optionA.getText().toString(),
                            optionB.getText().toString(),
                            optionC.getText().toString(),
                            optionD.getText().toString(),
                            q_ans,
                            totalMarks.getText().toString(),
                            model.getrCode()


                    );

                    t_mcq.enqueue(new Callback<PostRetrofitModel>() {
                        @Override
                        public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                            Toast.makeText(context, "UPDATED", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                        }
                    });

                    dialog.dismiss();
                }


            }


        });


        dialog.show();


    }


    public void trueFalse(Context context, Fragment fragment, TeacherRoomFetchModel model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View customLayout = fragment.getLayoutInflater().inflate(R.layout.row_truefalse_layout, null);

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        EditText question = customLayout.findViewById(R.id.questionET);
        EditText optionA = customLayout.findViewById(R.id.optionOneET);
        EditText optionB = customLayout.findViewById(R.id.optionTwoET);
        EditText totalMarks = customLayout.findViewById(R.id.totalMarksET);


        RadioGroup radioGroup = customLayout.findViewById(R.id.tfradioGroup);
        RadioButton option1 = customLayout.findViewById(R.id.optionARadio);
        RadioButton option2 = customLayout.findViewById(R.id.optionBRadio);

        Button pushBTN = customLayout.findViewById(R.id.pushBTN);
        radioGroup.clearCheck();

        pushBTN.setOnClickListener(view -> {
            if (optionA.getText().toString().length() == 0 || optionB.getText().toString().length() == 0
                    || question.getText().toString().length() == 0 || totalMarks.getText().toString().length() == 0) {
                Toast.makeText(context, "Please Fill Up All The Fields", Toast.LENGTH_SHORT).show();
            } else {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                if (selectedID == -1) {
                    Toast.makeText(context, "No answer has been selected", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton radioButton = radioGroup.findViewById(selectedID);
                    q_ans = radioButton.getText().toString();

                    Call<PostRetrofitModel> t_tf = APICONTROLLER.getInstance().getAPI().t_tf(

                            context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_name", " "),
                            context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_uniquecode", " "),
                            question.getText().toString(),
                            "1",
                            optionA.getText().toString(),
                            optionB.getText().toString(),
                            q_ans,
                            model.getrCode(),
                            totalMarks.getText().toString()

                    );

                    t_tf.enqueue(new Callback<PostRetrofitModel>() {
                        @Override
                        public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                            Toast.makeText(context, "UPDATED", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                        }
                    });

                    dialog.dismiss();

                }


            }


        });


        dialog.show();


    }


    public void shortAnswer(Context context, Fragment fragment, TeacherRoomFetchModel model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View customLayout = fragment.getLayoutInflater().inflate(R.layout.row_shortanswer_layout, null);

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        EditText question = customLayout.findViewById(R.id.questionET);
        EditText totalMarks = customLayout.findViewById(R.id.totalMarksET);
        Button pushBTN = customLayout.findViewById(R.id.pushBTN);

        pushBTN.setOnClickListener(view -> {

            if (question.getText().toString().length() == 0 || totalMarks.getText().toString().length() == 0) {
                Toast.makeText(context, "Please Fill Up All The Fields", Toast.LENGTH_SHORT).show();
            } else {

                Call<PostRetrofitModel> t_short = APICONTROLLER.getInstance().getAPI().t_shortanswer(
                        context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_name", " "),
                        context.getSharedPreferences(Constants.TEACHERINFO, Context.MODE_PRIVATE).getString("t_uniquecode", " "),
                        question.getText().toString(),
                        "2",
                        model.getrCode(),
                        totalMarks.getText().toString()


                );

                t_short.enqueue(new Callback<PostRetrofitModel>() {
                    @Override
                    public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                        Toast.makeText(context, "UPDATED", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                    }
                });
                dialog.dismiss();


            }


        });


        dialog.show();


    }


    public void fetchQuestions(String t_uniquecode, String r_code, RecyclerView recyclerView, Context context) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        Call<List<TeacherFetchQuestionModel>> t_fetchQuestion = APICONTROLLER.getInstance().getAPI().t_fetchquestion(t_uniquecode, r_code);
        t_fetchQuestion.enqueue(new Callback<List<TeacherFetchQuestionModel>>() {
            @Override
            public void onResponse(Call<List<TeacherFetchQuestionModel>> call, Response<List<TeacherFetchQuestionModel>> response) {


                TeacherFetchQuestionAdapter adapter = new TeacherFetchQuestionAdapter(response.body(), context);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<TeacherFetchQuestionModel>> call, Throwable t) {

            }
        });

    }

    public void fetchTime(String r_code, TextView textView) {

        Call<List<TeacherRoomFetchModel>> getTime = APICONTROLLER.getInstance().getAPI().t_fetchroom("", "", r_code);
        getTime.enqueue(new Callback<List<TeacherRoomFetchModel>>() {
            @Override
            public void onResponse(Call<List<TeacherRoomFetchModel>> call, Response<List<TeacherRoomFetchModel>> response) {
                textView.setText(response.body().get(0).getrTime());
            }

            @Override
            public void onFailure(Call<List<TeacherRoomFetchModel>> call, Throwable t) {

            }
        });


    }

    public void updateTime(String min, String sec, TeacherRoomFetchModel model, TextView textView) {
        Call<PostRetrofitModel> updateTime = APICONTROLLER.getInstance().getAPI().updateTime(model.getrCode(),
                min + ":" + sec);

        updateTime.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                textView.setText(min + ":" + sec);
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });
    }


}
