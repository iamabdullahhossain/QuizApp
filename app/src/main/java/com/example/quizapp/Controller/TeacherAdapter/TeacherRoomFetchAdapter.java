package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowRoomLayoutBinding;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;

import java.util.List;

public class TeacherRoomFetchAdapter extends RecyclerView.Adapter<TeacherRoomFetchAdapter.ViewHolder> {

    List<TeacherRoomFetchModel> list;
    Context context;
    onClick click;

    public TeacherRoomFetchAdapter(List<TeacherRoomFetchModel> list, Context context, onClick click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowRoomLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeacherRoomFetchModel model = list.get(position);

        holder.binding.roomNameTV.setText(model.getrName());
        holder.binding.batchTV.setText(model.getrBatch());
        holder.binding.detailsTV.setText(model.getrDescription());
        holder.itemView.setOnClickListener(view -> {
            click.onClick(model);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowRoomLayoutBinding binding;

        public ViewHolder(@NonNull RowRoomLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClick {
        void onClick(TeacherRoomFetchModel model);
    }
}
