package com.example.quizapp.Controller.StudentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.databinding.RowStudentjoinedroomLayoutBinding;
import com.example.quizapp.model.student.StudentJoinedRoomModel;

import java.util.List;

public class StudentJoinedRoomAdapter extends RecyclerView.Adapter<StudentJoinedRoomAdapter.ViewHolder> {

    List<StudentJoinedRoomModel> list;
    Context context;
    optionClick click;

    public StudentJoinedRoomAdapter(List<StudentJoinedRoomModel> list, Context context, optionClick click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowStudentjoinedroomLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentJoinedRoomModel model;
        model = list.get(position);

        holder.binding.roomNameTV.setText(model.getrName());
        holder.binding.descriptionTV.setText(model.getrDescription());
        holder.binding.assignedTV.setText("Assigned By: " + model.gettName());
        if (model.getsStatus().contains("0")) {
            holder.binding.statusTV.setText("Pending");
        } else {
            holder.binding.statusTV.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(view -> {

            if (model.getsStatus().contains("0")) {

                Toast.makeText(context, "Your request to join this room is in waiting room", Toast.LENGTH_SHORT).show();

            } else {

                click.onClick(model);

            }


        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowStudentjoinedroomLayoutBinding binding;

        public ViewHolder(@NonNull RowStudentjoinedroomLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    public interface optionClick {

        void onClick(StudentJoinedRoomModel model);


    }
}
