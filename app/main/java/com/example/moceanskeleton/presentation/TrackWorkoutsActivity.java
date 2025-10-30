package com.example.moceanskeleton.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moceanskeleton.R;

public class TrackWorkoutsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button trackNewWorkoutButton, previousWorkoutButton;
        ImageButton foodButton, workoutButton, settingsButton;
        setContentView(R.layout.activity_track_workouts);
        trackNewWorkoutButton = findViewById(R.id.track_new_workout_button);
        previousWorkoutButton = findViewById(R.id.previous_workout_button);

        trackNewWorkoutButton.setOnClickListener(v -> {
            changeToNewWorkout();
        });

        previousWorkoutButton.setOnClickListener(v -> {
            changeToPreviousWorkout();
        });

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
            public void onClick(View view) {
                openWorkoutView();
            }
        });

        settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsView();
            }
        });
    }

    public void changeToNewWorkout() {
        Intent intent = new Intent(this, NewWorkoutsActivity.class);
        startActivity(intent);
    }

    public void changeToPreviousWorkout() {
        Intent intent = new Intent(this, PreviousWorkoutsActivity.class);
        startActivity(intent);
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
