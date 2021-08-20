package com.myapplication.healthylife.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentLaunchBinding;
import com.myapplication.healthylife.local.AppPrefs;

public class LaunchFragment extends Fragment {

    private FragmentLaunchBinding binding;
    private Boolean newLogin = true, isLogout = false;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLaunchBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CountDownTimer countDownTimer = new CountDownTimer(1000, 500) {
            @Override
            public void onTick(long l) {
                isLogout = AppPrefs.getInstance(getContext()).getBoolean("isLogout", false);
                if(!isLogout)    {
                    newLogin = false;
                }
            }
            @Override
            public void onFinish() {
                if (newLogin)   {
                    navController.navigate(R.id.action_launchFragment_to_firstUseFragment);
                }else   {
                    navController.navigate(R.id.action_launchFragment_to_mainFragment);
                }
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}