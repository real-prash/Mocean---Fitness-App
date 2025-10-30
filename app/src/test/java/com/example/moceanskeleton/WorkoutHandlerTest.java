package com.example.moceanskeleton;

import com.example.moceanskeleton.logic.handlers.WorkoutHandler;
import com.example.moceanskeleton.objects.Workout;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;

public class WorkoutHandlerTest {

    private Workout testWorkout1;
    private Workout testWorkout2;
    private WorkoutHandler workoutHandler = new WorkoutHandler(false);

    @Before
    public void setup() {
        System.out.println("\nStaring test for WorkoutManager");
        Date newDate1 = new Date();

        // Added this delay to ensure test workouts have different timestamps
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date newDate2 = new Date();
        testWorkout1 = new Workout(newDate1);
        testWorkout2 = new Workout(newDate2);
    }

    @Test
    public void testAddWorkout() {
        System.out.println("\nStarting testAddWorkout");
        workoutHandler.addWorkout(testWorkout1, null);

        List<Workout> testWorkouts = workoutHandler.getWorkouts();

        assertEquals(4, testWorkouts.size());
        assertTrue(testWorkouts.contains(testWorkout1));
        System.out.println("Finished testAddWorkout");
    }

    @Test
    public void testGetWorkouts() {
        System.out.println("\nStarting testGetWorkouts");
        int size = workoutHandler.getWorkouts().size();
        workoutHandler.addWorkout(testWorkout1, null);
        workoutHandler.addWorkout(testWorkout2, null);

        List<Workout> testWorkouts = workoutHandler.getWorkouts();

        assertEquals(size+2, testWorkouts.size());
        assertTrue(testWorkouts.contains(testWorkout1));
        assertTrue(testWorkouts.contains(testWorkout2));
        System.out.println("Finished testGetWorkouts");
    }
}
