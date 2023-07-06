package com.example.quizapp.View.teacher;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.Controller.teacher.CreateRoomController;
import com.example.quizapp.databinding.ActivityCreateRoomBinding;
import com.example.quizapp.model.constants.Constants;

public class CreateRoomActivity extends AppCompatActivity {
    ActivityCreateRoomBinding binding;
    CreateRoomController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new CreateRoomController();
        controller.setCode(binding.roomCodeTV);

        binding.createRoomBTN.setOnClickListener(view -> {

            controller.createRoom(getSharedPreferences(Constants.TEACHERINFO, MODE_PRIVATE).getString("t_uniquecode", " "),
                    getSharedPreferences(Constants.TEACHERLOGIN,MODE_PRIVATE).getString("phone", ""),
                    binding.nameET.getText().toString(),
                    binding.batchET.getText().toString(),
                    binding.descriptionET.getText().toString(),
                    binding.getRoot().getContext(),
                    this);

        });

        registerForContextMenu(binding.roomCodeTV);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, v.getId(), 0, "Copy");
        menu.setHeaderTitle("Copy Room Code");
        TextView textView = (TextView) v;
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", textView.getText());
        manager.setPrimaryClip(clipData);

    }


}