package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.logic.handlers.FoodHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateFood extends AppCompatActivity {

    private final FoodHandler HANDLER = new FoodHandler(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton foodButton, workoutButton, settingsButton;
        Button returnButton, submitButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);

        // Food group drop down menu.
        Spinner foodGroupSpinner = findViewById(R.id.food_groups);

        // Create a list to display in the Spinner.
        ArrayList<String> foodGroupsList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_groups_array)));

        // Create an adapter using custom layout for spinner dropdown items.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list, foodGroupsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the Spinner.
        foodGroupSpinner.setAdapter(adapter);

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

        returnButton = findViewById(R.id.add_food_return_button);
        returnButton.setOnClickListener(v -> {
            openFoodView();
        });

        submitButton = findViewById(R.id.add_food_submit_button);
        submitButton.setOnClickListener(v -> {
            String foodName = Services.getTrimmedString(findViewById(R.id.add_food_name));
            String foodQuantityStr = Services.getTrimmedString(findViewById(R.id.add_food_quantity));
            String foodCaloriesStr = Services.getTrimmedString(findViewById(R.id.add_food_calories));
            String foodCarbsStr = Services.getTrimmedString(findViewById(R.id.add_food_carbs));
            String foodFatsStr = Services.getTrimmedString(findViewById(R.id.add_food_fats));
            String foodProteinStr = Services.getTrimmedString(findViewById(R.id.add_food_protein));

            String foodGroup = foodGroupSpinner.getSelectedItem().toString();

            if(HANDLER.validFoodInput(foodName, foodQuantityStr, foodCaloriesStr, foodCarbsStr, foodFatsStr, foodProteinStr)) {
                int foodQuantity, foodCarbs, foodFats, foodCalories, foodProtein;
                if(Services.validIntSize(foodQuantityStr)) {
                    foodQuantity = Integer.parseInt(foodQuantityStr);
                }else{
                    Toast.makeText(this, "Quantity input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Services.validIntSize(foodCaloriesStr)) {
                    foodCalories = Integer.parseInt(foodCaloriesStr);
                }else{
                    Toast.makeText(this, "Calorie input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Services.validIntSize(foodCarbsStr)) {
                    foodCarbs = Integer.parseInt(foodCarbsStr);
                }else{
                    Toast.makeText(this, "Carbs input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Services.validIntSize(foodFatsStr)) {
                    foodFats = Integer.parseInt(foodFatsStr);
                }else{
                    Toast.makeText(this, "Fats input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Services.validIntSize(foodProteinStr)) {
                    foodProtein = Integer.parseInt(foodProteinStr);
                }else{
                    Toast.makeText(this, "Protein input is too large. Max value:" + Integer.MAX_VALUE, Toast.LENGTH_SHORT).show();
                    return;
                }
                HANDLER.addFood(foodName, foodGroup, foodCalories, foodQuantity, foodCarbs, foodFats, foodProtein);
                openFoodView();
            }else{
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
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

}
