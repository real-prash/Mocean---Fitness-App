package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.logic.handlers.UserLogicHandler;


public class Home extends AppCompatActivity {

    private ProgressBar calorieProgressBar;
    private TextView progressText;
    private TextView progressText2;
    private double caloriesAdded;
    private double currentProgress = 0;
    private double maxCalorieIntake = 0;

    private UserLogicHandler userHandler = new UserLogicHandler(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageButton foodButton, workoutButton, settingsButton;
        Button calorieButton, addFoodButton, resetCals;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_home);
        currentProgress = userHandler.getCalories(Login.currentUser);
        maxCalorieIntake = userHandler.getAllowedCalories(Login.currentUser);

        // Checks to see if any integers have been passed back to the home view.
        // Adds them to the calorie count if there are any, otherwise adds default value of 0.
        Intent intent = getIntent();
        int caloriesToAdd = intent.getIntExtra("key", 0);
        System.out.println(caloriesToAdd);
        caloriesAdded = caloriesToAdd;
        updateProgressBar();

        calorieButton = findViewById(R.id.calorie_add_button);
        calorieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateFoodView();
            }
        });

        // This button will eventually bring up some sort of search menu to search for foods to add, and then the updating will be done once food is selected.
        addFoodButton = findViewById(R.id.food_add_button);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddFoodView();
            }
        });
        resetCals = findViewById(R.id.reset_cals);
        resetCals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHandler.resetCalories(Login.currentUser);
                caloriesAdded = 0;
                updateProgressBar();
            }
        });

        foodButton = findViewById(R.id.food_button);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do Nothing.
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

    @SuppressLint("DefaultLocale") // Suppress warnings given for String.format() usage.
    public void updateProgressBar() {
        userHandler.updateCalories(Login.currentUser, caloriesAdded);
        progressText = (TextView) findViewById(R.id.text_progress);
        progressText2 = (TextView)findViewById(R.id.text_progress_2);
        calorieProgressBar = (ProgressBar)findViewById(R.id.calorie_progress_bar);
        currentProgress = (userHandler.getCalories(Login.currentUser)*100)/maxCalorieIntake;
        calorieProgressBar.setProgress((int)currentProgress);
        String percentConsumed = String.format("%.2f", currentProgress);
        percentConsumed += "%";
        progressText.setText(percentConsumed);
        String literalConsumed = String.format("%.2f/%.2f", currentProgress*(maxCalorieIntake/100), maxCalorieIntake);
        progressText2.setText(literalConsumed);
    }

    public void openAddFoodView() {
        Intent newView = new Intent(this, FoodSearch.class);
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

    public void openCreateFoodView() {
        Intent newView = new Intent(this, CreateFood.class);
        startActivity(newView);
    }
}
