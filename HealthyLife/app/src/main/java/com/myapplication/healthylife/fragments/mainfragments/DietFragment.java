package com.myapplication.healthylife.fragments.mainfragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentDietBinding;
import com.myapplication.healthylife.databinding.FragmentLaunchBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;

public class DietFragment extends Fragment {

    private FragmentDietBinding binding;
    private NavController navController;
    private DatabaseHelper db;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDietBinding.inflate(getLayoutInflater());
        db = new DatabaseHelper(getContext());
        sharedPreferences = AppPrefs.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);

        binding.RecommendDietButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                navController.navigate(R.id.action_mainFragment_to_dietRecommendFragment);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}