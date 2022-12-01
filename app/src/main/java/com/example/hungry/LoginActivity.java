package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    // Define hardcoded login details
    final String USERNAME = "prochef98";
    final String PASSWORD = "chef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View view) {
        EditText username = (EditText) findViewById(R.id.editTextLoginUsername);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        if (!username.getText().toString().equals(USERNAME) || !password.getText().toString().equals(PASSWORD)) {
            Toast.makeText(getApplicationContext(),"Incorrect username/password entered", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Logging in...",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CreateRecipeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}