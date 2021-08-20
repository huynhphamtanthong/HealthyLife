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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentFirstUseBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstUseFragment extends Fragment {
    private FragmentFirstUseBinding binding;
    private NavController navController;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Date date = new Date();
        String now = sdf.format(date);
        binding = FragmentFirstUseBinding.inflate(getLayoutInflater());
        AppPrefs.getInstance(getContext()).edit().putString("date", now).apply();
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
                            user.setBmi(user.getWeight()/Math.pow(user.getHeight()/100, 2));
                            AppPrefs.getInstance(getContext()).edit().putString("user", new Gson().toJson(user)).apply();
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