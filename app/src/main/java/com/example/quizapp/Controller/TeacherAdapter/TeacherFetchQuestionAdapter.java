package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowFetchquestionLayoutBinding;
import com.example.quizapp.model.teacher.TeacherFetchQuestionModel;

import java.util.List;

public class TeacherFetchQuestionAdapter extends RecyclerView.Adapter<TeacherFetchQuestionAdapter.ViewHolder> {
    List<TeacherFetchQuestionModel> list;
    Context context;

    public TeacherFetchQuestionAdapter(List<TeacherFetchQuestionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowFetchquestionLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeacherFetchQuestionModel model = list.get(position);

        if (model.getqType().equals("0")) {

            holder.binding.questionTV.setText("Q. " + model.getqQuestion());
            holder.binding.optionATV.setText("a) " + model.getqA());
            holder.binding.optionBTV.setText("b) " + model.getqB());
            holder.binding.optionCTV.setText("c) " + model.getqC());
            holder.binding.optionDTV.setText("d) " + model.getqD());
            holder.binding.correctAnswerTV.setText("Correct Answer:  " + model.getqAns());
            holder.binding.totalMarksTV.setText("Total marks: "+model.getqMarks());

        } else if (model.getqType().equals("1")) {

            holder.binding.optionCTV.setVisibility(View.GONE);
            holder.binding.optionDTV.setVisibility(View.GONE);


            holder.binding.questionTV.setText("Q. " + model.getqQuestion());
            holder.binding.optionATV.setText("a) " + model.getqA());
            holder.binding.optionBTV.setText("b) " + model.getqB());
            holder.binding.correctAnswerTV.setText("Correct Answer:  " + model.getqAns());
            holder.binding.totalMarksTV.setText("Total marks: "+model.getqMarks());


        } else if (model.getqType().equals("2")) {

            holder.binding.optionATV.setVisibility(View.GONE);
            holder.binding.optionBTV.setVisibility(View.GONE);
            holder.binding.optionCTV.setVisibility(View.GONE);
            holder.binding.optionDTV.setVisibility(View.GONE);
            holder.binding.correctAnswerTV.setVisibility(View.GONE);

            holder.binding.questionTV.setText("Q. " + model.getqQuestion());
            holder.binding.totalMarksTV.setText("Total marks: "+model.getqMarks());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowFetchquestionLayoutBinding binding;

        public ViewHolder(@NonNull RowFetchquestionLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
