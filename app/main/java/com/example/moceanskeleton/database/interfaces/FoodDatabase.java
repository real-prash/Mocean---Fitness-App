package com.example.moceanskeleton.database.interfaces;

import com.example.moceanskeleton.objects.Food;
import java.util.*;

public interface FoodDatabase {

    ArrayList<Food> getFoodList();

    Food getFoodByID(int foodID);

    Food insertFoodItem(Food food);

    Boolean removeFoodItem(Food food);

}
