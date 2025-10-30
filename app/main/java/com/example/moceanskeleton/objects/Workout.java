package com.example.moceanskeleton.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Workout {

    private static int nextId = 1;
    private List<Exercise> EXERCISES = null;
    private Date DATE = null;

    private static int counter = -1;

    private int workoutId;
    private String workoutName;

    public Workout(Date date) {
        this.workoutId = nextId++;
        this.DATE = date;
        this.EXERCISES = new ArrayList<>();
        this.workoutName = date.toString();
    }
    public Workout(final int workoutId, String workoutName) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.EXERCISES = new ArrayList<>();
        this.DATE = new Date();
    }

    public Workout(String workoutName) {
        this.workoutId = setWorkoutId();
        this.workoutName = workoutName;
        this.EXERCISES = new ArrayList<>();
        this.DATE = new Date();
    }

    private int setWorkoutId() {
        counter ++;
        return counter;
    }

    public int getWorkoutID() {
        return workoutId;
    }

    public String getName() {
        return workoutName;
    }

    public void addExercise(Exercise exercise) {
            EXERCISES.add(exercise);
        }
    public List<Exercise> getExercises() {

        return EXERCISES;
    }
    public Date getDate() {

        return this.DATE;
    }

    public void setWorkoutName(String name) {
        this.workoutName = name;
    }
}