package com.example.moceanskeleton.presentation;

import com.example.moceanskeleton.logic.Services;
import com.example.moceanskeleton.database.interfaces.UserDatabase;
import com.example.moceanskeleton.logic.handlers.UserLogicHandler;
import com.example.moceanskeleton.objects.User;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.moceanskeleton.R;
import com.example.moceanskeleton.objects.Sex;

public class CreateAccount extends AppCompatActivity {

    private UserLogicHandler userHandler = new UserLogicHandler(true);
    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button returnButton;
        Button submitButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize the UserDatabaseStub.
        userDatabase = Services.getUserDatabase(true);

        returnButton = findViewById(R.id.return_button);
        submitButton = findViewById(R.id.create_account_submit_button);

        returnButton.setOnClickListener(v -> {
            changeToLogin();
        });

        submitButton.setOnClickListener(v -> {

            // Retrieve values from text fields.
            EditText usernameEditText = findViewById(R.id.username_edittext);
            EditText passwordEditText = findViewById(R.id.password_edittext);
            EditText heightEditText = findViewById(R.id.height_edittext);
            EditText weightEditText = findViewById(R.id.weight_edittext);
            EditText ageEditText = findViewById(R.id.age_edittext);

            final String username = Services.getTrimmedString(usernameEditText);
            final String password = Services.getTrimmedString(passwordEditText);
            final String heightStr = Services.getTrimmedString(heightEditText);
            final String weightStr = Services.getTrimmedString(weightEditText);
            final String ageStr = Services.getTrimmedString(ageEditText);

            // Perform validation checks.
            // Display error message if any field is empty.
            if (username.isEmpty() || password.isEmpty() || weightStr.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();

                return;
            }
            else if (username.length() > 50 || password.length() > 50 || heightStr.length() > 6 || weightStr.length() > 6 || ageStr.length() > 3) {
                Toast.makeText(this, "Values are too large, try again...", Toast.LENGTH_SHORT).show();

                return;
            }

            // Parse weight and age to integers.
            int height, age;
            double weight;

            try {
                height = Integer.parseInt(heightStr);
                weight = Double.parseDouble(weightStr);
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) { // Display error message if weight or age is not a valid number
                Toast.makeText(this, "Height, Weight, and Age must be valid numbers!", Toast.LENGTH_SHORT).show();

                return;
            }

            // Get the selected sex.
            RadioGroup sexRadioGroup = findViewById(R.id.sex_radio_group);
            String sexStr = getSelectedSex(sexRadioGroup);
            Sex sex = Sex.valueOf(sexStr.toUpperCase());

            // Create a new User object
            User newUser = new User(username, password, height, weight, age, sex);

            if (!userHandler.userExists(username)) { // If the account creation is successful.
                userDatabase.addUser(newUser);
                Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
                changeToLogin();
            } else { // If the username is taken.
                Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Go back to login page.
    public void changeToLogin() {
        Intent changeToLogin = new Intent(this, Login.class);
        startActivity(changeToLogin);
    }

    private String getSelectedSex(RadioGroup sexRadioGroup) {
        int selectedRadioButtonId = sexRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId == R.id.male_radio_button) {
            return "Male";
        } else if (selectedRadioButtonId == R.id.female_radio_button) {
            return "Female";
        } else {
            return "Intersex";
        }
    }

}
