package com.example.moceanskeleton.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.database.interfaces.ExercisesDatabase;
import com.example.moceanskeleton.database.interfaces.FoodDatabase;
import com.example.moceanskeleton.database.interfaces.WorkoutsDatabase;
import com.example.moceanskeleton.database.utility.DBHelper;
import com.example.moceanskeleton.logic.handlers.UserLogicHandler;
import com.example.moceanskeleton.objects.User;

public class Login extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private UserLogicHandler userHandler;
    private FoodDatabase foodDatabase;
    private WorkoutsDatabase workoutsDatabase;
    private ExercisesDatabase exercisesDatabase;
    public static User currentUser;

    @SuppressLint("SourceLockedOrientationActivity") // Suppress warning for screen orientation request.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DBHelper.copyDatabaseToDevice(this);

        // Initialize the UserDatabaseStub and FoodDatabaseStub
        foodDatabase = Services.getFoodDatabase(true);
        workoutsDatabase = Services.getWorkoutsDatabase(true);
        userHandler = new UserLogicHandler(true);
        exercisesDatabase = Services.getExercisesDatabase(true);

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.login_button);
        Button createAccountButton = findViewById(R.id.create_account_button);

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (userHandler.authenticateUser(username, password)) {
                Log.i("LOGIN TESTING", "Username: " + username + " and Password: " + password);
                currentUser = userHandler.getUser(username);
                changeToHome();
            } else { // Handle the case when either username or password is empty.
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });

        createAccountButton.setOnClickListener(v -> {
            changeToCreateAccount();
        });
    }

    // Change from login page to Home page.
    public void changeToHome() {
        Intent changeToHome = new Intent(this, Home.class);
        startActivity(changeToHome);
    }

    // Change from login page to create account page.
    public void changeToCreateAccount() {
        Intent changeToCreateAccount = new Intent(this, CreateAccount.class);
        startActivity(changeToCreateAccount);
    }
}