package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSearchClick(View view){
        EditText editText = findViewById(R.id.SearchText);
        if(editText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter an ingredient.",Toast.LENGTH_SHORT).show();
            return;
        }
        String str = editText.getText().toString();
        Intent intent = new Intent(this, SearchResult.class);
        Bundle bundle = new Bundle();
        bundle.putString("Search Text", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onBrowseClick(View view){
        Intent intent = new Intent(this, Initialize.class);
        startActivity(intent);
    }

    public void onAddNewClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}