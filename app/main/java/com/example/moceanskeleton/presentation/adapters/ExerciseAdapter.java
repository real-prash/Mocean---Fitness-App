package com.example.moceanskeleton.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.objects.Exercise;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private final List<Exercise> EXERCISE_LIST;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ExerciseName, ExerciseID, exerciseSets, exerciseReps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ExerciseName = itemView.findViewById(R.id.exerciseNameTextView);
            ExerciseID = itemView.findViewById(R.id.exerciseID);
            exerciseSets = itemView.findViewById(R.id.workoutSetsValue);
            exerciseReps = itemView.findViewById(R.id.workoutRepsValue);
        }
    }

    public ExerciseAdapter(List<Exercise> myExercises) {
        EXERCISE_LIST = myExercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        ViewHolder myView = new ViewHolder(v);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise currentExerciseItem = EXERCISE_LIST.get(position);
        holder.ExerciseName.setText(currentExerciseItem.getExerciseName());
        holder.ExerciseID.setText(String.valueOf(currentExerciseItem.getExerciseID()));
        holder.exerciseReps.setText(String.valueOf(currentExerciseItem.getExerciseReps()));
        holder.exerciseSets.setText(String.valueOf(currentExerciseItem.getExerciseSets()));
    }

    @Override
    public int getItemCount() {
        return EXERCISE_LIST.size();
    }
}