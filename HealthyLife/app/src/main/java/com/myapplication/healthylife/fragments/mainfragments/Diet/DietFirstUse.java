package com.myapplication.healthylife.fragments.mainfragments.Diet;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentDietBinding;
import com.myapplication.healthylife.databinding.FragmentDietFirstUseBinding;

public class DietFirstUse extends Fragment {
    private FragmentDietFirstUseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDietFirstUseBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}
