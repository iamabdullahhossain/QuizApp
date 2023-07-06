package com.example.quizapp.Controller.TeacherAdapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quizapp.View.teacher.Fragment.QuizFragment;
import com.example.quizapp.View.teacher.Fragment.StudentFragment;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;

public class MyAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    TeacherRoomFetchModel model;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs, TeacherRoomFetchModel model) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.model = model;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                QuizFragment quizFragment = new QuizFragment(model);
                return quizFragment;
            case 1:
                StudentFragment studentFragment = new StudentFragment(model);
                return studentFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
