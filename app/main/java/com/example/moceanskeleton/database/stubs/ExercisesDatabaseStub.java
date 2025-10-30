package com.example.moceanskeleton.database.stubs;

import java.util.ArrayList;
import java.util.List;

import com.example.moceanskeleton.logic.handlers.WorkoutHandler;
import  com.example.moceanskeleton.objects.Workout;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.database.interfaces.ExercisesDatabase;


public class ExercisesDatabaseStub implements ExercisesDatabase {

    private List<Exercise> exercises;
    private WorkoutHandler workoutHandler;
    public ExercisesDatabaseStub() {
        this.exercises = new ArrayList<>();
        workoutHandler = new WorkoutHandler(false);
    }

    @Override
    public Exercise getExerciseById(int exerciseId) {
        for(int i = 0; i < exercises.size(); i ++) {
            if(exercises.get(i).getExerciseID() == exerciseId) {
                return exercises.get(i);
            }
        }
        return null;
    }
    

    @Override
    public Exercise insertExercise(Workout workout, Exercise currentExercise) {
        int index;
        index = exercises.indexOf(currentExercise);

        if(index >= 0) {
            // check for duplicates
            return null;
        }

        exercises.add(currentExercise);
        return currentExercise;
    }

    @Override
    public List<Exercise> getExercisesByWorkoutId(int workoutId) {
        return workoutHandler.getWorkouts().get(workoutId-1).getExercises();
    }


}