package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowAcceptedstudentsLayoutBinding;
import com.example.quizapp.model.teacher.StudentAcceptedListModel;

import java.util.List;

public class StudentAcceptedListAdapter extends RecyclerView.Adapter<StudentAcceptedListAdapter.ViewHolde> {
    List<StudentAcceptedListModel> list;
    Context context;
    StudentAcceptInterface studentAcceptInterface;

    public StudentAcceptedListAdapter(List<StudentAcceptedListModel> list, Context context, StudentAcceptInterface studentAcceptInterface) {
        this.list = list;
        this.context = context;
        this.studentAcceptInterface = studentAcceptInterface;
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolde(RowAcceptedstudentsLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {

        StudentAcceptedListModel model = list.get(position);

        holder.binding.nameTV.setText(model.getsName());
        holder.binding.batchTV.setText(model.getsBatch());
        holder.binding.idTV.setText(model.getsId());

        holder.itemView.setOnClickListener(view -> {
            studentAcceptInterface.onClick(model);

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolde extends RecyclerView.ViewHolder {
        RowAcceptedstudentsLayoutBinding binding;

        public ViewHolde(@NonNull RowAcceptedstudentsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface StudentAcceptInterface {

        void onClick(StudentAcceptedListModel model);

    }

}
