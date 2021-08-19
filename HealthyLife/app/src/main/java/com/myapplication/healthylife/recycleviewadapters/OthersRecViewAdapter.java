package com.myapplication.healthylife.recycleviewadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.healthylife.R;
import com.myapplication.healthylife.databinding.ExerciseListBinding;
import com.myapplication.healthylife.model.Exercise;

import java.util.ArrayList;

public class OthersRecViewAdapter extends RecyclerView.Adapter<OthersRecViewAdapter.ViewHolder> {

    private ArrayList<Exercise> exercises = new ArrayList<>();

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

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ExerciseListBinding binding;

        public ViewHolder(@NonNull ExerciseListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Exercise exercise)   {
            binding.tvName.setText(exercise.getName());
            binding.level.setText(String.valueOf(exercise.getLevel()));
            binding.duration.setText(String.valueOf(exercise.getDuration()));
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
                }
            });

        }
    }

}