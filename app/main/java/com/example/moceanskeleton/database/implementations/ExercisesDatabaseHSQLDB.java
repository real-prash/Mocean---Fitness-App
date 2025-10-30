package com.example.moceanskeleton.database.implementations;

import android.util.Log;

import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.database.interfaces.ExercisesDatabase;
import com.example.moceanskeleton.objects.Exercise;
import com.example.moceanskeleton.objects.Workout;
import com.example.moceanskeleton.database.utility.DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

public class ExercisesDatabaseHSQLDB implements ExercisesDatabase {

    private final String dbPath;
    private com.example.moceanskeleton.database.interfaces.WorkoutsDatabase WorkoutsDatabase;
    private List<Exercise> exercises;

    public ExercisesDatabaseHSQLDB(WorkoutsDatabase WorkoutsDatabase, String dbPath) {
        this.dbPath = dbPath;
        this.exercises = new ArrayList<>();
        this.WorkoutsDatabase = WorkoutsDatabase;
        loadExercises();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Exercise fromResultSet(final ResultSet rs) throws SQLException {
        int exerciseID = rs.getInt("id");
        String exerciseName = rs.getString("name");
        int exerciseSets = rs.getInt("sets");
        int exerciseReps = rs.getInt("reps");
        Date date = rs.getTimestamp("creation_date");
        return new Exercise(exerciseID, exerciseName, exerciseSets, exerciseReps, date);
    }

    private void loadExercises() {

        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM EXERCISES");

            while (resultSet.next()) {
                final Exercise exercise = fromResultSet(resultSet);
                connectExerciseWithWorkout(exercise);
                this.exercises.add(exercise);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private void connectExerciseWithWorkout(Exercise exercise) {

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT workout_id FROM EXERCISES_WORKOUTS WHERE EXERCISES_WORKOUTS.exercise_id = ?");
            statement.setInt(1, exercise.getExerciseID());

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int workoutID = resultSet.getInt("workout_id");
                for (Workout workout: WorkoutsDatabase.getAllWorkouts()) {
                    if (workout.getWorkoutID() == workoutID) {
                        workout.addExercise(exercise);
                    }
                }
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    public Exercise getExerciseById(int ExerciseId) {
        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM EXERCISES WHERE EXERCISES.id = ?");
            statement.setString(1, Integer.toString(ExerciseId));
            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return fromResultSet(resultSet);
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }


    public Exercise insertExercise(Workout workout, Exercise Exercise) {
        System.out.println("[LOG] Inserting Exercise " + Exercise.getExerciseName());
        String dateString = DBHelper.getSQLDateString(Exercise.getExerciseDate());

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO EXERCISES VALUES(DEFAULT, ?, ?, ?, ?)");
            statement.setString(1, Exercise.getExerciseName());
            statement.setInt(2, Exercise.getExerciseSets());
            statement.setInt(3, Exercise.getExerciseReps());
            statement.setString(4, dateString);

            statement.executeUpdate();
            statement.close();

            // Since the Exercise was automatically assigned an ID by the database
            // we need to retrieve the Exercise using title + creation date and get its ID
            final PreparedStatement newStatement = connection.prepareStatement("SELECT * FROM EXERCISES WHERE name = ? AND creation_date = ?");
            newStatement.setString(1, Exercise.getExerciseName());
            newStatement.setString(2, dateString);
            final ResultSet resultSet = newStatement.executeQuery();

            if (resultSet.next()) {
                final Exercise ExerciseWithId = fromResultSet(resultSet);
                this.exercises = new ArrayList<>();
                loadExercises();
                return ExerciseWithId;
            }

            return Exercise;

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }


    public List<Exercise> getExercisesByWorkoutId(int workoutId) {

        List<Exercise> exercisesByWorkoutId = new ArrayList<>();

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM EXERCISES_WORKOUTS WHERE workout_id = ?");
            statement.setInt(1, workoutId);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int exerciseId = resultSet.getInt("exercise_id");
                Exercise exercise = getExerciseById(exerciseId);
                if (exercise != null) {
                    exercisesByWorkoutId.add(exercise);
                }
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return exercisesByWorkoutId;
    }


}
