package com.example.moceanskeleton;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.example.moceanskeleton.objects.Sex;
import com.example.moceanskeleton.objects.User;

public class UserTest {

    @Before
    public void setup(){
        System.out.println("Starting test for User");
    }

    @Test
    public void testUserWithId() {
        System.out.println("\nStarting testUserWithId");
        int testId = 4;
        String testUsername = "test";
        String testPassword = "pass";
        int testHeight = 160;
        double testWeight = 70.0;
        int testAge = 24;
        String testSex = "MALE";
        User testUser = new User(testId, testUsername, testPassword, testHeight, testWeight,testAge, testSex);

        assertEquals(testUsername, testUser.getUsername());
        assertEquals(testPassword, testUser.getPassword());
        assertEquals(testHeight, testUser.getHeight());
        assertEquals(testWeight, testUser.getWeight(), 0.01);
        assertEquals(testAge, testUser.getAge());
        System.out.println("Finished testUserWithId");
    }

    @Test
    public void testUserEdgeCases() {
        System.out.println("\nStarting testUserEdgeCases");
        String testUsername = "test";
        String testPassword = "pass";
        int testHeight = -4;
        double testWeight = -16.0;
        int testAge = 0;
        User testUser = new User(testUsername, testPassword, testHeight, testWeight, testAge, Sex.FEMALE);

        assertEquals(-4, testUser.getHeight());
        assertEquals(-16, testUser.getWeight(), 0.01);
        assertEquals(0, testUser.getAge());
        System.out.println("Finished testUserEdgeCases");
    }

    @Test
    public void testAddCalories() {
        System.out.println("\nStarting testAddCalories");
        // Create a new user with no calories yet
        User testUser = new User("test", "pass1", 182, 210.5, 26, Sex.MALE);
        assertEquals(0.0, testUser.getConsumedCalories(), 0.01);

        // Add 100.0 calories to new user
        testUser.addCalories(100.0);
        assertEquals(100.0, testUser.getConsumedCalories(), 0.01);

        // Add 200.2 more calories to new user
        testUser.addCalories(200.2);
        assertEquals(300.2, testUser.getConsumedCalories(), 0.01);
        System.out.println("Finished testAddCalories");
    }

    @Test
    public void testSetAllowedCalories() {
        System.out.println("\nStarting testSetAllowedCalories");
        User testUser = new User("test", "pass1", 182, 90.5, 26, Sex.MALE);

        testUser.setWeight(80.0);
        testUser.setHeight(180);
        testUser.setAge(35);
        testUser.setSex(Sex.FEMALE);
        assertEquals(1591, testUser.getAllowedCalories(),0.01);

        testUser.setWeight(-3);
        testUser.setHeight(0);
        testUser.setAge(-24);
        testUser.setSex(Sex.MALE);
        assertEquals(93, testUser.getAllowedCalories(), 0.01);
        System.out.println("Finished testSetAllowedCalories");
    }

    @Test
    public void testSettersAndGetters() {
        System.out.println("\nStarting testSettersAndGetters");
        User testUser = new User("test", "pass1", 182, 210.5, 26, Sex.MALE);
        testUser.setUsername("testSetter");
        testUser.setPassword("pass2");
        testUser.setHeight(190);
        testUser.setWeight(215);
        testUser.setAge(30);
        testUser.setSex(Sex.FEMALE);
        assertEquals("testSetter", testUser.getUsername());
        assertEquals("pass2", testUser.getPassword());
        assertEquals(190, testUser.getHeight(), 0);
        assertEquals(215, testUser.getWeight(), 0);
        assertEquals(Sex.FEMALE, testUser.getSex());
        System.out.println("Finished testSettersAndGetters");
    }
}
