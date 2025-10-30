package com.example.moceanskeleton.objects;

import java.util.Date;

public class Food {

    private int imageResource;
    private String foodName;
    private String foodGroup;
    private int foodGroupID;
    private int foodQuantity;
    private int foodCalories;
    private int foodID;
    private Date date;
    private int carbs;
    private int fats;
    private int protein;

    // Default constructor, must set unique foodID.
    public Food(final int foodID) {
        this.foodName = null;
        this.foodGroup = null;
        this.foodQuantity = 0;
        this.foodCalories = 0;
        this.carbs = 0;
        this.fats = 0;
        this.protein = 0;
        this.foodID = foodID;
        this.date = null;
    }

    // Constructor for a food item in our food dataset, no date associated with it.
    public Food(int foodID, String foodName, String foodGroup, int foodGroupID, int foodQuantity, int foodCalories, int carbs, int fats, int protein, int imageResource) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodGroup = foodGroup;
        this.foodGroupID = foodGroupID;
        this.foodQuantity = foodQuantity;
        this.foodCalories = foodCalories;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.imageResource = imageResource;
    }

    // Constructor for a food item that a person is logging, need date it was eaten.
    public Food(int foodID, String foodName, String foodGroup, int foodGroupID, int foodQuantity, int foodCalories, int carbs, int fats, int protein, Date date, int imageResource) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodGroup = foodGroup;
        this.foodGroupID = foodGroupID;
        this.foodQuantity = foodQuantity;
        this.foodCalories = foodCalories;
        this.imageResource = imageResource;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.date = date;
    }

    // Method for testing click listener with food search view.
    public void changeFoodName(String text) {
        this.foodName = text;
    }

    // Getters and setters
    public int getFoodID() {

        return foodID;
    }

    public String getFoodName() {

        return foodName;
    }

    public String getFoodGroup() {

        return foodGroup;
    }

    public int getFoodGroupID(){
        return foodGroupID;
    }
    public int getFoodQuantity() {

        return foodQuantity;
    }

    public int getFoodCalories() {

        return foodCalories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFats(){
        return fats;
    }

    public int getProtein(){
        return protein;
    }

    public Date getDateEaten() {

        return date;
    }

    public int getImageResource() {

        return imageResource;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public void setFoodCalories(int foodCalories) {
        this.foodCalories = foodCalories;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

}
