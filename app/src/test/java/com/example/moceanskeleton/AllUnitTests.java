package com.example.moceanskeleton;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Add your testing class here.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExerciseManagerTest.class,
        ExerciseTest.class,
        FoodHandlerTest.class,
        FoodTest.class,
        UserLogicHandlerTest.class,
        UserTest.class,
        WorkoutHandlerTest.class,
        WorkoutTest.class
})
public class AllUnitTests {

}
