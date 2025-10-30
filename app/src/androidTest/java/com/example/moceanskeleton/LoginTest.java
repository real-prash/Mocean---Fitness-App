package com.example.moceanskeleton;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.moceanskeleton.presentation.Home;
import com.example.moceanskeleton.presentation.Login;

@RunWith(JUnit4.class)
public class LoginTest {

    //@Rule
    //public ActivityScenarioRule<Login> activityScenarioRule = new ActivityScenarioRule<>(Login.class);

    @Test
    public void testLoginWithValidCredentials() {

        //Log in with default username/password (admin account)
        ActivityScenario<Login> LoginActivity = ActivityScenario.launch(Login.class);
        onView(withId(R.id.username_input)).perform(replaceText("admin"));
        onView(withId(R.id.password_input)).perform(replaceText("password"));
        onView(withId(R.id.login_button)).perform(click());

        //Verify we can log in
        onView(withId(R.id.activity_home_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_button)).check(matches(isDisplayed()));

        //Proceed to settings page
        onView(withId(R.id.settings_button)).perform(click());

        //Verify we are on the settings page
        onView(withId(R.id.activity_profile)).check(matches(isDisplayed()));
        onView(withId(R.id.update_info_button)).check(matches(isDisplayed()));

        //Proceed to logout, and return to main screen
        onView(withId(R.id.logout)).perform(click());

        //Verify we are back on the login screen
        onView(withId(R.id.activity_login)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }
}
