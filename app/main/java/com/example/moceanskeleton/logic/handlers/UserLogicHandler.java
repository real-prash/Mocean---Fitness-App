package com.example.moceanskeleton.logic.handlers;

import com.example.moceanskeleton.database.interfaces.UserDatabase;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.objects.User;

public class UserLogicHandler {

    private UserDatabase userDB;

    public UserLogicHandler(boolean forProd){
        userDB = Services.getUserDatabase(forProd);
    }
    public boolean authenticateUser(String username, String password) {
        User user = userDB.getUser(username);

        // Make sure the given password is paired with the given username.
        return user != null && user.getPassword().equals(password);
    }

    public User getUser(String username) {
        return userDB.getUser(username);
    }


    public  boolean userExists(String username) {
        return userDB.userExists(username);
    }

    public void updateCalories(User user, double calories){
        userDB.updateCalories(user, calories);
    }

    public double getCalories(User user){
        return userDB.getCaloriesConsumed(user);
    }

    public double getAllowedCalories(User user) {
        return userDB.getAllowedCalories(user);
    }

    public void resetCalories(User user){
        userDB.resetCalories(user);
    }
}
