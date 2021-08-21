package com.myapplication.healthylife.fragments.firstusefragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentFirstUseBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstUseFragment extends Fragment {
    private FragmentFirstUseBinding binding;
    private NavController navController;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SharedPreferences sharedPreferences;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = AppPrefs.getInstance(getContext());
        db = new DatabaseHelper(getContext());
        Date date = new Date();
        String now = sdf.format(date);
        binding = FragmentFirstUseBinding.inflate(getLayoutInflater());
        sharedPreferences.edit().putString("date", now).apply();
        initData();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.etName.getText().toString().equals("") && validateString(binding.etName.getText().toString())) {
                    if (!binding.etHeight.getText().toString().equals("")
                            && validateFloat(binding.etHeight.getText().toString())
                            && (Float.valueOf(binding.etHeight.getText().toString()) >= 10  && Float.valueOf(binding.etHeight.getText().toString()) <= 300))    {
                        if (!binding.etWeight.getText().toString().equals("")
                                && validateFloat(binding.etWeight.getText().toString())
                                && (Float.valueOf(binding.etWeight.getText().toString()) >= 1 && Float.valueOf(binding.etWeight.getText().toString()) <= 600))    {
                            User user = new User(binding.etName.getText().toString(),
                                    Float.valueOf(binding.etHeight.getText().toString()),
                                    Float.valueOf(binding.etWeight.getText().toString()));

                            double bmi = user.getWeight()/Math.pow(user.getHeight()/100, 2);
                            Log.d("DATA", String.valueOf(bmi));
                            user.setBmi(bmi);

                            sharedPreferences.edit().putBoolean("isLogout", false).apply();

                            sharedPreferences.edit().putString("user", new Gson().toJson(user)).apply();

                            saveListOfExercisesForNewUser(exercises, bmi);

                            navController.navigate(R.id.action_firstUseFragment_to_mainFragment);
                        }else   {
                            binding.etWeight.requestFocus();
                            openKeyboard(binding.etWeight);
                            Toast.makeText(getActivity(), "Invalid Weight", Toast.LENGTH_SHORT).show();
                        }
                    }else   {
                        binding.etHeight.requestFocus();
                        openKeyboard(binding.etHeight);
                        Toast.makeText(getActivity(), "Invalid Height", Toast.LENGTH_SHORT).show();
                    }
                }else   {
                    binding.etName.requestFocus();
                    openKeyboard(binding.etName);
                    Toast.makeText(getActivity(), "Invalid Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateString(String str)   {
        Pattern patternString = Pattern.compile("^[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" + "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" + "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$");
        Matcher matcher = patternString.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private Boolean validateFloat(String num) {
        Pattern patternFloat = Pattern.compile("([0-9]*[.])?[0-9]+");
        Matcher matcher = patternFloat.matcher(num);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private void initData() {
        exercises.add(new Exercise(-1,"A", "BBB", 1, R.drawable.test, new int[]{1, 2, 3}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"B", "BBB", 1, R.drawable.test, new int[]{ 5}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"C", "BBB", 1, R.drawable.test, new int[]{1, 5}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"D", "BBB", 1, R.drawable.test, new int[]{2}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"E", "BBB", 1, R.drawable.test, new int[]{1}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"F", "BBB", 1, R.drawable.test, new int[]{4}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"G", "BBB", 1, R.drawable.test, new int[]{1, 2, 4, 5}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"H", "BBB", 1, R.drawable.test, new int[]{1}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"I", "BBB", 1, R.drawable.test, new int[]{4, 2}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"J", "BBB", 1, R.drawable.test, new int[]{4}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"K", "BBB", 1, R.drawable.test, new int[]{ 2, 5}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"L", "BBB", 1, R.drawable.test, new int[]{1, 2, 4}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"M", "BBB", 1, R.drawable.test, new int[]{1}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"N", "BBB", 1, R.drawable.test, new int[]{2}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"O", "BBB", 1, R.drawable.test, new int[]{1}, R.raw.test, "description", "tutorial"));
        exercises.add(new Exercise(-1,"P", "BBB", 1, R.drawable.test, new int[]{2, 5}, R.raw.test, "description", "tutorial"));

    }

    private void saveListOfExercisesForNewUser(ArrayList<Exercise> exercise, double bmi)    {
        boolean startRecommended = false;
        boolean startOthers = false;
        ArrayList<Exercise> result = new ArrayList<>();
        int type;
        if (bmi >= 30) {
            type = 5;
        }else if(bmi >= 25 && bmi <= 29.9) {
            type = 4;
        }else if(bmi >= 23 && bmi <= 24.9)  {
            type = 3;
        }else if(bmi >= 18.5 && bmi <= 22.9)    {
            type = 2;
        }else   {
            type = 1;
        }
        Log.d("DATA", String.valueOf(type));

        //add recommended ex
        for (Exercise ex: exercise)    {
            for (int i: ex.getTypes())  {
                if (i == type && !startRecommended)  {
                    ex.setFirst(true);
                    ex.setRecommended(true);
                    Log.d("REC", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                    result.add(ex);
                    startRecommended = true;
                    break;
                }else if(i == type && startRecommended) {
                    ex.setRecommended(true);
                    Log.d("REC", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                    result.add(ex);
                    break;
                }
            }
        }

        boolean isOthers;
        for (Exercise ex: exercise)    {
            isOthers = true;
            for (int i: ex.getTypes()) {
                if(i == type)    {
                    isOthers = false;
                }
            }
            if (isOthers && !startOthers) {
                ex.setFirst(true);
                ex.setOthers(true);
                Log.d("OTH", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                result.add(ex);
                startOthers = true;
            } else if (isOthers && startOthers) {
                ex.setOthers(true);
                Log.d("OTH", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
                result.add(ex);
            }
        }

        for (Exercise ex: result
             ) {
            Log.d("DATA", ex.getName()+" "+ex.isRecommended()+" "+ex.isOthers()+" "+ex.isFirst());
            db.add(ex);
        }
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

    public void openKeyboard(View view)  {
        InputMethodManager inputMethodManager =  (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(),     InputMethodManager.SHOW_FORCED, 0);
    }
}