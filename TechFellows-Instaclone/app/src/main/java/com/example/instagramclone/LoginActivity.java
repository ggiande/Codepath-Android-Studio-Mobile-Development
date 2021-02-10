package com.example.instagramclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity"; //Tag is used a var to know where we are for debugging
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity); // Sets the layout for the class

        if (ParseUser.getCurrentUser() != null){ // If an authenticated user is already cached, then we can send them straight to the main
            goMainActivity();
        }
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.etButton);
        btnSignUp = findViewById(R.id.etButtonSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Create User button has been clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                CreateUser(username, password);
            }
        });

        // Create the onClickListener handler for the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Log In Button has been clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }

    private void CreateUser(String username, String password) {
        Log.i(TAG, "Attempting to Create a new user: " + username);
        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(username);
        parseUser.setPassword(password);
        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                // start new main activity
                if(e != null) {
                    // better error handling
                    Log.e(TAG, "User creation was unsuccessful", e);
                    Toast.makeText(LoginActivity.this, "Issue with Sign Up", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Navigate to the main activity if the user's credentials are created successfully
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "User creation has been authenticated and signed in.");
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to Log In User: " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    // better error handling
                    Log.e(TAG, "User authentication failed", e);
                    Toast.makeText(LoginActivity.this, "Issue with Login", Toast.LENGTH_SHORT).show();
                    return;
                }
                // navigate to the main activity if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "User has been authenticated and signed in.");
            }
        });
    }

    private void goMainActivity() {
        // Create the intent to go to main activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
