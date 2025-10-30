package com.example.moceanskeleton;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import com.example.moceanskeleton.objects.Workout;
import com.example.moceanskeleton.objects.Exercise;

public class WorkoutTest {

    @Before
    public void setup() {
        System.out.println("Starting test for Workout");
    }

    @Test
    public void testWorkout() {
        System.out.println("\nStarting testWorkout");
        Date newDate = new Date();
        Workout newWorkout = new Workout(newDate);

        assertNotNull(newWorkout.getExercises());
        assertEquals(0, newWorkout.getExercises().size());
        System.out.println("Finished testWorkout");
    }

    @Test
    public void testWorkoutWithDate() {
        System.out.println("\nStarting testWorkoutWithDate");
        Date newDate = new Date();
        Workout testWorkout = new Workout(newDate);

        assertNotNull(testWorkout);
        assertEquals(newDate, testWorkout.getDate());
        System.out.println("Finished testWorkoutWithDate");
    }

    @Test
    public void testWorkoutWithIdAndName() {
        System.out.println("\nStarting testWorkoutWithIdAndName");
        int testWorkoutId = 1;
        String testWorkoutName = "Push-up";
        Workout testWorkout = new Workout(testWorkoutId, testWorkoutName);

        assertNotNull(testWorkout);
        assertEquals(testWorkoutId, testWorkout.getWorkoutID());
        assertEquals(testWorkoutName, testWorkout.getName());
        System.out.println("Finished testWorkoutWithIdAndName");
    }

    @Test
    public void testWorkoutWithName() {
        System.out.println("\nStarting testWorkoutWithName");
        String testWorkoutName = "Push-up";
        Workout testWorkout = new Workout(testWorkoutName);

        assertNotNull(testWorkout);
        assertEquals(testWorkoutName, testWorkout.getName());
        System.out.println("Finished testWorkoutWithName");
    }

    @Test
    public void testEquals() {
        System.out.println("\nStarting testEquals");
        Workout testWorkout1 = new Workout("Push-up");
        Workout testWorkout2 = new Workout("Sit-up");
        Workout testWorkout3 = new Workout("Push-up");

        //These are asserted this way to test our own equals method
        assertTrue(testWorkout1.equals(testWorkout3));
        assertFalse(testWorkout2.equals(testWorkout3));
        System.out.println("Finished testEquals");
    }

    @Test
    public void testAddExercise() {
        System.out.println("\nStarting testAddExercise");
        Date newDate = new Date();
        Workout newWorkout = new Workout(newDate);
        Exercise newExercise = new Exercise("Push-up", 5, 10);

        newWorkout.addExercise(newExercise);
        List<Exercise> exerciseList = newWorkout.getExercises();
        assertNotNull(exerciseList);
        assertEquals(1, exerciseList.size());
        assertEquals(newExercise, exerciseList.get(0));
        System.out.println("Finished testAddExercise");
    }

    @Test
    public void testGetExercises() {
        System.out.println("\nStarting testGetExercises");
        Date newDate = new Date();
        Workout newWorkout = new Workout(newDate);
        Exercise newExercise1 = new Exercise("Push-up", 5, 10);
        Exercise newExercise2 = new Exercise("Sit-up", 3, 15);

        newWorkout.addExercise(newExercise1);
        newWorkout.addExercise(newExercise2);
        List<Exercise> exerciseList = newWorkout.getExercises();
        assertNotNull(exerciseList);
        assertEquals(2, exerciseList.size());
        assertEquals(newExercise1, exerciseList.get(0));
        assertEquals(newExercise2, exerciseList.get(1));
        System.out.println("Finished testGetExercises");
    }

    @Test
    public void testSettersAndGetters() {
        System.out.println("\nStarting testSettersAndGetters");
        int testWorkoutId = 3;
        String testWorkoutName = "Push-up";
        Workout testWorkout = new Workout(testWorkoutId, testWorkoutName);
        assertEquals(3, testWorkout.getWorkoutID());

        System.out.println("Finished testSettersAndGetters");
    }
}
