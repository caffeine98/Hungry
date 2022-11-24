package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtOutput;
    String file = "output.txt";
    FileInputStream fis;
    InputStreamReader isr;
    BufferedReader br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        readFile();
    }
    public void writeFile() throws IOException, JSONException {
        ArrayList<String> IngredientList = new ArrayList<>();
        IngredientList.add("1 cup milk");
        IngredientList.add("¾ cup sugar");
        IngredientList.add("2 tablespoons unsweetened cocoa powder");
        IngredientList.add("¼ teaspoon salt");
        IngredientList.add("3 egg yolk, lightly beaten");
        IngredientList.add("2 ounces semisweet chocolate, chopped");
        IngredientList.add("2 cups heavy cream");
        IngredientList.add("1 teaspoon vanilla extract");
        ArrayList<String> StepsList = new ArrayList<>();
        StepsList.add("Combine milk, sugar, cocoa powder, and salt in a saucepan over medium heat. Bring to a simmer, stirring constantly.");
        StepsList.add("Place egg yolks into a small bowl. Gradually whisk in about 1/2 cup of hot milk mixture, then return to the saucepan. Heat and stir until thickened, but do not boil.");
        StepsList.add("Remove from the heat and stir in chopped chocolate until melted.");
        StepsList.add("Pour into a chilled bowl and refrigerate, stirring occasionally, until cold, about 2 hours.");
        StepsList.add("Stir in cream and vanilla. Pour into an ice cream maker and freeze according to manufacturer's directions.");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Ingredient", IngredientList);
        jsonObject.put("Enroll_No", StepsList);
        String userString = jsonObject.toString();
        File file = new File(this.getFilesDir(),"recipe.json");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }
    public void readFile(){
        File file = new File(this.getFilesDir(),"recipe.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        try{
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}