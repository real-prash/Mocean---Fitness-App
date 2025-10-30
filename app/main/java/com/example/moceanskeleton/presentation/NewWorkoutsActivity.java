package com.example.moceanskeleton.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moceanskeleton.R;
import com.example.moceanskeleton.presentation.adapters.SampleWorkoutsAdapter;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.logic.handlers.ExerciseHandler;
import com.example.moceanskeleton.logic.handlers.WorkoutHandler;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class NewWorkoutsActivity extends AppCompatActivity {

    private EditText etExerciseName, etSets, etReps, workoutName;
    private SampleWorkoutsAdapter adapter;
    private final WorkoutHandler workoutHandler = new WorkoutHandler(true);
    private final ExerciseHandler exerciseHandler = new ExerciseHandler(true);
    private final List<Exercise> ADDED_EXERCISES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);
        Button btnAddExercise, btnSaveWorkout;
        ListView lvAddedExercises;
        ImageButton foodButton, workoutButton, settingsButton;

        etExerciseName = findViewById(R.id.etExerciseName);
        etSets = findViewById(R.id.etSets);
        etReps = findViewById(R.id.etReps);
        workoutName = findViewById(R.id.new_workout_name);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout);
        lvAddedExercises = findViewById(R.id.lvAddedExercises);

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

        adapter = new SampleWorkoutsAdapter(this, ADDED_EXERCISES);
        lvAddedExercises.setAdapter(adapter);

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExerciseToList();
            }
        });

        btnSaveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkout();
            }
        });
    }

    private void addExerciseToList() {
        String exerciseName = Services.getTrimmedString(etExerciseName);
        String setsStr = Services.getTrimmedString(etSets);
        String repsStr = Services.getTrimmedString(etReps);
        String workoutInput = Services.getTrimmedString(workoutName);

        if (workoutHandler.validWorkoutInput(exerciseName, setsStr, repsStr, workoutInput)) {
            if (exerciseName.length() > 50 || setsStr.length() > 3 || repsStr.length() > 3 || workoutInput.length() > 50) {
                Toast.makeText(this, "Values are too large, try again...", Toast.LENGTH_SHORT).show();

                return;
            }
        }
        else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();

            return;
        }

        int sets = Integer.parseInt(setsStr);
        int reps = Integer.parseInt(repsStr);

        Exercise exercise = new Exercise(exerciseName, sets, reps);
        ADDED_EXERCISES.add(exercise);
        adapter.notifyDataSetChanged();

        // Clear input fields
        etExerciseName.getText().clear();
        etSets.getText().clear();
        etReps.getText().clear();
    }

    private void saveWorkout() {
        if (ADDED_EXERCISES.isEmpty()) {
            Toast.makeText(this, "No exercises added", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (Services.getTrimmedString(workoutName).isEmpty()) {
            Toast.makeText(this, "Workout name required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new workout object with the added exercises.
        Workout workout = new Workout(new Date());
        workout.setWorkoutName(Services.getTrimmedString(workoutName));
        ArrayList<Integer> exerciseIDs = new ArrayList<Integer>();
        Exercise newExercise;
        for (Exercise exercise : ADDED_EXERCISES) {
            newExercise = exerciseHandler.addExercise(workout, exercise);
            workout.addExercise(newExercise);
            exerciseIDs.add(newExercise.getExerciseID());
        }

        workoutHandler.addWorkout(workout, exerciseIDs);
        Toast.makeText(this, "Workout saved", Toast.LENGTH_SHORT).show();

        // Clear the list of added exercises.
        ADDED_EXERCISES.clear();
        adapter.notifyDataSetChanged();
        openWorkoutView();
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
