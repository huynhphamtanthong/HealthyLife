package com.myapplication.healthylife.fragments.mainfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentFitnessBinding;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.recycleviewadapters.ExerciseRecViewAdapter;

import java.util.ArrayList;

public class FitnessFragment extends Fragment {
    private FragmentFitnessBinding binding;
    private ExerciseRecViewAdapter exerciseRecViewAdapter;
    private DatabaseHelper db;
    private ArrayList<Exercise> list = new ArrayList<>();
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFitnessBinding.inflate(getLayoutInflater());
        db = new DatabaseHelper(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleViews();
    }

    private void initRecycleViews() {
        list = db.getList();
        exerciseRecViewAdapter = new ExerciseRecViewAdapter(getActivity());
        exerciseRecViewAdapter.setExercises(list);
        binding.recommendedRecView.setAdapter(exerciseRecViewAdapter);
        binding.recommendedRecView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
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