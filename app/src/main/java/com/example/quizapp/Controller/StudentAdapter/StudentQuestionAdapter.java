package com.example.quizapp.Controller.StudentAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowExamLayoutBinding;
import com.example.quizapp.model.student.StudentQuestionModel;

import java.util.List;

public class StudentQuestionAdapter extends RecyclerView.Adapter<StudentQuestionAdapter.ViewHolder> {

    Context context;
    List<StudentQuestionModel> list;
    ExamInterface examInterface;

    public StudentQuestionAdapter(Context context, List<StudentQuestionModel> list, ExamInterface examInterface) {
        this.context = context;
        this.list = list;
        this.examInterface = examInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowExamLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        StudentQuestionModel model = list.get(position);

        if (model.getqType().equals("0")) {

            holder.binding.questionTV.setText(model.getqQuestion());
            holder.binding.optionATV.setText("a) " + model.getqA());
            holder.binding.optionBTV.setText("b) " + model.getqB());
            holder.binding.optionCTV.setText("c) " + model.getqC());
            holder.binding.optionDTV.setText("d) " + model.getqD());
            holder.binding.totalMarksTV.setText("Total marks: " + model.getqMarks());
            holder.binding.headline2.setText("Choose the right answer: ");

            holder.binding.answerET.setVisibility(View.GONE);

            holder.binding.radioGroup.clearCheck();

            holder.binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {


                    int selectedID = holder.binding.radioGroup.getCheckedRadioButtonId();
                    if (selectedID==-1){

                    }else {
                        RadioButton radioButton = holder.binding.radioGroup.findViewById(selectedID);
                        examInterface.getAnswer(radioButton.getText().toString(),model, list, position);
                    }


                }
            });




        } else if (model.getqType().equals("1")) {
            holder.binding.questionTV.setText(model.getqQuestion());
            holder.binding.optionATV.setText("a) " + model.getqA());
            holder.binding.optionBTV.setText("b) " + model.getqB());
            holder.binding.totalMarksTV.setText("Total marks: " + model.getqMarks());
            holder.binding.headline2.setText("Choose the right answer: ");

            holder.binding.optionCTV.setVisibility(View.GONE);
            holder.binding.optionDTV.setVisibility(View.GONE);
            holder.binding.answerET.setVisibility(View.GONE);
            holder.binding.optionCRadio.setVisibility(View.GONE);
            holder.binding.optionDRadio.setVisibility(View.GONE);


            holder.binding.radioGroup.clearCheck();

            holder.binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {


                    int selectedID = holder.binding.radioGroup.getCheckedRadioButtonId();
                    if (selectedID==-1){

                    }else {
                        RadioButton radioButton = holder.binding.radioGroup.findViewById(selectedID);
                        examInterface.getAnswer(radioButton.getText().toString(), model, list, position);
                    }


                }
            });


        } else if (model.getqType().equals("2")) {
            holder.binding.questionTV.setText(model.getqQuestion());
            holder.binding.totalMarksTV.setText("Total marks: " + model.getqMarks());
            holder.binding.headline2.setText("Write the answer below: ");

            holder.binding.optionATV.setVisibility(View.GONE);
            holder.binding.optionBTV.setVisibility(View.GONE);
            holder.binding.optionCTV.setVisibility(View.GONE);
            holder.binding.optionDTV.setVisibility(View.GONE);
            holder.binding.optionARadio.setVisibility(View.GONE);
            holder.binding.optionBRadio.setVisibility(View.GONE);
            holder.binding.optionCRadio.setVisibility(View.GONE);
            holder.binding.optionDRadio.setVisibility(View.GONE);

            holder.binding.answerET.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   // examInterface.getAnswer(charSequence+"");
                }

                @Override
                public void afterTextChanged(Editable editable) {

                    examInterface.getAnswer(editable+"", model, list, position);
                }
            });








        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RowExamLayoutBinding binding;

        public ViewHolder(RowExamLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ExamInterface{

        void getAnswer(String answer, StudentQuestionModel model, List<StudentQuestionModel> list, int position);

    }
}
