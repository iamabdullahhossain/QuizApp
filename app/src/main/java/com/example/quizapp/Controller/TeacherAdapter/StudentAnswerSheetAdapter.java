package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowCheckresultLayoutBinding;
import com.example.quizapp.model.teacher.StudentAnswerSheetModel;

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
        holder.binding.questionTV.setText("Correct Answer: " + model.getqQuestion());
        holder.binding.acceptBTN.setOnClickListener(view -> {
            checkAnswer.check(model);
        });
        holder.binding.rejectBTN.setOnClickListener(view -> {
            checkAnswer.check(model);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowCheckresultLayoutBinding binding;

        public ViewHolder(@NonNull RowCheckresultLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface checkAnswer {
        void check(StudentAnswerSheetModel model);
    }


}
