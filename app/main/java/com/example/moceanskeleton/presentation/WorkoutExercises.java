package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.presentation.adapters.ExerciseAdapter;
import com.example.moceanskeleton.logic.handlers.ExerciseHandler;

public class WorkoutExercises extends AppCompatActivity {

    private final ExerciseHandler exerciseHandler = new ExerciseHandler(true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_exercises);
        ImageButton foodButton, workoutButton, settingsButton;

        foodButton = findViewById(R.id.food_button);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFoodView();
            }
        });

        workoutButton = findViewById(R.id.weight_button);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openWorkoutView();
            }
        });

        settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsView();
            }
        });

        Intent intent = getIntent();
        int workoutID = intent.getIntExtra("key", 0);

        RecyclerView currentWorkoutView = findViewById(R.id.currentWorkout);
        currentWorkoutView.setHasFixedSize(true);
        RecyclerView.LayoutManager exerciseLayoutManager = new LinearLayoutManager(this);
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exerciseHandler.getExercises(workoutID));
        currentWorkoutView.setLayoutManager(exerciseLayoutManager);
        currentWorkoutView.setAdapter(exerciseAdapter);
    }
    public void openFoodView() {
        Intent newView = new Intent(this, Home.class);
        startActivity(newView);
    }

    public void openWorkoutView() {
        Intent newView = new Intent(this, TrackWorkoutsActivity.class);
        startActivity(newView);
    }

    public void openSettingsView() {
        Intent newView = new Intent(this, Profile.class);
        startActivity(newView);
    }
}