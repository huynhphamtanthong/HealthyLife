package com.myapplication.healthylife.fragments.mainfragments.Diet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.ActivityDietEvaluationBinding;
import com.myapplication.healthylife.databinding.FragmentDietBinding;

public class DietEvaluation extends Fragment {
    private ActivityDietEvaluationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActivityDietEvaluationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}