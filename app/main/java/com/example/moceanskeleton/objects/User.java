package com.example.moceanskeleton.objects;

public class User {

    // User variables.
    // Height in cm
    // Weight in kg
    private int id;
    private String username;
    private String password;
    private int height;
    private double weight;
    private double consumedCalories;
    private double allowedCalories;
    private int age;
    private Sex sex;

    // Constructor.
    public User(String username, String password, int height, double weight, int age, Sex sex) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.consumedCalories = 0;
        this.age = age;
        this.sex = sex;
        setAllowedCalories();
    }

    public User(int id, String username, String password, int height, double weight, int age, String sex){
        this.id = id;
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = Sex.valueOf(sex);
        setAllowedCalories();
    }

    // Getters and setters.
    public String getUsername() {

        return username;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeight() {

        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {

        return weight;
    }

    public int getId(){
        return id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getConsumedCalories() {

        return this.consumedCalories;
    }

    public void addCalories(double calories){
        this.consumedCalories += calories;
    }
    public void setConsumedCalories(int consumedCalories) {
        this.consumedCalories = consumedCalories;
    }

    public double getAllowedCalories() {
        setAllowedCalories();
        return this.allowedCalories;
    }

    private void setAllowedCalories() {
        double weightModifier = 9.99;
        double heightModifier = 6.25;
        double ageModifier = 4.92;
        int maleModifier = 5;
        int nonMaleModifier = 166;
        this.allowedCalories = (int)((weightModifier * this.weight) + (heightModifier * height) - (ageModifier * age) + maleModifier);
        if(this.sex != Sex.MALE){
            this.allowedCalories -= nonMaleModifier;
        }
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {

        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

}
