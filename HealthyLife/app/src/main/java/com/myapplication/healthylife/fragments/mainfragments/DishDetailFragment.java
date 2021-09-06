package com.myapplication.healthylife.fragments.mainfragments;

import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.FragmentDishDetailBinding;
import com.myapplication.healthylife.databinding.FragmentDietRecommendBinding;
import com.myapplication.healthylife.local.AppPrefs;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Dish;

import java.io.IOException;

public class DishDetailFragment extends Fragment implements TextureView.SurfaceTextureListener{
    private FragmentDishDetailBinding binding;
    private NavController navController;
    private DatabaseHelper db;
    private SharedPreferences sharedPreferences;
    private Dish dish;
    private MediaPlayer mediaPlayer;
    private AssetFileDescriptor assetFileDescriptor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dish = (Dish) getArguments().getSerializable("DishData");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDishDetailBinding.inflate(getLayoutInflater());
        db = new DatabaseHelper(getContext());
        sharedPreferences = AppPrefs.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.DishDetailVideo.setSurfaceTextureListener(this);

        mediaPlayer = new MediaPlayer();
       // assetFileDescriptor = getResources().openRawResourceFd(dish.getVideo());

     //   binding.DishDetailImage.setImageResource(dish.getImage());
        binding.DishDetailName.setText(dish.getName());
        binding.DishDetailDescriptionContent.setText(dish.getDescription());
        binding.DishDetailTutorialContent.setText(dish.getTutorial());
        binding.DishDetailNoteContent.setText(dish.getNote());
        binding.DishDetailIngredientsContent.setText(dish.getIngredients());
    }

    @Override
    public void onStart() {
        super.onStart();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
        Surface surface = new Surface(surfaceTexture);
        /*
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

         */
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
}
