package com.example.quizapp.Controller.student;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Controller.StudentAdapter.StudentQuestionAdapter;
import com.example.quizapp.View.student.Fragment.ExamFragment;
import com.example.quizapp.View.student.StudentSubmitActivity;
import com.example.quizapp.databinding.FragmentExamTwoBinding;
import com.example.quizapp.model.retrofit.APICONTROLLER;
import com.example.quizapp.model.retrofit.PostRetrofitModel;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.example.quizapp.model.student.StudentQuestionModel;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamFragmentController implements StudentQuestionAdapter.ExamInterface {

    List<StudentQuestionModel> studentQuestionModelList;
    int index=0;
    private static final String TAG = "ExamFragmentController";



    public void showQuestions(RecyclerView recyclerView, Context context, String r_code, TextView noExamTV, Button submitBTN, String s_id) {


        Call<PostRetrofitModel> check = APICONTROLLER.getInstance().getAPI().checkAttempt(s_id, r_code);
        check.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().equals("100")) {

                    noExamTV.setVisibility(View.VISIBLE);
                    noExamTV.setText("You have already attempted this exam!");
                    submitBTN.setVisibility(View.GONE);

                } else {

                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setHasFixedSize(false);

                    Call<List<StudentQuestionModel>> question = APICONTROLLER.getInstance().getAPI().question(r_code);
                    question.enqueue(new Callback<List<StudentQuestionModel>>() {
                        @Override
                        public void onResponse(Call<List<StudentQuestionModel>> call, Response<List<StudentQuestionModel>> response) {
                            studentQuestionModelList = response.body();


                            if (response.body().size() > 0) {
                                noExamTV.setVisibility(View.GONE);
                                submitBTN.setVisibility(View.VISIBLE);
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
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });


    }

    public void submitAnswer(Context context, List<StudentQuestionModel> studentQuestionModelList, StudentJoinedRoomModel model, Button button, ExamFragment fragment) {

        attempted(model);

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
        fragment.getActivity().finish();


    }

    private void attempted(StudentJoinedRoomModel model) {

        Call<PostRetrofitModel> attempt = APICONTROLLER.getInstance().getAPI().attend(
                model.getsId(),
                model.getrCode(),
                model.getrBatch()
        );

        attempt.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });


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

    public void showSeparateQuestions(Context context, String r_code, String s_id, TextView noExamTV,
                                      int questionNumber, FragmentExamTwoBinding binding) {

        Call<PostRetrofitModel> check = APICONTROLLER.getInstance().getAPI().checkAttempt(s_id, r_code);
        check.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().equals("100")) {


                    noExamTV.setVisibility(View.VISIBLE);
                    noExamTV.setText("You have already attempted this exam!");
                    binding.nextBTN.setVisibility(View.GONE);
                } else {
                    getQuestion(r_code,questionNumber,binding);
                }
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });


    }

    private void getQuestion(String r_code, int questionNumber, FragmentExamTwoBinding binding) {
        Call<List<StudentQuestionModel>> question = APICONTROLLER.getInstance().getAPI().question(r_code);
        question.enqueue(new Callback<List<StudentQuestionModel>>() {
            @Override
            public void onResponse(Call<List<StudentQuestionModel>> call, Response<List<StudentQuestionModel>> response) {

                studentQuestionModelList=response.body();
                Log.d(TAG, "onResponse: "+studentQuestionModelList.toString());

                setQuestion(0,binding);




            }

            @Override
            public void onFailure(Call<List<StudentQuestionModel>> call, Throwable t) {
                Toast.makeText(binding.getRoot().getContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setQuestion(int _index, FragmentExamTwoBinding binding) {
        if (_index<studentQuestionModelList.size()){

            //StudentQuestionModel model = response.body().get(questionNumber);
            StudentQuestionModel model = studentQuestionModelList.get(_index);
            Log.d(TAG, "setQuestion: "+model.toString());
            Log.d(TAG, "binding: "+binding);


            if (model.getqType().equals("0")  ) {
                Log.d(TAG, "getqType: 0");
                binding.questionTV.setText(model.getqQuestion());
                binding.optionATV.setText("a) " + model.getqA());
                binding.optionBTV.setText("b) " + model.getqB());
                binding.optionCTV.setText("c) " + model.getqC());
                binding.optionDTV.setText("d) " + model.getqD());
                binding.totalMarksTV.setText("Total marks: " + model.getqMarks());
                binding.headline2.setText("Choose the right answer: ");

                binding.answerET.setVisibility(View.GONE);

                binding.radioGroup.clearCheck();

                binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {


                        int selectedID = binding.radioGroup.getCheckedRadioButtonId();
                        if (selectedID == -1) {

                        } else {
                            RadioButton radioButton = binding.radioGroup.findViewById(selectedID);
                            // examInterface.getAnswer(radioButton.getText().toString(), model, list, position);
                        }


                    }
                });


            } else if (Objects.equals(model.getqType(), "1")) {
                Log.d(TAG, "getqType: 1");
                binding.questionTV.setText(model.getqQuestion());
                binding.optionATV.setText("a) " + model.getqA());
                binding.optionBTV.setText("b) " + model.getqB());
                binding.totalMarksTV.setText("Total marks: " + model.getqMarks());
                binding.headline2.setText("Choose the right answer: ");

                binding.optionCTV.setVisibility(View.GONE);
                binding.optionDTV.setVisibility(View.GONE);
                binding.answerET.setVisibility(View.GONE);
                binding.optionCRadio.setVisibility(View.GONE);
                binding.optionDRadio.setVisibility(View.GONE);


                binding.radioGroup.clearCheck();

                binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {


                        int selectedID = binding.radioGroup.getCheckedRadioButtonId();
                        if (selectedID == -1) {

                        } else {
                            RadioButton radioButton = binding.radioGroup.findViewById(selectedID);
                            //  examInterface.getAnswer(radioButton.getText().toString(), model, list, position);
                        }


                    }
                });


            } else if (Objects.equals(model.getqType(), "2")) {
                Log.d(TAG, "getqType: 2");
                binding.questionTV.setText(model.getqQuestion());
                binding.totalMarksTV.setText("Total marks: " + model.getqMarks());
                binding.headline2.setText("Write the answer below: ");

                binding.optionATV.setVisibility(View.GONE);
                binding.optionBTV.setVisibility(View.GONE);
                binding.optionCTV.setVisibility(View.GONE);
                binding.optionDTV.setVisibility(View.GONE);
                binding.optionARadio.setVisibility(View.GONE);
                binding.optionBRadio.setVisibility(View.GONE);
                binding.optionCRadio.setVisibility(View.GONE);
                binding.optionDRadio.setVisibility(View.GONE);

                binding.answerET.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // examInterface.getAnswer(charSequence+"");
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                        // examInterface.getAnswer(editable+"", model, list, position);
                    }
                });

            }
        }
        else {
            index=0;
            Toast.makeText(binding.getRoot().getContext(), "List finish"+_index, Toast.LENGTH_SHORT).show();

        }
    }

    public void nextQuestion(FragmentExamTwoBinding binding)
    {
        index++;
        setQuestion(index,binding);
    }

    public void nextBTN(int point,Context context, String r_code, String s_id, TextView noExamTV,
                        int questionNumber, FragmentExamTwoBinding binding){
        Toast.makeText(context, ""+point, Toast.LENGTH_SHORT).show();
        
        try {
            showSeparateQuestions(context, r_code, s_id, noExamTV, questionNumber, binding);
        }
        catch (Exception e){
            Toast.makeText(context, "List Completed", Toast.LENGTH_SHORT).show();
        }
        
    }


}
