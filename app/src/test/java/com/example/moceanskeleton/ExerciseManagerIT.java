package com.example.moceanskeleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.moceanskeleton.database.stubs.ExercisesDatabaseStub;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.logic.handlers.ExerciseHandler;
import com.example.moceanskeleton.logic.handlers.WorkoutHandler;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExerciseManagerIT {

    private ExerciseHandler exerciseHandler;
    private  WorkoutHandler workoutHandler;
    private File tempDB;
    private Workout testWorkout1;
    private Workout testWorkout2;

    @Before
    public void setup() throws IOException {
        System.out.println("\nStarting test for Exercise Manager");
        this.tempDB = TestUtils.copyDB();
        exerciseHandler = new ExerciseHandler(true);
        workoutHandler = new WorkoutHandler(true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAddExercise() {
        System.out.println("\nStarting testAddExercise");
        Date newDate1 = new Date();
        Exercise testExercise = new Exercise(18, "Push-up", 3, 15, newDate1);
        Workout testWorkout = new Workout(1, "Test Workout");
        Exercise added = exerciseHandler.addExercise(testWorkout, testExercise);
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
        workoutHandler.getWorkouts().clear();
        System.out.println("Finished testGetExercises");
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
        Services.clean();
    }
}
