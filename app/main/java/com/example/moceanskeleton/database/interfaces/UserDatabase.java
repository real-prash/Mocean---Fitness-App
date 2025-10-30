package com.example.moceanskeleton.database.interfaces;

import com.example.moceanskeleton.objects.User;

public interface UserDatabase {

    void addUser(User user);

    User getUser(String username);

    boolean userExists(String username);

    void updateCalories(User user, double calories);

    double getCaloriesConsumed(User user);
    double getAllowedCalories(User user);

    void resetCalories(User user);
}
