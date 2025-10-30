package com.example.moceanskeleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.moceanskeleton.logic.handlers.UserLogicHandler;
import com.example.moceanskeleton.objects.User;
import org.junit.*;


public class UserLogicHandlerTest {

    private UserLogicHandler userLogicHandler;

    @Before
    public void setup() {
        System.out.println("\nStarting test for UserLogicHandler");
        userLogicHandler = new UserLogicHandler(false);
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
}
