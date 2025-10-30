package com.example.moceanskeleton.logic.handlers;

import com.example.moceanskeleton.database.interfaces.ExercisesDatabase;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;
import java.util.ArrayList;
import java.util.List;

public class ExerciseHandler {
    private ExercisesDatabase exercisesDatabase;

    public ExerciseHandler(boolean forProduction) {
        exercisesDatabase = Services.getExercisesDatabase(forProduction);
    }

    public Exercise addExercise(Workout workout, Exercise exercise) {
        return exercisesDatabase.insertExercise(workout, exercise);
    }

    public List<Exercise> getExercises(int workoutID) {
        return exercisesDatabase.getExercisesByWorkoutId(workoutID);
    }
}