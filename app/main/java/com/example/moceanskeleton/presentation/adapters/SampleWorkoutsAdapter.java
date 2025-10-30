package com.example.moceanskeleton.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.moceanskeleton.R;
import com.example.moceanskeleton.objects.Exercise;
import java.util.List;
import java.util.Locale;

public class SampleWorkoutsAdapter extends ArrayAdapter<Exercise> {

    public SampleWorkoutsAdapter(Context context, List<Exercise> exercises) {
        super(context, 0, exercises);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Exercise exercise = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sample_workout_item, parent, false);
        }

        TextView exerciseNameTextView = convertView.findViewById(R.id.exerciseNameTextView);
        TextView setsRepsTextView = convertView.findViewById(R.id.setsRepsTextView);

        if (exercise != null) {
            exerciseNameTextView.setText(exercise.getExerciseName());
            setsRepsTextView.setText(String.format(Locale.getDefault(), "Sets: %d, Reps: %d", exercise.getExerciseSets(), exercise.getExerciseReps()));
        }

        return convertView;
    }

}