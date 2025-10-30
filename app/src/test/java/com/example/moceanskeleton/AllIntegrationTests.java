package com.example.moceanskeleton;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExerciseManagerIT.class,
        FoodHandlerIT.class,
        UserLogicHandlerIT.class,
        WorkoutHandlerIT.class
})


public class AllIntegrationTests {
}
