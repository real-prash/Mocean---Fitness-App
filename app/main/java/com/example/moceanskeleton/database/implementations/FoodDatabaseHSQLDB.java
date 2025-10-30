package com.example.moceanskeleton.database.implementations;

import android.util.Log;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.database.interfaces.FoodDatabase;
import com.example.moceanskeleton.objects.Food;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodDatabaseHSQLDB implements FoodDatabase {

    private final String dbPath;
    private ArrayList<Food> foodList;

    public FoodDatabaseHSQLDB(String dbPath){
        this.dbPath = dbPath;
        this.foodList = new ArrayList<>();
        loadFood();
    }

    private Food fromResultSet(final ResultSet rs) throws SQLException{
        int foodID = rs.getInt("id");
        String foodName = rs.getString("name");
        String foodType = rs.getString("foodGroup");
        int foodGroupID = rs.getInt("food_group");
        int calories = rs.getInt("calories");
        int fat = rs.getInt("fat_grams");
        int carbs = rs.getInt("carbs_grams");
        int protein = rs.getInt("protein_grams");
        int quantity = rs.getInt("quantity");

        return new Food(foodID, foodName, foodType, foodGroupID, quantity, calories, carbs, fat, protein, R.drawable.add_food_icon);
    }

    private void loadFood() {

        try(Connection conn = connect()){
            final Statement statement = conn.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD JOIN FOOD_GROUPS ON FOOD_GROUPS.ID = FOOD.food_group");

            while(resultSet.next()){
                final Food food = fromResultSet(resultSet);
                this.foodList.add(food);
            }
        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    public Boolean removeFoodItem(Food food) {
        return foodList.remove(food);
    }

    @Override
    public ArrayList<Food> getFoodList() {
        if(foodList.isEmpty()){
            loadFood();
        }
        return foodList;
    }

    @Override
    public Food getFoodByID(int foodID) {
        for(int i=0; i< foodList.size(); i++){
            if(foodList.get(i).getFoodID() == foodID){
                return foodList.get(i);
            }
        }
        return null;
    }

    @Override
    public Food insertFoodItem(Food food) {
        try(Connection conn = connect()){
            final PreparedStatement statement =
                    conn.prepareStatement("INSERT INTO FOOD VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, food.getFoodName());
            statement.setInt(2, food.getFoodGroupID());
            statement.setInt(3, food.getFoodQuantity());
            statement.setInt(4, food.getFoodCalories());
            statement.setInt(5, food.getCarbs());
            statement.setInt(6, food.getFats());
            statement.setInt(7, food.getProtein());

            statement.executeUpdate();
            statement.close();
            foodList.add(food);

            return food;
        }catch(final SQLException e){
            Log.e("Connect SQL", e.getMessage() + "\n" + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    private Connection connect() throws SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
}
