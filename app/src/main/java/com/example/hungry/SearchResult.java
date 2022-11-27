package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        intent = getIntent();
        bundle = intent.getExtras();
        printSearch();
    }
    public void printSearch(){
        String str = bundle.getString("Search Text");
        TextView textView = findViewById(R.id.textView);
        textView.setText(str);
    }
    public void back(View view){
        finish();
    }

    public void recipe1(View view){
        Intent intent = new Intent(this,RecipePage.class);
        startActivity(intent);
    }
}