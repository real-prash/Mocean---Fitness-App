package com.example.moceanskeleton.database.implementations;


import android.util.Log;

import com.example.moceanskeleton.database.interfaces.UserDatabase;
import com.example.moceanskeleton.objects.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDatabaseHSQLDB implements UserDatabase {
    private String dbPath;
    private final Map<String, User> USERS;
    public UserDatabaseHSQLDB(String dbPath){
        this.dbPath = dbPath;
        USERS = new HashMap<>();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
    @Override
    public void addUser(User user) {
        try(final Connection conn = connect()){
            final PreparedStatement statement =
                    conn.prepareStatement("INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)");
            statement.setString(1,user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getHeight());
            statement.setDouble(4, user.getWeight());
            statement.setInt(5,user.getAge());
            statement.setString(6, user.getSex().toString());

            statement.executeUpdate();
            statement.close();
            USERS.put(user.getUsername(), user);

            User userWithID = getUser(user.getUsername());

            final PreparedStatement calorieStatement = conn.prepareStatement("INSERT INTO USERS_CALORIES VALUES(?, 0.0)");

            calorieStatement.setInt(1, userWithID.getId());
            calorieStatement.executeUpdate();

        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + "\n" + e.getSQLState());
            e.printStackTrace();
        }
    }
    private User fromResultSet(final ResultSet rs) throws SQLException{
        int userID = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        int height = rs.getInt("height");
        double weight = rs.getBigDecimal("weight").doubleValue();
        int age = rs.getInt("age");
        String sex = rs.getString("sex");
        return new User(userID, username, password, height, weight, age, sex);
    }

    @Override
    public User getUser(String username) {
        try(final Connection conn = connect()){
            final PreparedStatement statement = conn.prepareStatement("SELECT * FROM USERS WHERE ? = USERS.username");
            statement.setString(1, username);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return fromResultSet(rs);
            }
        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + "\n" + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userExists(String username) {
        return getUser(username) != null;
    }

    @Override
    public void updateCalories(User user, double calories) {
        try(final Connection conn = connect()){
            final PreparedStatement statement = conn.prepareStatement("SELECT consumed_cals FROM USERS_CALORIES WHERE user_id = ?");
            statement.setInt(1, user.getId());
            final ResultSet rs = statement.executeQuery();
            double currConsumed = 0;
            if(rs.next()) {
                currConsumed = rs.getBigDecimal("consumed_cals").doubleValue();
            }
            currConsumed += calories;
            final PreparedStatement updateStatement = conn.prepareStatement("UPDATE USERS_CALORIES SET consumed_cals = ? WHERE user_id = ?");
            updateStatement.setBigDecimal(1, BigDecimal.valueOf(currConsumed));
            updateStatement.setInt(2, user.getId());
            updateStatement.executeUpdate();

        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + "\n" + e.getSQLState());
            e.printStackTrace();
        }
    }

    public double getCaloriesConsumed(User user){
        // Return the calories logged in the user calories table with the associated date and user
        try(final Connection conn = connect()){
            final PreparedStatement statement = conn.prepareStatement("SELECT consumed_cals FROM USERS_CALORIES WHERE user_id = ?");
            statement.setInt(1, user.getId());
            final ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return rs.getBigDecimal("consumed_cals").doubleValue();
            }
        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + "\n" + e.getSQLState());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getAllowedCalories(User user) {
        return user.getAllowedCalories();
    }

    public void resetCalories(User user){
        try(final Connection conn = connect()){
            final PreparedStatement updateStatement = conn.prepareStatement("UPDATE USERS_CALORIES SET consumed_cals = ? WHERE user_id = ?");
            updateStatement.setBigDecimal(1, BigDecimal.valueOf(0));
            updateStatement.setInt(2, user.getId());
            updateStatement.executeUpdate();
            user.setConsumedCalories(0);
        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + "\n" + e.getSQLState());
            e.printStackTrace();
        }
    }
}
