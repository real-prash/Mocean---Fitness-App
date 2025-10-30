package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.moceanskeleton.R;
import com.example.moceanskeleton.objects.User;

public class Profile extends AppCompatActivity {

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton foodButton, workoutButton, settingsButton;
        Button updateButton, logoutButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Retrieve the User object
        currentUser = Login.currentUser;

        // Find where text is to be displayed.
        TextView usernameTextView = findViewById(R.id.username_textview);
        TextView heightTextView = findViewById(R.id.height_textview);
        TextView weightTextView = findViewById(R.id.weight_textview);
        TextView ageTextView = findViewById(R.id.age_textview);
        TextView calorieLimitTextView = findViewById(R.id.calorie_limit_textview);

        // Set the text.
        usernameTextView.setText(String.format("%s", String.valueOf(currentUser.getUsername())));
        heightTextView.setText(String.format("Height: \t%s cm", String.valueOf(currentUser.getHeight())));
        weightTextView.setText(String.format("Weight: \t%s kg", String.valueOf(currentUser.getWeight())));
        ageTextView.setText(String.format("Age: \t%s years old", String.valueOf(currentUser.getAge())));
        calorieLimitTextView.setText(String.format("Calorie Cap: \t%s cal", String.valueOf(currentUser.getAllowedCalories())));

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

        updateButton = findViewById(R.id.update_info_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateView();
            }
        });
        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginView();
            }
        });
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

    public void openUpdateView() {
        Intent newView = new Intent(this, UpdateInfo.class);
        startActivity(newView);
    }

    public void openLoginView(){
        Intent newView = new Intent(this, Login.class);
        startActivity(newView);
    }
}
