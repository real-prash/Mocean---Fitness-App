package com.example.moceanskeleton.logic.handlers;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.database.interfaces.FoodDatabase;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.objects.Food;
import com.example.moceanskeleton.objects.FoodGroup;

import java.util.ArrayList;

public class FoodHandler {

    private FoodDatabase foodDatabase;
    private FoodGroup[] foodGroups;

    public FoodHandler(boolean forProd) {
        foodDatabase = Services.getFoodDatabase(forProd);
        foodGroups = FoodGroup.values();
    }

    public void removeFoodItem(Food item){
        foodDatabase.removeFoodItem(item);
    }

    public Food getFoodByID(int foodID) {
        return foodDatabase.getFoodByID(foodID);
    }

    public int getCaloriesByFoodID(int foodID) {
        return getFoodByID(foodID).getFoodCalories();
    }

    public ArrayList<Food> getFoodList(){
        return foodDatabase.getFoodList();
    }

    public void addFood(String foodName, String foodGroup, int foodCalories, int foodQuantity, int foodCarbs, int foodFats, int foodProtein) {
        int groupID = convertGroupToID(foodGroup);
        Food myFood = new Food(100, foodName, foodGroup, groupID, foodQuantity, foodCalories, foodCarbs, foodFats, foodProtein, R.drawable.add_food_icon);
        foodDatabase.insertFoodItem(myFood);
    }

    public int convertGroupToID(String group) {
        for (int i = 0; i < foodGroups.length; i++) {
            if (foodGroups[i].toString().replace("_", " ").equals(group)) {
                return i + 1; //Return to match the ID in the DB
            }
        }
        return 0;
    }

    public boolean validFoodInput(String foodName, String foodQuantity, String foodCals, String carbs, String fats, String protein){
        return !(foodName.isEmpty() || foodQuantity.isEmpty() || foodCals.isEmpty()
                || carbs.isEmpty() || fats.isEmpty() || protein.isEmpty());
    }

}
