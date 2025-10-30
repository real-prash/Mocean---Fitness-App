package com.example.moceanskeleton;

import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@LargeTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateAccountTest.class,
        FoodSystemTest.class,
        LoginTest.class,
        WorkoutTest.class
})

public class AllAcceptanceTests {
}
