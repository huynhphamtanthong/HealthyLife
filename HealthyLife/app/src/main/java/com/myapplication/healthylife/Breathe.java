package com.myapplication.healthylife;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.myapplication.healthylife.databinding.FragmentBreatheBinding;
import com.myapplication.healthylife.local.AppPrefs;


public class Breathe extends Fragment {
    FragmentBreatheBinding binding;
    NavController navController;

    CountDownTimer timer;
    boolean isRunning = false;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBreatheBinding.inflate(getLayoutInflater());
        sharedPreferences = AppPrefs.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateTime(60000);
        binding.video.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.breath));
        MediaController ctrl = new MediaController(getContext());
        ctrl.setVisibility(View.GONE);
        binding.video.setMediaController(ctrl);
        binding.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    countDown(60000);
                    binding.video.start();
                    isRunning = true;
                    binding.btn.setText("Cancel");
                } else {
                    timer.cancel();
                    binding.video.stopPlayback();
                    isRunning = false;
                    binding.btn.setText("Start");
                }
            }
        });
    }

    synchronized private void countDown(long time) {
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long time) {
                updateTime(time);
            }

            @Override
            public void onFinish() {
                binding.video.stopPlayback();
            }
        }.start();
                // Dialog dialog = new Dialog(getContext());
                // dialog.setContentView(R.layout.custom_dialog_congratz);
                // dialog.setCanceledOnTouchOutside(true);
                // Button btnOk = dialog.findViewById(R.id.btnOk);
                // btnOk.setOnClickListener(new View.OnClickListener() {
                //     @Override
                //     public void onClick(View view) {
                //         navController.navigateUp();
                //         dialog.dismiss();
                //     }
                // });
                // dialog.show();
                // Window window = dialog.getWindow();
                // window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    };

    private void updateTime(long time) {
            int min = (int) time / 60000;
            int sec = (int) time % 60000 / 1000;

            String text;
            text = "" + min;
            text += " : ";
            if (sec < 10) {
                text += "0";
            }
            text += sec;
            binding.tvTime.setText(text);
            if (time ==59000)
                Toast.makeText( getActivity(),"Be still and bring your attention to your breath.", Toast.LENGTH_SHORT ).show();
            if (time==56000)
                Toast.makeText( getActivity(),"Now inhale...", Toast.LENGTH_SHORT ).show();
            if (time==55000)
                Toast.makeText( getActivity(),"and exhale.", Toast.LENGTH_SHORT ).show();

    };

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
    }
}