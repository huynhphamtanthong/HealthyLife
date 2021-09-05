package com.myapplication.healthylife.fragments.mainfragments;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentDietBinding;
import com.myapplication.healthylife.databinding.FragmentDietRecommendBinding;
import com.myapplication.healthylife.databinding.FragmentLaunchBinding;

import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Diet;
import com.myapplication.healthylife.model.Dish;
import com.myapplication.healthylife.recycleviewadapters.DietRecViewAdapter;

import java.util.ArrayList;

public class DietRecommendFragment extends Fragment {
    private FragmentDietRecommendBinding binding;
    private NavController navController;
    private DatabaseHelper db;
    private ArrayList<Diet> dietList;
    private ArrayList<Dish> dish;
    private SharedPreferences sharedPreferences;
    DietRecViewAdapter dietRecAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDietRecommendBinding.inflate(getLayoutInflater());
        db = new DatabaseHelper(getContext());
        sharedPreferences = AppPrefs.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDietRecycleView();
    }

    private void initDietRecycleView() {
        dietList = db.getDietList();
        dish = db.getDishList();
        int m = 0;
        for(Diet i : dietList){
            if(i.isRecommended()){
                if(i.isCarbAllowed()) {
                    for (Dish k : dish) {
                        if (k.isCarb()) {
                            if (k.isBreakfast()) {
                                dietList.get(m).insertBreakfast(k);
                            } else if (k.isLunch()) {
                                dietList.get(m).insertLunch(k);
                            } else if (k.isDinner()) {
                                dietList.get(m).insertDinner(k);
                            }
                        }
                    }
                }
                else if(i.isFatAllowed()) {
                    for (Dish k : dish) {
                        if (k.isFat()) {
                            if (k.isBreakfast()) {
                                dietList.get(m).insertBreakfast(k);
                            } else if (k.isLunch()) {
                                dietList.get(m).insertLunch(k);
                            } else if (k.isDinner()) {
                                dietList.get(m).insertDinner(k);
                            }
                        }
                    }
                }
                else if(i.isVegan()) {
                    for(Dish k : dish) {
                        if(k.isVegan()){
                            if (k.isBreakfast()) {
                                dietList.get(m).insertBreakfast(k);
                            }
                            else if (k.isLunch()){
                                dietList.get(m).insertLunch(k);
                            }
                            else if (k.isDinner()){
                                dietList.get(m).insertDinner(k);
                            }
                        }
                    }
                }
            }
            m++;
        }
        dietRecAdapter = new DietRecViewAdapter(getActivity(), getContext());
        dietRecAdapter.setDiets(dietList);
        binding.RVDietRecommend.setAdapter(dietRecAdapter);
        binding.RVDietRecommend.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
