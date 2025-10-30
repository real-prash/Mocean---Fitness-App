package com.example.moceanskeleton.objects;
import java.util.*;

public class Exercise {

    private int exerciseID;
    private String exerciseName;
    private int sets;
    private int reps;
    private Date date;
    
    private List <Workout> workoutList;
    

    public  Exercise(final int exerciseID) {
        this.exerciseID = exerciseID;
        this.sets = 0;
        this.reps = 0;
        this.date = new Date();
        this.exerciseName = null;
        this.workoutList = new ArrayList<>();
    }


    /*
     *  Exercise constructor
     *      - used when DB creates Exercises with data straight from the database.
     *      - Allows for full control over Exercise creation (set the id and the date)
     */
    public Exercise(int exerciseID,String exerciseName, int sets, int reps, Date date) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.exerciseID = exerciseID;
        this.date = date; // Current date

    }

    /*
     * Exercise constructor
     *      - used by the app when a new Exercise is being created
     *      - Doesn't set an ID, and sets the date to current date
     */
    public Exercise(String ExerciseTitle, int sets, int reps) {

        this.exerciseName = ExerciseTitle;
        this.reps = reps;
        this.sets = sets;
        this.workoutList = new ArrayList<>();
        this.date = new Date(); // Current date
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getExerciseSets() {
        return sets;
    }

    public int getExerciseReps() {
        return reps;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public Date getExerciseDate() {
        return date;
    }

    public void setExerciseName(String ExerciseTitle) {
        this.exerciseName = ExerciseTitle;
    }

    public void setExerciseSets(int sets) {
        this.sets = sets;
    }

    public void setExerciseReps(int reps) {
        this.reps = reps;
    }

    public Workout addWorkout(Workout workout) {
        if(!workoutList.contains(workout)) {
            workoutList.add(workout);
            return workout;
        } else {
            return workoutList.get(workoutList.indexOf(workout));
        }
    }

}
