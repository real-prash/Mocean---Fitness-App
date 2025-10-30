package com.example.moceanskeleton.database.stubs;

import com.example.moceanskeleton.database.interfaces.FoodDatabase;
import com.example.moceanskeleton.objects.Food;
import com.example.moceanskeleton.R;
import java.util.ArrayList;

public class FoodDatabaseStub implements FoodDatabase {
    private static ArrayList<Food> FOOD_LIST;

    public FoodDatabaseStub() {
        FOOD_LIST = new ArrayList<>();
        FOOD_LIST.add(new Food(1, "Apple", "Fruits and Vegetables",1, 1, 52, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(2, "Banana", "Fruits and Vegetables",1, 1, 89, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(3, "Carrot", "Fruits and Vegetables", 1,1, 41, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(4, "Orange", "Fruits and Vegetables", 1,1, 55, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(5, "Cucumber", "Fruits and Vegetables", 1, 1, 66, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(6, "Celery", "Fruits and Vegetables", 1, 1, 23, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(7, "Watermelon", "Fruits and Vegetables", 1, 1, 34, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(8, "Grapes", "Fruits and Vegetables", 1, 1, 46, 0, 0, 0, R.drawable.add_food_icon));
        FOOD_LIST.add(new Food(9, "Pineapple", "Fruits and Vegetables",1,  1, 80, 0, 0, 0, R.drawable.add_food_icon));
    }

    @Override
    public ArrayList<Food> getFoodList() {

        return new ArrayList<>(FOOD_LIST);
    }


    public Food getFoodByID(int foodID) {
        for (int i=0; i<FOOD_LIST.size(); i++) {
            if (FOOD_LIST.get(i).getFoodID() == foodID) {
                return FOOD_LIST.get(i);
            }
        }

        return null;
    }

    public Boolean removeFoodItem(Food food) {
        return FOOD_LIST.remove(food);
    }

    @Override
    public Food insertFoodItem(Food foodItem) {
        if (FOOD_LIST.contains(foodItem)) {

            return null;
        }
        else {
            FOOD_LIST.add(foodItem);

            return foodItem;
        }
    }
}
