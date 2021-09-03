package com.myapplication.healthylife.fragments.mainfragments;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentTimerBinding;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Exercise;

import java.io.IOException;
import java.util.ArrayList;

import kotlin.jvm.Synchronized;

public class TimerFragment extends Fragment implements TextureView.SurfaceTextureListener{
    FragmentTimerBinding binding;
    DatabaseHelper db;
    private MediaPlayer mediaPlayer;
    private AssetFileDescriptor assetFileDescriptor;
    private ArrayList<Exercise> list;

    long currentDuration;
    int numOfSet;
    long breakEx;
    long breakSet;
    int setCount = 1;

    CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        binding = FragmentTimerBinding.inflate(getLayoutInflater());
        list = db.getRecommendedList();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.video.setSurfaceTextureListener(this);

        for (Exercise ex: list
             ) {
            mediaPlayer = new MediaPlayer();
            assetFileDescriptor = getResources().openRawResourceFd(ex.getVideo());

            currentDuration = ex.getDurationSet()*1000;
            numOfSet = ex.getnumSet();
            breakEx = ex.getbreakEx();
            breakSet = ex.getbreakSet();

            for (int i = 0; i < numOfSet; i++)  {
                countDown(currentDuration);

            }

        }


    }

    synchronized private void countDown(long time)    {
        timer= new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                updateTime(l);
            }

            @Override
            public void onFinish() {
//                binding.tvStatus.setText("Break");
//                CountDownTimer breakTimer = new CountDownTimer(breakEx, 1000) {
//                    @Override
//                    public void onTick(long l) {
//                        updateTime(l);
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//                }.start();
            }
        }.start();
    }

    private void updateTime(long time)   {
        int min = (int)time/60000;
        int sec = (int)time%60000/1000;

        String text;
        text = ""+min;
        text += " : ";
        if (sec < 10)   {
            text += "0";
        }
        text += sec;
        binding.tvTime.setText(text);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
        Surface surface = new Surface(surfaceTexture);
        try {
            mediaPlayer.setDataSource(assetFileDescriptor);
            mediaPlayer.setSurface(surface);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mediaPlayer != null)    {
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null)    {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void runCallback(Runnable callback)
    {
        countDown(currentDuration);
        callback.run();
    }
}