package com.example.quizapp.Controller.StudentAdapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quizapp.View.student.Fragment.ExamFragment;
import com.example.quizapp.View.student.Fragment.ResultFragment;
import com.example.quizapp.model.student.StudentJoinedRoomModel;

public class StudentViewAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;
    StudentJoinedRoomModel model;


    public StudentViewAdapter(Context context, FragmentManager fm, int totalTabs, StudentJoinedRoomModel model) {
        super(fm);
        context = context;
        this.totalTabs = totalTabs;
        this.model = model;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ExamFragment examFragment = new ExamFragment(model);
                return examFragment;
            case 1:
                ResultFragment resultFragment = new ResultFragment(model);
                return resultFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
