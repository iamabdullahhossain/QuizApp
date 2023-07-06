package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowAcceptrequestLayoutBinding;
import com.example.quizapp.model.teacher.StudentRequestModel;

import java.util.List;

public class TeacherAcceptRequestAdapter extends RecyclerView.Adapter<TeacherAcceptRequestAdapter.ViewHolder> {

    List<StudentRequestModel> list;
    Context context;
    onClick click;

    public TeacherAcceptRequestAdapter(List<StudentRequestModel> list, Context context, onClick click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowAcceptrequestLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentRequestModel model = list.get(position);

        holder.binding.studentIDTV.setText(model.getsId());
        holder.binding.batchTV.setText(model.getsBatch());
        holder.binding.studentNameTV.setText(model.getsName());

        holder.binding.acceptBTN.setOnClickListener(view -> {
            click.itemClick(model);
            click.removeItem(list, position);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RowAcceptrequestLayoutBinding binding;

        public ViewHolder(@NonNull RowAcceptrequestLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClick {
        void itemClick(StudentRequestModel model);

        void removeItem(List<StudentRequestModel> list, int position);
    }
}
