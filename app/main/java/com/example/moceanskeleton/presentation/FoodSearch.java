package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import com.example.moceanskeleton.R;
import com.example.moceanskeleton.presentation.adapters.RecyclerViewAdapter;
import com.example.moceanskeleton.logic.handlers.FoodHandler;

public class FoodSearch extends AppCompatActivity {
    private final FoodHandler HANDLER = new FoodHandler(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton foodButton, workoutButton, settingsButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);
        buildRecyclerView();

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
    }

    // Builds our recycler view of all food items.
    public void buildRecyclerView() {
        RecyclerView foodRecyclerView = findViewById(R.id.recycler_view);
        foodRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager foodLayoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapter foodRecyclerViewAdapter = new RecyclerViewAdapter(HANDLER.getFoodList());
        foodRecyclerView.setLayoutManager(foodLayoutManager);
        foodRecyclerView.setAdapter(foodRecyclerViewAdapter);

        // Set up the search functionality.
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodRecyclerViewAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // What happens when any food's plus icon is clicked.
        foodRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int foodID) {
                updateCalorieView(HANDLER.getCaloriesByFoodID(foodID));
            }
        });
    }

    public void updateCalorieView(int sendFoodCalories){
        Intent newView = new Intent(this, Home.class);
        newView.putExtra("key", sendFoodCalories);
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