package com.example.moceanskeleton.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.presentation.adapters.WorkoutAdapter;
import com.example.moceanskeleton.logic.handlers.WorkoutHandler;

public class PreviousWorkoutsActivity extends AppCompatActivity {

    private final WorkoutHandler workoutHandler = new WorkoutHandler(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_workouts);
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

        RecyclerView previousWorkoutsView = findViewById(R.id.lvPreviousWorkouts);
        previousWorkoutsView.setHasFixedSize(true);
        RecyclerView.LayoutManager workoutLayoutManager = new LinearLayoutManager(this);
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(workoutHandler.getWorkouts());
        previousWorkoutsView.setLayoutManager(workoutLayoutManager);
        previousWorkoutsView.setAdapter(workoutAdapter);

        workoutAdapter.setOnItemClickListener(new WorkoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openWorkoutExercisesView(position);
            }
        });
    }

    public void openWorkoutExercisesView(int position) {
        Intent newView = new Intent(this, WorkoutExercises.class);
        newView.putExtra("key", position);
        startActivity(newView);
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