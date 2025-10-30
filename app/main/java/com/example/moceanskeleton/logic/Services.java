package com.example.moceanskeleton.logic;


import android.widget.EditText;

import com.example.moceanskeleton.application.Main;
import com.example.moceanskeleton.database.interfaces.FoodDatabase;
import com.example.moceanskeleton.database.implementations.FoodDatabaseHSQLDB;
import com.example.moceanskeleton.database.stubs.FoodDatabaseStub;
import com.example.moceanskeleton.database.interfaces.UserDatabase;
import com.example.moceanskeleton.database.implementations.UserDatabaseHSQLDB;
import com.example.moceanskeleton.database.stubs.UserDatabaseStub;
import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.database.interfaces.ExercisesDatabase;
import com.example.moceanskeleton.database.implementations.WorkoutsDatabaseHSQLDB;
import com.example.moceanskeleton.database.implementations.ExercisesDatabaseHSQLDB;
import com.example.moceanskeleton.database.stubs.WorkoutsDatabaseStub;
import com.example.moceanskeleton.database.stubs.ExercisesDatabaseStub;


public class Services {
    private static FoodDatabase foodDatabase = null;
    private static UserDatabase userDatabase = null;
    private static WorkoutsDatabase WorkoutsDatabase = null;
    private static ExercisesDatabase ExercisesDatabase = null;




    public static synchronized FoodDatabase getFoodDatabase(boolean forProduction) {
        if (foodDatabase == null) {
            if (forProduction) {
                foodDatabase = new FoodDatabaseHSQLDB(Main.getDBPathName());
            } else {
                foodDatabase = new FoodDatabaseStub();
            }
        }
        return foodDatabase;
    }

    public static synchronized WorkoutsDatabase getWorkoutsDatabase(boolean forProduction) {
        if(WorkoutsDatabase == null) {
            if (forProduction) {
                WorkoutsDatabase = new WorkoutsDatabaseHSQLDB(Main.getDBPathName());
            } else {
                WorkoutsDatabase = new WorkoutsDatabaseStub();
            }
        }
        return WorkoutsDatabase;
    }

    public static synchronized ExercisesDatabase getExercisesDatabase(boolean forProduction) {
        if(ExercisesDatabase == null) {
            if (forProduction) {
                ExercisesDatabase = new ExercisesDatabaseHSQLDB(getWorkoutsDatabase(forProduction), Main.getDBPathName());
            } else {
                ExercisesDatabase = new ExercisesDatabaseStub();
            }
        }
        return ExercisesDatabase;
    }

    public static synchronized UserDatabase getUserDatabase(boolean forProduction) {
        if(userDatabase == null) {
            if (forProduction) {
                userDatabase = new UserDatabaseHSQLDB(Main.getDBPathName());
            } else {
                userDatabase = new UserDatabaseStub();
            }
        }
        return userDatabase;
    }

    public static synchronized void clean() {
        ExercisesDatabase = null;
        foodDatabase = null;
        userDatabase = null;
        WorkoutsDatabase = null;
    }

    public static String getTrimmedString(EditText string) {
        return string.getText().toString().trim();
    }

    public static boolean validIntSize(String input){
        boolean toReturn = false;
        if(input != null){
            try{
                int validInt = Integer.parseInt(input);
                toReturn = true;
            }catch(NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }
        }
        return toReturn;
    }

    public static boolean validDoubleSize(String input){
        boolean toReturn = false;
        if(input != null){
            try{
                double validDouble = Double.parseDouble(input);
                toReturn = true;
            }catch(NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }
        }
        return toReturn;
    }

}

