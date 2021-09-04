package com.myapplication.healthylife.fragments.mainfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentHomeBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController navController;
    private SharedPreferences sharedPreferences;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = AppPrefs.getInstance(getContext());
        db = new DatabaseHelper(getContext());
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putString("user", null).apply();
                db.deleteAllExercises();
                navController.navigate(R.id.action_mainFragment_to_firstUseFragment);
            }
        });
        binding.btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainFragment_to_aboutUs);
            }
        });
        binding.btnAboutDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainFragment_to_aboutDiet);
            }
        });
        binding.btnAboutFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainFragment_to_aboutFitness);

            }
        });
        binding.btnCommonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainFragment_to_commonKnowledge);

            }
        });
    }
}