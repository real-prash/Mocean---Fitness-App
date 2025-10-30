package com.example.moceanskeleton;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.moceanskeleton.presentation.Home;
import com.example.moceanskeleton.presentation.Login;

import java.util.concurrent.RecursiveAction;

@RunWith(JUnit4.class)
public class WorkoutTest {

    @Test
    public void testWorkout() {

        //Log in with default username/password (admin account)
        ActivityScenario<Login> LoginActivity = ActivityScenario.launch(Login.class);
        onView(withId(R.id.username_input)).perform(replaceText("admin"));
        onView(withId(R.id.password_input)).perform(replaceText("password"));
        onView(withId(R.id.login_button)).perform(click());

        //Verify we can log in
        onView(withId(R.id.activity_home_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.weight_button)).check(matches(isDisplayed()));

        //Proceed to Tack Workouts page
        onView(withId(R.id.weight_button)).perform(click());

        //Verify we are on the Track Workout page
        onView(withId(R.id.activity_track_workouts)).check(matches(isDisplayed()));
        onView(withId(R.id.track_new_workout_button)).check(matches(isDisplayed()));

        //Proceed to Workouts page
        onView(withId(R.id.previous_workout_button)).perform(click());

        onView(withText("Pull")).check(matches(isDisplayed()));
        onView(withText("Pull")).perform(click());
        //onView(ViewMatchers.withId(R.id.lvPreviousWorkouts)).perform(actionOnItemAtPosition(0, click()));
        pressBack();
        pressBack();

        //Verify we are back on the Track Workout page, and proceed to New Workout
        onView(withId(R.id.track_new_workout_button)).perform(click());

        //Verify we are on New Workout page
        onView(withId(R.id.activity_new_workout)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAddExercise)).check(matches(isDisplayed()));

        //Proceed with entering a new workout
        onView(withId(R.id.new_workout_name)).perform(typeText("Test Workout"));
        onView(withId(R.id.etExerciseName)).perform(typeText("Push-up"));
        onView(withId(R.id.etSets)).perform(typeText("3"));
        onView(withId(R.id.etReps)).perform(typeText("15"));
        onView(withId(R.id.btnAddExercise)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.btnSaveWorkout)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Verify we are back on the Track Workout page
        onView(withId(R.id.activity_track_workouts)).check(matches(isDisplayed()));

        //Proceed to Workouts page
        onView(withId(R.id.previous_workout_button)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //onView(ViewMatchers.withId(R.id.lvPreviousWorkouts)).perform(actionOnItemAtPosition(3, click()));
        onView(withText("Test Workout")).perform(click());
        onView(withText("Push-up")).check(matches(isDisplayed()));
    }
}
