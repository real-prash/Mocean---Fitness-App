package com.example.moceanskeleton;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;


import com.example.moceanskeleton.presentation.Login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class FoodSystemTest {

    @Test
    public void testCreateFood() { //This also tests the search feature after we add the food

        login();
        String foodName = "Mangosteen";
        String foodQuantity = "250";
        String calories = " 143";
        String carbs = "35";
        String fats = "1";
        String protein = "1";

        // Switch to adding food
        onView(withId(R.id.calorie_add_button)).perform(click());
        // Verify we got to the page
        onView(withId(R.id.create_food_act)).check(matches(isDisplayed()));

        // Add a food item
        onView(withId(R.id.add_food_name)).perform(replaceText(foodName));
        onView(withId(R.id.add_food_quantity)).perform(replaceText(foodQuantity));
        onView(withId(R.id.add_food_calories)).perform(replaceText(calories));
        onView(withId(R.id.add_food_carbs)).perform(replaceText(carbs));
        onView(withId(R.id.add_food_fats)).perform(replaceText(fats));
        onView(withId(R.id.add_food_protein)).perform(replaceText(protein), closeSoftKeyboard());

        onView(withId(R.id.add_food_submit_button)).perform(click());
        // Check if the inserted item is there
        // Switch to search food page
        searchFood();
        //Search for the item
        onView(withId(R.id.search_view)).perform(typeText(foodName));

        // Make sure the recycler is updated
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        //Check if item matches the input item
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(foodName))))
                .check(matches(hasDescendant(withText(foodName))));

    }

    private void login(){
        //Log in with default username/password (admin account)
        ActivityScenario<Login> LoginActivity = ActivityScenario.launch(Login.class);
        onView(withId(R.id.username_input)).perform(replaceText("admin"));
        onView(withId(R.id.password_input)).perform(replaceText("password"));
        onView(withId(R.id.login_button)).perform(click());
    }

    private void searchFood(){
        // Switch to search food page
        onView(withId(R.id.food_add_button)).perform(click());
        // Verify we got to the page
        onView(withId(R.id.add_food_search)).check(matches(isDisplayed()));
    }
    @Test
    public void testLogFood(){

        String calorieTotal = "/1981.00";
        String apple = "Apple";
        String appleCals = "52.00";

        // Login to account
        login();

        // Move to search food page
        searchFood();

        // Wait for the RecyclerView to be updated
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));

        // Click on Apple
        onView(withText(apple)).perform(click());

        // Verify we got back to Home
        onView(withId(R.id.activity_home_layout)).check(matches(isDisplayed()));

        //Verify calories added is correct
        onView(withId(R.id.text_progress_2)).check(matches(withText(appleCals+calorieTotal)));

        onView(withId(R.id.reset_cals)).perform(click());

    }

    @Test
    public void testResetCalories(){

        String emptyCals = "0.00/1981.00";

        testLogFood();

        onView(withId(R.id.reset_cals)).perform(click());

        onView(withId(R.id.text_progress_2)).check(matches(withText(emptyCals)));

    }

}

