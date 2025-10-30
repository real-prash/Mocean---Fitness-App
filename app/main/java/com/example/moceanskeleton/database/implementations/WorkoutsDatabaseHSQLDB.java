package com.example.moceanskeleton.database.implementations;

import android.util.Log;

import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.objects.Workout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkoutsDatabaseHSQLDB implements WorkoutsDatabase {

    private final String dbPath;
    private List<Workout> workouts;

    public WorkoutsDatabaseHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.workouts = new ArrayList<>();
        loadWorkouts();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Workout fromResultSet(final ResultSet rs) throws SQLException {
        int workoutId = rs.getInt("id");
        String workoutName = rs.getString("name");

        return new Workout(workoutId, workoutName);
    }

    private void loadWorkouts() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM WORKOUT");

            while (resultSet.next()) {
                final Workout workout = fromResultSet(resultSet);
                this.workouts.add(workout);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workouts;
    }

    @Override
    public Workout insertWorkout(Workout workout, ArrayList<Integer> exerciseIDs) {
        System.out.println("[LOG] Inserting a workout");

        if (workouts.contains(workout)) {
            System.out.println("[LOG] workout already exists. done");
            return workouts.get(workouts.indexOf(workout));
        }

        System.out.println("[LOG] inserting " + workout);

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO WORKOUT VALUES(DEFAULT, ?)");
            statement.setString(1, workout.getName());
            statement.executeUpdate();
            statement.close();

            final PreparedStatement newStatement = connection.prepareStatement("SELECT * FROM WORKOUT WHERE name = ?");
            newStatement.setString(1, workout.getName());
            final ResultSet resultSet = newStatement.executeQuery();

            if (resultSet.next()) {
                final Workout work = fromResultSet(resultSet);
                System.out.println(work + " ");
                this.workouts.add(work);
                final PreparedStatement linkStatement = connection.prepareStatement("INSERT INTO EXERCISES_WORKOUTS VALUES(?, ?)");
                for (int i=0; i<exerciseIDs.size(); i++) {
                    linkStatement.setInt(1, exerciseIDs.get(i));
                    linkStatement.setInt(2, work.getWorkoutID());
                    linkStatement.executeUpdate();
                }
                linkStatement.close();

                return work;
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }
}
