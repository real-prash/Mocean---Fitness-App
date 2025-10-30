package com.example.moceanskeleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.moceanskeleton.database.interfaces.FoodDatabase;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.logic.handlers.FoodHandler;
import com.example.moceanskeleton.objects.Food;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FoodHandlerIT {
    private FoodHandler foodHandler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting test for FoodHandler");
        this.tempDB = TestUtils.copyDB();
        foodHandler = new FoodHandler(true);
    }

    @Test
    public void testGetFoodList() {
        System.out.println("\nStarting testGetFoodList");
        ArrayList<Food> myFoodList = foodHandler.getFoodList();

        assertNotNull(myFoodList);
        assertEquals(9, myFoodList.size());
        System.out.println("Finished testGetFoodList");
    }

    @Test
    public void testAddFood() {
        System.out.println("\nStarting testAddFood");
        foodHandler.addFood("Peach", "Fruits and Vegetables", 50, 1, 15, 1, 1);
        ArrayList<Food> myFoodList = foodHandler.getFoodList();
        assertEquals(10, myFoodList.size());
        assertEquals("Peach", foodHandler.getFoodByID(100).getFoodName());
        foodHandler.removeFoodItem(foodHandler.getFoodByID(100));

        System.out.println("Finished testAddFood");
    }

    @Test
    public void testValidInput(){
        assertTrue(foodHandler.validFoodInput("FoodItem", "100", "100", "20", "20", "20"));
    }

    @Test
    public void testConvertGroupToID(){
        assertEquals(1, foodHandler.convertGroupToID("Fruits and Vegetables"));
        assertEquals(2, foodHandler.convertGroupToID("Grains"));
        assertEquals(3, foodHandler.convertGroupToID("Protein"));
        assertEquals(4, foodHandler.convertGroupToID("Dairy"));
        assertEquals(5, foodHandler.convertGroupToID("Oils"));
        assertEquals(6, foodHandler.convertGroupToID("Empty Calories"));
    }

    @Test
    public void testGetCaloriesByFoodID(){
        int appleCals = foodHandler.getCaloriesByFoodID(1);
        assertEquals(52, appleCals);
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
        Services.clean();
    }
}