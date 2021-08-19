package com.myapplication.healthylife.fragments.mainfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentFitnessBinding;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.recycleviewadapters.OthersRecViewAdapter;
import com.myapplication.healthylife.recycleviewadapters.RecommendedRecViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FitnessFragment extends Fragment {
    private FragmentFitnessBinding binding;
    private ArrayList<Exercise> recommended = new ArrayList<>();
    private ArrayList<Exercise> others = new ArrayList<>();
    private RecommendedRecViewAdapter recommendedRecViewAdapter;
    private OthersRecViewAdapter othersRecViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFitnessBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initRecycleViews();
    }

    private void initRecycleViews() {
        recommendedRecViewAdapter = new RecommendedRecViewAdapter();
        recommendedRecViewAdapter.setExercises(recommended);
        binding.recommendedRecView.setAdapter(recommendedRecViewAdapter);
        binding.recommendedRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        othersRecViewAdapter = new OthersRecViewAdapter();
        othersRecViewAdapter.setExercises(others);
        binding.othersRecView.setAdapter(othersRecViewAdapter);
        binding.othersRecView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        recommended.add(new Exercise("AAA", "BBB", 1, R.mipmap.ic_launcher));
        recommended.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
        recommended.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
        recommended.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
        recommended.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));

        others.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
        others.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
        others.add(new Exercise("AAA", "BBB", 1, R.mipmap.ic_launcher));
        others.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
        others.add(new Exercise("AAA", "BBB", 1,  R.mipmap.ic_launcher));
    }
}