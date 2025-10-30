package com.example.moceanskeleton.logic.handlers;

import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.objects.Workout;
import java.util.ArrayList;
import java.util.List;

public class WorkoutHandler {
    private WorkoutsDatabase workoutsDatabase;

    public WorkoutHandler(boolean forProduction) {
        workoutsDatabase = Services.getWorkoutsDatabase(forProduction);
    }

    public void addWorkout(Workout workout, ArrayList<Integer> exerciseIDs) {
        workoutsDatabase.insertWorkout(workout, exerciseIDs);
    }

    public List<Workout> getWorkouts() {
        return workoutsDatabase.getAllWorkouts();
    }

    public boolean validWorkoutInput(String name, String sets, String reps, String workoutName) {
        return !(name.isEmpty() || sets.isEmpty() || reps.isEmpty() || workoutName.isEmpty());
    }

}