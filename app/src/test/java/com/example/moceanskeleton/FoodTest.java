package com.example.moceanskeleton;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;
import com.example.moceanskeleton.objects.Food;

public class FoodTest {

    @Before
    public void setup(){
        System.out.println("Starting test for Food");
    }

    @Test
    public void testChangeFoodName() {
        System.out.println("\nStarting testChangeFoodName");
        Food food = new Food(1);
        food.changeFoodName("Test Food");
        assertEquals("Test Food", food.getFoodName());
        System.out.println("Finished testChangeFoodName");
    }

    @Test
    public void testFoodConstructorWithNoDate() {
        System.out.println("\nStarting testFoodConstructorWithNoDate");
        Food food = new Food(409, "Chocolate Cake", "Empty Calories", 6, 1, 400, 0, 0, 0, 418); //TODO Set appropriate carbs/fats/protein
        assertEquals(409, food.getFoodID());
        assertEquals("Chocolate Cake", food.getFoodName());
        assertEquals("Empty Calories", food.getFoodGroup());
        assertEquals(1.0, food.getFoodQuantity(), 0);
        assertEquals(400, food.getFoodCalories(), 0);
        assertNull(food.getDateEaten());
        assertEquals(418, food.getImageResource());
        System.out.println("Finished testFoodConstructorWithNoDate");
    }

    @Test
    public void testFoodConstructorWithDate() {
        System.out.println("\nStarting testFoodConstructorWithDate");
        Date date = new Date();
        Food food = new Food(7, "Pasta", "Grains", 2, 2, 220, 0, 0, 0, date, 404);
        assertEquals(7, food.getFoodID());
        assertEquals("Pasta", food.getFoodName());
        assertEquals("Grains", food.getFoodGroup());
        assertEquals(2.0, food.getFoodQuantity(), 0);
        assertEquals(220, food.getFoodCalories(), 0);
        assertEquals(date, food.getDateEaten());
        assertEquals(404, food.getImageResource());
        System.out.println("Finished testFoodConstructorWithDate");
    }

    @Test
    public void testSettersAndGetters() {
        System.out.println("\nStarting testSettersAndGetters");
        Food food = new Food(1);
        food.setFoodName("Banana");
        food.setFoodGroup("Fruit");
        food.setFoodQuantity(1);
        food.setFoodCalories(105);
        food.setFoodID(2);
        food.setImageResource(999);
        assertEquals("Banana", food.getFoodName());
        assertEquals("Fruit", food.getFoodGroup());
        assertEquals(1, food.getFoodQuantity(), 0);
        assertEquals(105, food.getFoodCalories(), 0);
        assertEquals(2, food.getFoodID());
        assertEquals(999, food.getImageResource());
        System.out.println("Finished testSettersAndGetters");
    }

}

