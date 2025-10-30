package com.example.moceanskeleton.database.interfaces;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;

import java.util.*;

public interface ExercisesDatabase {

    Exercise getExerciseById(int exerciseId);

    Exercise insertExercise(Workout workout, Exercise exercise);

    List<Exercise> getExercisesByWorkoutId(int workoutId);
}