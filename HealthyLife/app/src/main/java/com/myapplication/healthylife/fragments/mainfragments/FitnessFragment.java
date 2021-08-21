package com.myapplication.healthylife.fragments.mainfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentFitnessBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.model.User;
import com.myapplication.healthylife.recycleviewadapters.ExerciseRecViewAdapter;

import java.util.ArrayList;

public class FitnessFragment extends Fragment {
    private FragmentFitnessBinding binding;
    private ExerciseRecViewAdapter exerciseRecViewAdapter;
    private User user;
    private DatabaseHelper db;
    private ArrayList<Exercise> list = new ArrayList<>();
    private SharedPreferences sharedPreferences = AppPrefs.getInstance(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        binding = FragmentFitnessBinding.inflate(getLayoutInflater());
        String data = AppPrefs.getInstance(getContext()).getString("user", null);
        user = new Gson().fromJson(data, User.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleViews();
    }

    private void initRecycleViews() {
//        list = getListOfExercises(exercises);
        exerciseRecViewAdapter = new ExerciseRecViewAdapter();
        exerciseRecViewAdapter.setExercises(list);
        binding.recommendedRecView.setAdapter(exerciseRecViewAdapter);
        binding.recommendedRecView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<Exercise> getListOfExercises(ArrayList<Exercise> exercise)    {
        boolean startRecommended = false;
        boolean startOthers = false;
        ArrayList<Exercise> result = new ArrayList<>();
        int type;
        double bmi = user.getBmi();
        if (bmi >= 30) {
            type = 5;
        }else if(bmi >= 25 && bmi <= 29.9) {
            type = 4;
        }else if(bmi >= 23 && bmi <= 24.9)  {
            type = 3;
        }else if(bmi >= 18.5 && bmi <= 22.9)    {
            type = 2;
        }else   {
            type = 1;
        }

        //add recommended ex
        for (Exercise ex: exercise)    {
            for (int i: ex.getTypes())  {
                if (i == type && !startRecommended)  {
                    ex.setFirst(true);
                    ex.setRecommended(true);
                    result.add(ex);
                    startRecommended = true;
                    break;
                }else if(i == type && startRecommended) {
                    ex.setRecommended(true);
                    result.add(ex);
                    break;
                }
            }
        }

        boolean isOthers;
        for (Exercise ex: exercise)    {
            isOthers = true;
            for (int i: ex.getTypes()) {
               if(i == type)    {
                   isOthers = false;
               }
            }
            if (isOthers && !startOthers) {
                ex.setFirst(true);
                ex.setOthers(true);
                result.add(ex);
                startOthers = true;
            } else if (isOthers && startOthers) {
                ex.setOthers(true);
                result.add(ex);
            }
        }

        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Exercise ex: list
             ) {
            db.add(ex);
        }
    }
}