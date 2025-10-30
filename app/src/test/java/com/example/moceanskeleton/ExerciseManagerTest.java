package com.example.moceanskeleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.moceanskeleton.logic.handlers.ExerciseHandler;
import com.example.moceanskeleton.logic.handlers.WorkoutHandler;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;
import org.junit.*;

import java.util.List;

public class ExerciseManagerTest {

    private ExerciseHandler exerciseHandler;
    private  WorkoutHandler workoutHandler;
    private Workout testWorkout1;
    private Workout testWorkout2;

    @Before
    public void setup() {
        System.out.println("\nStarting test for Exercise Manager");
        exerciseHandler = new ExerciseHandler(false);
        workoutHandler = new WorkoutHandler(false);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddExercise() {
        System.out.println("\nStarting testAddExercise");
        Exercise testExercise = new Exercise("Push-up", 3, 15);
        Workout testWorkout = new Workout(1, "Test Workout");
        Exercise added = exerciseHandler.addExercise(testWorkout, testExercise);
        System.out.println(added.getExerciseName());
        assertNotNull(added);
        assertEquals(testExercise, added);
        System.out.println("Finished testAddExercise");
    }

    @Test
    public void testGetExercises() {
        System.out.println("\nStarting testGetExercises");
        List<Exercise> exercises = exerciseHandler.getExercises(1);
        assertNotNull(exercises);
        assertEquals(6, exercises.size());
        System.out.println("Finished testGetExercises");
    }
}
