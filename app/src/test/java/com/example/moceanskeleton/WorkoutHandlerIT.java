package com.example.moceanskeleton;

import static org.junit.Assert.*;

import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.database.implementations.WorkoutsDatabaseHSQLDB;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.logic.handlers.ExerciseHandler;
import com.example.moceanskeleton.logic.handlers.WorkoutHandler;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.File;
import java.io.IOException;

public class WorkoutHandlerIT {

    private ArrayList<Integer> exercises;
    private ExerciseHandler exerciseHandler;
    private Workout testWorkout1;
    private Workout testWorkout2;
    private WorkoutHandler workoutHandler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("\nStaring test for WorkoutManager");
        this.tempDB = TestUtils.copyDB();
        this.exerciseHandler = new ExerciseHandler(true);
        this.workoutHandler = new WorkoutHandler(true);
        Date newDate1 = new Date();

        // Added this delay to ensure test workouts have different timestamps
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date newDate2 = new Date();
        //testWorkout1 = new Workout(newDate1);
        testWorkout1 = new Workout("Push-up");
        testWorkout2 = new Workout(newDate2);
        workoutHandler.getWorkouts().clear();
        exercises = new ArrayList<Integer>();
        exercises.add(1);
        exercises.add(3);
    }

    @Test
    public void testAddWorkout() {
        System.out.println("\nStarting testAddWorkout");
        workoutHandler.addWorkout(testWorkout1, exercises);

        List<Workout> testWorkouts = workoutHandler.getWorkouts();

        assertEquals(1, testWorkouts.size());
        assertEquals(testWorkout1, testWorkouts.get(0));
        System.out.println("Finished testAddWorkout");
    }

    @Test
    public void testGetWorkouts() {
        System.out.println("\nStarting testGetWorkouts");
        workoutHandler.addWorkout(testWorkout1, exercises);
        workoutHandler.addWorkout(testWorkout2, exercises);

        List<Workout> testWorkouts = workoutHandler.getWorkouts();

        assertEquals(2, testWorkouts.size());
        assertTrue(testWorkouts.contains(testWorkout1));
        assertTrue(testWorkouts.contains(testWorkout2));
        System.out.println("Finished testGetWorkouts");
    }

    @Test
    public void testValidWorkoutInput() {
        System.out.println("\nStarting testValidWorkoutInput");
        String testName = "Test Name";
        String testSets = "3";
        String testReps = "15";
        String testWorkoutName = "Test Workout";

        boolean isValid = workoutHandler.validWorkoutInput(testName, testSets, testReps, testWorkoutName);
        assertTrue(isValid);
        System.out.println("Finished testValidWorkoutInput");
    }

    @Test
    public void testInvalidWorkoutInput() {
        System.out.println("\nStarting testInvalidWorkoutInput");
        String testName = "";
        String testSets = "3";
        String testReps = "15";
        String testWorkoutName = "Workout";

        boolean isValid = workoutHandler.validWorkoutInput(testName, testSets, testReps, testWorkoutName);
        assertFalse(isValid);
        System.out.println("Finished testInvalidWorkoutInput");
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
        Services.clean();
    }
}