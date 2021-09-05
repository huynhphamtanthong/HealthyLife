package com.myapplication.healthylife.fragments.mainfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentHomeBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Diet;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.model.User;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController navController;
    private SharedPreferences sharedPreferences;
    private DatabaseHelper db;
    ArrayList<Exercise> list = new ArrayList<>();
    ArrayList<Diet> listDiet = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = AppPrefs.getInstance(getContext());
        db = new DatabaseHelper(getContext());

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = sharedPreferences.getString("user", null);
        User userObject = new Gson().fromJson(data, User.class);
        binding.userName.setText(userObject.getName());
        int CaloExe, CaloDiet, CaloTotal;
        CaloExe=countCaloExercise();
        CaloDiet=countCaloDiet();
        CaloTotal=countCaloDiet()-countCaloExercise();
        binding.CaloExercise.setText(CaloExe+" Calo");
        binding.CaloDiet.setText(CaloDiet+" Calo");
        binding.CaloTotal.setText(CaloTotal+" Calo");

    }



    public int countCaloExercise()
    {
        int totalCaloExercise = 0;
        list = db.getExerciseList();
        for (int i=0; i<list.size(); i++)
        {
            if(list.get(i).isFinished())
                totalCaloExercise+=list.get(i).getcaloSet();
        }
        return totalCaloExercise;
    }

    public int countCaloDiet()
    {
        int totalCaloDiet = 0;
        /*
        listDiet=db.getDietList();
        for (int i=0; i<listDiet.size(); i++)
        {
            if(listDiet.get(i).isAssigned())
                totalCaloDiet+=list.get(i).getcaloSet();
        }*/
        return totalCaloDiet;
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