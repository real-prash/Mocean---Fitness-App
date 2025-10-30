package com.example.moceanskeleton.database.stubs;

import java.util.ArrayList;
import java.util.List;

import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;

public class WorkoutsDatabaseStub implements WorkoutsDatabase {

    private List<Workout> workouts;

    public WorkoutsDatabaseStub() {
        this.workouts = new ArrayList<>();

        Workout pullWorkout = new Workout(1, "Pull");
        pullWorkout.addExercise(new Exercise("Lat Pull-downs", 4, 15));
        pullWorkout.addExercise(new Exercise("Seated Cable Rows", 3, 12));
        pullWorkout.addExercise(new Exercise("Barbell Bent-over Rows", 4, 8));
        pullWorkout.addExercise(new Exercise("Barbell Bicep Curls", 4, 12));
        pullWorkout.addExercise(new Exercise("DB Bicep Hammer Curls", 3, 12));
        pullWorkout.addExercise(new Exercise("Barbell Bicep Preacher Curls", 3, 12));
        workouts.add(pullWorkout);

        Workout pushWorkout = new Workout(2, "Push");
        pushWorkout.addExercise(new Exercise("Barbell Bench Press", 3, 15));
        pushWorkout.addExercise(new Exercise("DB Seated Shoulder Press", 3, 12));
        pushWorkout.addExercise(new Exercise("Pec Deck Fly's", 4, 15));
        pushWorkout.addExercise(new Exercise("DB Lateral Raises", 4, 12));
        pushWorkout.addExercise(new Exercise("Rope Triceps Push downs", 3, 12));
        pushWorkout.addExercise(new Exercise("DB Single-arm Overhead Triceps Extensions", 3, 12));
        workouts.add(pushWorkout);

        Workout legsWorkout = new Workout(3, "Legs");
        legsWorkout.addExercise(new Exercise("Barbell Squats", 3, 8));
        legsWorkout.addExercise(new Exercise("Leg Press", 3, 12));
        legsWorkout.addExercise(new Exercise("Leg Extensions", 4, 15));
        legsWorkout.addExercise(new Exercise("Leg Curls", 4, 12));
        legsWorkout.addExercise(new Exercise("Seated Calf Raises", 3, 12));
        workouts.add(legsWorkout);

    }

    @Override
    public List<Workout> getAllWorkouts(){
        return workouts;
    }

    @Override
    public Workout insertWorkout(Workout targetWorkout, ArrayList<Integer> exerciseIDs){
        if(!workouts.contains(targetWorkout)) {
            workouts.add(targetWorkout);
        }

        return targetWorkout;
    }


}