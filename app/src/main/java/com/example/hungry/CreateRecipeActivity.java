package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class CreateRecipeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int minCookTime = 0;
    int maxCookTime = 60;
    SeekBar minCookTimeSeekBar;
    SeekBar maxCookTimeSeekBar;
    TextView minCookTimeText;
    TextView maxCookTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        Spinner spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(
                this, R.array.recipe_category, android.R.layout.simple_spinner_item
        );
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);
        spinnerCategory.setOnItemSelectedListener(this);

        Spinner spinnerTags = (Spinner) findViewById(R.id.spinnerTags);
        ArrayAdapter<CharSequence> adapterTags = ArrayAdapter.createFromResource(
                this, R.array.recipe_tags, android.R.layout.simple_spinner_item
        );
        adapterTags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTags.setAdapter(adapterTags);
        spinnerTags.setOnItemSelectedListener(this);

        minCookTimeSeekBar = (SeekBar) findViewById(R.id.seekBarMinCookTime);
        maxCookTimeSeekBar = (SeekBar) findViewById(R.id.seekBarMaxCookTime);
        minCookTimeText = (TextView) findViewById(R.id.textViewMinCookTime);
        maxCookTimeText = (TextView) findViewById(R.id.textViewMaxCookTime);

        minCookTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                minCookTime = i;
                minCookTimeText.setText("Min Cook Time: " + minCookTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxCookTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                maxCookTime = i;
                maxCookTimeText.setText("Max Cook Time:" + maxCookTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickUpload(View view) {
        EditText recipeName = (EditText) findViewById(R.id.editTextRecipeName);
        Spinner spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        Spinner spinnerTags = (Spinner) findViewById(R.id.spinnerTags);
        EditText ingredients = (EditText) findViewById(R.id.editTextIngredients);
        EditText instructions = (EditText) findViewById(R.id.editTextMultiLineInstructions);

        if (recipeName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter a recipe name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (ingredients.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Ingredients field cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (instructions.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Instructions field cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        // Format every recipe text in a similar way to the code in Initialize.java
        String[] ingredientsList = ingredients.getText().toString().split(",");
        String[] instructionsList = instructions.getText().toString().split(System.lineSeparator());

        String fullRecipeText = "";
        fullRecipeText += "Ingredients:\n";
        fullRecipeText += "[" + recipeName.getText().toString() + "]\n";
        fullRecipeText += "[Category: " + spinnerCategory.getSelectedItem().toString() + "] ";
        fullRecipeText += "[Tags: " + spinnerTags.getSelectedItem().toString() + "] ";
        fullRecipeText += "[Duration:" + minCookTime + " to " + maxCookTime + " minutes]\n";
        for (int i = 0; i < ingredientsList.length; i++) {
            fullRecipeText += "* " + ingredientsList[i] + "\n";
        }
        fullRecipeText += "Instructions:\n";
        for (int i = 0; i < instructionsList.length; i++) {
            fullRecipeText += "* " + instructionsList[i] + "\n";
        }
        fullRecipeText += "The End.\n";

        // Write to output.txt, so it can be read while browsing recipes
        saveNewRecipe(fullRecipeText);
        finish();

        // FOR DEBUGGING
        //instructions.setText("");
        //instructions.setText(fullRecipeText);
        /*Toast.makeText(getApplicationContext(),spinnerCategory.getSelectedItem().toString() + " "
                + spinnerTags.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();*/
    }

    private void saveNewRecipe(String recipeText) {
        FileOutputStream outputStream;
        String fileName = "recipe.txt";

        try{
            outputStream = openFileOutput(fileName, Context.MODE_APPEND);
            outputStream.write(recipeText.getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(),"New recipe uploaded",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error uploading recipe",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBack(View view) {
        finish();
    }
}