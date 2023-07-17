package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowCheckresultLayoutBinding;
import com.example.quizapp.model.teacher.StudentAnswerSheetModel;
import com.example.quizapp.model.teacher.StudentRequestModel;

import java.util.List;

public class StudentAnswerSheetAdapter extends RecyclerView.Adapter<StudentAnswerSheetAdapter.ViewHolder> {

    List<StudentAnswerSheetModel> list;
    Context context;
    checkAnswer checkAnswer;

    public StudentAnswerSheetAdapter(List<StudentAnswerSheetModel> list, Context context, StudentAnswerSheetAdapter.checkAnswer checkAnswer) {
        this.list = list;
        this.context = context;
        this.checkAnswer = checkAnswer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowCheckresultLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentAnswerSheetModel model = list.get(position);

        holder.binding.questionTV.setText("Q. " + model.getqQuestion());
        holder.binding.studentAnswerTV.setText("Written answer: " + model.getsAnswer());

        if (model.getqAnswer().equals("")){
            holder.binding.correctAnswerTV.setVisibility(View.GONE);
        }else {
            holder.binding.correctAnswerTV.setText("Correct Answer: " + model.getqAnswer());
        }
        holder.binding.acceptBTN.setOnClickListener(view -> {
            checkAnswer.removeItem(list, position, holder);
            checkAnswer.accept(model);

        });
        holder.binding.rejectBTN.setOnClickListener(view -> {
            checkAnswer.removeItem(list, position, holder);
            checkAnswer.decline(model);

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowCheckresultLayoutBinding binding;

        public ViewHolder(@NonNull RowCheckresultLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface checkAnswer {
        void accept(StudentAnswerSheetModel model);
        void decline(StudentAnswerSheetModel model);

        void removeItem(List<StudentAnswerSheetModel> list, int position, ViewHolder holder);
    }


}
