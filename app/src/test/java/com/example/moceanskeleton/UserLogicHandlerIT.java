package com.example.moceanskeleton;

import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.logic.handlers.UserLogicHandler;
import com.example.moceanskeleton.objects.Sex;
import com.example.moceanskeleton.objects.User;
import com.example.moceanskeleton.TestUtils;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class UserLogicHandlerIT {

    private UserLogicHandler userLogicHandler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("\nStarting integration test for UserLogicHandlerIT");
        this.tempDB = TestUtils.copyDB();
        userLogicHandler = new UserLogicHandler(true);

        assertNotNull(this.userLogicHandler);
    }

    @Test
    public void testAuthenticateUserWithValidAccount() {
        System.out.println("\nStarting testAuthenticateUserWithValidAccount");
        String testUsername = "admin";
        String testPassword = "password";
        boolean result = userLogicHandler.authenticateUser(testUsername, testPassword);

        assertTrue(result);
        System.out.println("Finished testAuthenticateUserWithValidAccount");
    }

    @Test
    public void testAuthenticateUserWithInvalidAccount() {
        System.out.println("\nStarting testAuthenticateUserWithInvalidAccount");
        String testUsername = "admin";
        String testIncorrectPassword = "incorrectPassword";
        boolean result = userLogicHandler.authenticateUser(testUsername, testIncorrectPassword);

        assertFalse(result);
        System.out.println("Finished testAuthenticateUserWithInvalidAccount");
    }

    @Test
    public void testGetUserWithExistingUsername() {
        System.out.println("\nStarting testGetUserWithExistingUsername");
        String testUsername = "admin";
        User result = userLogicHandler.getUser(testUsername);

        assertNotNull(result);
        assertEquals(testUsername, result.getUsername());
        System.out.println("Finished testGetUserWithExistingUsername");
    }

    @Test
    public void testGetUserWithNonExistingUsername() {
        System.out.println("\nStarting testGetUserWithNonExistingUsername");
        String nonExistingUsername = "nonexistentUser";
        User result = userLogicHandler.getUser(nonExistingUsername);

        assertNull(result);
        System.out.println("Finished testGetUserWithNonExistingUsername");
    }

    @Test
    public void testUserExistsWithExistingUser() {
        System.out.println("\nStarting testUserExistsWithExistingUser");
        String existingUsername = "admin";
        boolean result = userLogicHandler.userExists(existingUsername);

        assertTrue(result);
        System.out.println("Finished testUserExistsWithExistingUser");
    }

    @Test
    public void testUserExistsWithNonExistingUser() {
        System.out.println("\nStarting testUserExistsWithNonExistingUser");
        String nonExistingUsername = "nonexistentUser";
        boolean result = userLogicHandler.userExists(nonExistingUsername);

        assertFalse(result);
        System.out.println("Finished testUserExistsWithNonExistingUser");
    }

    @Test
    public void testUserUpdateCalories() {
        System.out.println("\nStarting testUserUpdateCalories");
        User testUser = new User(1,"testUser", "testPassword", 180, 70.0, 30, "MALE");
        userLogicHandler.updateCalories(testUser, 30.0);
        assertEquals(userLogicHandler.getCalories(testUser), 30.0, 0.01);
        System.out.println("Finished testUserUpdateCalories");
    }

    @Test
    public void testUserAllowedAndResetCalories() {
        System.out.println("\nStarting testUserAllowedAndResetCalories");
        User testUser = new User(1, "testUser", "testPassword", 180, 70.0, 30, "MALE");

        //According to calculations in User class, testUser is allowed 1681.0 calories daily
        assertEquals(userLogicHandler.getAllowedCalories(testUser), 1681.0, 0.01);

        userLogicHandler.updateCalories(testUser, 30.0);
        assertEquals(userLogicHandler.getCalories(testUser), 30.0, 0.01);

        userLogicHandler.resetCalories(testUser);
        assertEquals(userLogicHandler.getCalories(testUser), 0.0, 0.01);
        System.out.println("\nFinished testUserAllowedAndResetCalories");
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
        Services.clean();
    }
}