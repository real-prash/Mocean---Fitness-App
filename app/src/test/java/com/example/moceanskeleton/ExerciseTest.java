package com.example.moceanskeleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExerciseTest {

    @Before
    public void setup() {
        System.out.println("Starting test for Exercise");
    }

    @Test
    public void testExercise() {
        System.out.println("\nStarting testExercise");
        int testExerciseId = 3;
        Exercise testExercise = new Exercise(testExerciseId);

        assertEquals(testExerciseId, testExercise.getExerciseID());
        assertEquals(0, testExercise.getExerciseSets());
        assertEquals(0, testExercise.getExerciseReps());
        System.out.println("Finished testExercise");
    }

    @Test
    public void testExerciseFromDb() {
        System.out.println("\nStarting testExerciseFromDb");
        String testExerciseName = "Push-up";
        int testSets = 5;
        int testReps = 10;
        int testExerciseID = 2;
        Date testDate = new Date();
        Exercise testExercise = new Exercise(testExerciseID, testExerciseName, testSets, testReps, testDate);

        assertEquals(testExerciseName, testExercise.getExerciseName());
        assertEquals(testSets, testExercise.getExerciseSets());
        assertEquals(testReps, testExercise.getExerciseReps());
        assertEquals(testExerciseID, testExercise.getExerciseID());
        System.out.println("Finished testExerciseFromDb");
    }

    @Test
    public void testAddWorkout() {
        System.out.println("\nStarting testAddWorkout");
        Date testDate = new Date();
        Exercise testExercise = new Exercise("Push-up", 5, 10);
        Workout testWorkout1 = new Workout(testDate);
        Workout testWorkout2 = new Workout("Sit-up");

        testExercise.addWorkout(testWorkout1);
        testExercise.addWorkout(testWorkout2);
        List<Workout> testWorkoutList = testExercise.getWorkoutList();

        assertNotNull(testWorkoutList);
        assertEquals(2, testWorkoutList.size());
        assertTrue(testWorkoutList.contains(testWorkout1));
        assertTrue(testWorkoutList.contains(testWorkout2));
        System.out.println("Finished testAddWorkout");
    }

    @Test
    public void testSettersAndGetters() {
        System.out.println("\nStarting testSettersAndGetters");
        Exercise exercise = new Exercise("Push-up", 5, 10);
        exercise.setExerciseName("Sit-up");
        exercise.setExerciseSets(3);
        exercise.setExerciseReps(15);

        assertEquals("Sit-up", exercise.getExerciseName());
        assertEquals(3, exercise.getExerciseSets());
        assertEquals(15, exercise.getExerciseReps());
        System.out.println("Finished testSettersAndGetters");
    }
}
