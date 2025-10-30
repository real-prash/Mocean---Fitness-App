# Architecture
## Presentation Layer
NewWorkoutsActivity
- Displays page for creating a new workouts

PreviousWorkoutsActivity
- Displays page with previously user added workouts

TrackWorkoutsActivity
- Main page for all workout related activities

WorkoutExercises
- Displays page with exercises contained within a workout

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

UpdateInfo
- Displays page where user can update their info

WorkoutAdapter
- Allows workouts to be displayed properly from stubs/database

ExerciseAdapter
- Allows exercises to be displayed properly from stubs/database

RecyclerViewAdapter
- Handles logic related to the displaying foods in a recycler view format

SampleWorkoutsAdapter
- Allows premade workouts to be displayed properly

## Logic Layer
WorkoutHandler
- Handles workout related logic

ExerciseHandler
- Handles exercise related logic

FoodHandler
- Handles food/calorie related logic

UserLogicHandler
- Handles user related logic

Services
- General services logic class

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

UserDatabase
- User database interface

UserDatabaseHSQLDB
- HSQLDB specific database

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
