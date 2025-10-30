# Architecture
## Presentation Layer
LegsWorkoutActivity
- Displays the leg specific sample workouts

NewWorkoutsActivity
- Displays page for creating a new workouts

PreviousWorkoutsActivity
- Displays page with previously user added workouts

PullWorkoutActivity
- Displays the pull specific sample workouts

PushWorkoutActivity
- Displays the push specific sample workouts

SampleWorkoutsActivity
- Displays page to select specific type of sample workouts

SampleWorkoutsAdapter
- Allows SampleWorkouts to be displayed in a list view

TrackWorkoutsActivity
- Main page for all workout related activities

WorkoutAdapter
- Allows other workouts to be displayed in a list view

CreateFood
- Displays page for creating a new food item in the database

FoodSearch
- Displays page with search view for searching database for FoodSearch

Home
- Main page with calorie status and food add/search buttons

CreateAccount
- Displays page for user to create a new account

Login
- Displays login page

Profile
- Displays page with all user info

## Logic Layer
WorkoutManager
- Handles workout related logic

FoodHandler
- Handles food/calorie related logic

UserLogicHandler
- Handles user related logic

## Persistence Layer
ExercisesDatabase
- Exercise database interface

ExercisesDatabaseHSQLDB
- HSQLDB specific database

ExercisesDatabaseStub
- Exercises stub database

WorkoutDatabase
- Workout database interface

WorkoutDatabaseHSQLDB
- HSQLDB specific database

WorkoutDatabaseStub
- Workout stub database

FoodDatabase
- Food database interface

FoodDatabseHSQLDB
- HSQLDB specific database

FoodDatabaseStub
- Food stub database

FoodGroupDatabase
- FoodGroup database interface

FoodGroupDatbaseHSQLDB
- HSQLDB specific database

FoodGroupDatabaseStub
- FoodGroup stub database

UserDatabase
- User database interface

UserDatabaseStub
- User stub database

## Objects

Exercise
- Specific exercise with sets and reps

Workout
- Contains many Exercises

Food
- Food item with nutritional information

FoodGroup
- Specific food group

Sex
- User sex information

User
- All user information
