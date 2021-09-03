package com.myapplication.healthylife.recycleviewadapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.ExerciseListBinding;
import com.myapplication.healthylife.local.DatabaseHelper;
import com.myapplication.healthylife.model.Exercise;

import java.io.Serializable;
import java.util.ArrayList;

public class ExerciseRecViewAdapter extends RecyclerView.Adapter<ExerciseRecViewAdapter.ViewHolder> {
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private Activity activity;
    private DatabaseHelper db;

    public ExerciseRecViewAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ExerciseListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(exercises.get(position));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

       ExerciseListBinding binding;

        public ViewHolder(@NonNull ExerciseListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Exercise exercise)   {

            binding.tvName.setText(exercise.getName());
            binding.level.setText(String.valueOf(exercise.getLevel()));
            binding.duration.setText(String.valueOf(exercise.convertSecToMin()));
            binding.progressBar.setMax(14);
            binding.progressBar.setProgress(exercise.getProgress());
            binding.image.setImageResource(exercise.getImage());
            binding.tvProgressBar.setText(String.valueOf(exercise.getProgress()+"/14"));

            binding.checkBox.setChecked(exercise.isFinished());

            binding.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exercise.setFinished(!exercise.isFinished());
                    if (exercise.isFinished())  {
                        exercise.setProgress(exercise.getProgress()+1);

                    }else   {
                        exercise.setProgress(exercise.getProgress()-1);
                    }
                    notifyItemChanged(getAdapterPosition());
                    db.edit(exercise);
                }
            });

            if(exercise.isRecommended() && exercise.isFirst())   {
                binding.titleLayout.setVisibility(View.VISIBLE);
                binding.title.setText("Recommend");
            }else if (exercise.isOthers() && exercise.isFirst())  {
                binding.titleLayout.setVisibility(View.VISIBLE);
                binding.title.setText("Others");
            }else   {
                binding.titleLayout.setVisibility(View.GONE);
            }

            binding.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", (Serializable) exercise);
                    Navigation.findNavController(activity, R.id.fragmentContainer).navigate(R.id.action_fitnessFragment_to_exerciseDetailFragment, bundle);
                }
            });
        }
    }

}
