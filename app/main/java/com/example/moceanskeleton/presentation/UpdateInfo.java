package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.objects.User;

public class UpdateInfo extends AppCompatActivity {

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton foodButton, workoutButton, settingsButton;
        Button returnButton, submitButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        // Retrieve the User object
        currentUser = Login.currentUser;

        // Set the text fields to the current user's info.
        EditText heightEditText = findViewById(R.id.height_edittext);
        EditText weightEditText = findViewById(R.id.weight_edittext);
        EditText ageEditText = findViewById(R.id.age_edittext);

        int heightValue = currentUser.getHeight();
        double weightValue = currentUser.getWeight();
        int ageValue = currentUser.getAge();

        heightEditText.setText(String.valueOf(heightValue));
        weightEditText.setText(String.valueOf(weightValue));
        ageEditText.setText(String.valueOf(ageValue));

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

        returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> {
            openSettingsView();
        });

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            updateInfo();
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

    public void updateInfo() {

        // Update user info to reflect the current inputs.
        EditText heightEditText = findViewById(R.id.height_edittext);
        EditText weightEditText = findViewById(R.id.weight_edittext);
        EditText ageEditText = findViewById(R.id.age_edittext);

        String heightText = heightEditText.getText().toString();
        String weightText = weightEditText.getText().toString();
        String ageText = ageEditText.getText().toString();
        int heightValue, ageValue;
        double weightValue;

        if (!(heightText.isEmpty() || weightText.isEmpty() || ageText.isEmpty())) {
            if (Services.validDoubleSize(weightText)) {
                weightValue = Double.parseDouble(weightText);
            }else{
                Toast.makeText(this, "Quantity input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                return;
            }
            if (Services.validIntSize(heightText)) {
                heightValue = Integer.parseInt(heightText);
            }else{
                Toast.makeText(this, "Quantity input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                return;
            }
            if (Services.validIntSize(ageText)) {
                ageValue = Integer.parseInt(ageText);
            }else{
                Toast.makeText(this, "Quantity input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                return;
            }
        }else{
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        currentUser.setHeight(heightValue);
        currentUser.setWeight(weightValue);
        currentUser.setAge(ageValue);

        // Return to the profile page.
        openSettingsView();
    }
}