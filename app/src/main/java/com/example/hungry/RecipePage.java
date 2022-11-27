package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecipePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
    }
    public void nextPage(View view){
        Intent intent = new Intent(this,page2.class);
        startActivity(intent);
    }
    public void finish(View view){
        finish();
    }
}