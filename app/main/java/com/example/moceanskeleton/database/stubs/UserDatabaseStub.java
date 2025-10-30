package com.example.moceanskeleton.database.stubs;

import com.example.moceanskeleton.database.interfaces.UserDatabase;
import com.example.moceanskeleton.objects.Sex;
import com.example.moceanskeleton.objects.User;

import java.util.HashMap;
import java.util.Map;

public class UserDatabaseStub implements UserDatabase {
    private final Map<String, User> USERS;

    public UserDatabaseStub() {
        this.USERS = new HashMap<>();
        // Add an admin account, username: admin, password: password
        addUser(new User("admin", "password", 180, 100, 30, Sex.valueOf("MALE")));
    }

    public void addUser(User user) {
        USERS.put(user.getUsername(), user);
    }

    public User getUser(String username) {

        return USERS.get(username);
    }

    @Override
    public boolean userExists(String username) {
        return getUser(username) != null;
    }

    @Override
    public void updateCalories(User user, double calories) {
        user.addCalories(calories);
    }

    @Override
    public double getCaloriesConsumed(User user) {
        return user.getConsumedCalories();
    }

    @Override
    public double getAllowedCalories(User user) {
        return user.getAllowedCalories();
    }

    public void resetCalories(User user){
        user.setConsumedCalories(0);
    }
}
