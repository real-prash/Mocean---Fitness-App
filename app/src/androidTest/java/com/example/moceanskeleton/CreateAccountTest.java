package com.example.moceanskeleton;

import androidx.test.core.app.ActivityScenario;

import static androidx.test.espresso.action.ViewActions.*;
import com.example.moceanskeleton.presentation.Login;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class CreateAccountTest {

    @Test
    public void testCreateAccountWithValidInput() {

        ActivityScenario<Login> activityScenarioRule = ActivityScenario.launch(Login.class);
        onView(withId(R.id.activity_login)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_login)).perform(closeSoftKeyboard());

        onView(withId(R.id.create_account_button)).perform(click());


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.activity_create_account)).check(matches(isDisplayed()));

        String testUsername = "testUser";
        String testPassword = "testPassword";
        String testHeight = "180";
        String testWeight = "75.0";
        String testAge = "30";

        //Intents.init();

        onView(withId(R.id.username_edittext)).perform(typeText(testUsername));
        onView(withId(R.id.password_edittext)).perform(typeText(testPassword));
        onView(withId(R.id.height_edittext)).perform(typeText(testHeight));
        onView(withId(R.id.weight_edittext)).perform(typeText(testWeight));
        onView(withId(R.id.age_edittext)).perform(typeText(testAge));

        onView(withId(R.id.male_radio_button)).perform(click(), closeSoftKeyboard());

        onView(withId(R.id.create_account_submit_button)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.activity_login)).check(matches(isDisplayed()));

        onView(withId(R.id.username_input)).perform(replaceText(testUsername));
        onView(withId(R.id.password_input)).perform(replaceText(testPassword));
        onView(withId(R.id.login_button)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        onView(withId(R.id.activity_home_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.settings_button)).perform(click());

        onView(withId(R.id.username_textview)).check(matches(withText(testUsername)));
        onView(withId(R.id.height_textview)).check(matches(withText("Height: \t"+ testHeight + " cm")));
        onView(withId(R.id.weight_textview)).check(matches(withText("Weight: \t"+ testWeight+ " kg")));
        onView(withId(R.id.age_textview)).check(matches(withText("Age: \t" + testAge + " years old")));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.update_info_button)).perform(click());

        onView(withId(R.id.activity_update_info)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_button)).check(matches(isDisplayed()));

        testHeight = "190";
        testWeight = "70.0";
        testAge = "31";

        onView(withId(R.id.height_edittext)).perform(replaceText(testHeight));
        onView(withId(R.id.weight_edittext)).perform(replaceText(testWeight));
        onView(withId(R.id.age_edittext)).perform(replaceText(testAge));
        onView(withId(R.id.submit_button)).perform(click());

        onView(withId(R.id.activity_profile)).check(matches(isDisplayed()));
        onView(withId(R.id.update_info_button)).check(matches(isDisplayed()));

        onView(withId(R.id.height_textview)).check(matches(withText("Height: \t"+ testHeight + " cm")));
        onView(withId(R.id.weight_textview)).check(matches(withText("Weight: \t"+ testWeight + " kg")));
        onView(withId(R.id.age_textview)).check(matches(withText("Age: \t" + testAge + " years old")));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}