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
import com.myapplication.healthylife.databinding.FragmentLaunchBinding;

import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Diet;
import com.myapplication.healthylife.model.Dish;
import com.myapplication.healthylife.recycleviewadapters.DietRecViewAdapter;
import com.myapplication.healthylife.recycleviewadapters.DishRecViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class DietFragment extends Fragment {

    private FragmentDietBinding binding;
    private NavController navController;
    private DatabaseHelper db;
    private SharedPreferences sharedPreferences;
    private ArrayList<Diet> diets;
    private DishRecViewAdapter dishRecViewAdapterBreakfast;
    private DishRecViewAdapter dishRecViewAdapterLunch;
    private DishRecViewAdapter dishRecViewAdapterDinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        diet = (Diet) getArguments().getSerializable("PickDietData");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDietBinding.inflate(getLayoutInflater());
        db = new DatabaseHelper(getContext());
        sharedPreferences = AppPrefs.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int index = 0;
        for(Diet i : diets){
            if(i.isAssigned()){
                break;
            }
            index++;
        }
        if(index == diets.size()){
            binding.LoveDishNotification.setText("No dishes found! " +
                    "Please pick a diet by clicking recommend button and doing further actions.");
        }
        else{
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            ArrayList<Dish> breakfast = new ArrayList<>();
            breakfast.add(diets.get(index).getBreakfast().get(day % diets.get(index).getBreakfast().size()));

            ArrayList<Dish> lunch = new ArrayList<>();
            lunch.add(diets.get(index).getLunch().get(day % diets.get(index).getLunch().size()));

            ArrayList<Dish> dinner = new ArrayList<>();
            dinner.add(diets.get(index).getDinner().get(day % diets.get(index).getDinner().size()));

            dishRecViewAdapterBreakfast = new DishRecViewAdapter(getActivity(), getContext());
            dishRecViewAdapterBreakfast.SetDishes(breakfast);
            binding.rvDishDetailTodayBreakfast.setAdapter(dishRecViewAdapterBreakfast);
            binding.rvDishDetailTodayBreakfast.setLayoutManager(new LinearLayoutManager(getContext()));

            dishRecViewAdapterLunch = new DishRecViewAdapter(getActivity(), getContext());
            dishRecViewAdapterLunch.SetDishes(lunch);
            binding.rvDishDetailTodayLunch.setAdapter(dishRecViewAdapterBreakfast);
            binding.rvDishDetailTodayLunch.setLayoutManager(new LinearLayoutManager(getContext()));

            dishRecViewAdapterBreakfast = new DishRecViewAdapter(getActivity(), getContext());
            dishRecViewAdapterBreakfast.SetDishes(dinner);
            binding.rvDishDetailTodayBreakfast.setAdapter(dishRecViewAdapterBreakfast);
            binding.rvDishDetailTodayBreakfast.setLayoutManager(new LinearLayoutManager(getContext()));
        }
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