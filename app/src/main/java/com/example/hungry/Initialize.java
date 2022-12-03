package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Initialize extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    TextView txtOutput;
    Integer counter = 0, max = 0;
    String file = "baseline.txt";
    String line = "";
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);

        /*FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file,false);
            outputStream.write(" ".getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("baseline.txt"));
//            if (br.readLine() == null)
//                writeFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtOutput = findViewById(R.id.InitilizingText);

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile() throws IOException {
        FileOutputStream outputStream;  //Allow a file to be opened for writing
        String filename = "baseline.txt";
        String fileContents = "Ingredients:\n" +
                "1 cup milk\n" +
                "¾ cup sugar\n" +
                "2 tablespoons unsweetened cocoa powder\n" +
                "¼ teaspoon salt\n" +
                "3 egg yolk, lightly beaten\n" +
                "2 ounces semisweet chocolate, chopped\n" +
                "2 cups heavy cream\n" +
                "1 teaspoon vanilla extract\n" +
                "Steps:\nCombine milk, sugar, cocoa powder, and salt in a saucepan over medium heat. Bring to a simmer, stirring constantly.\n" +
                "Place egg yolks into a small bowl. Gradually whisk in about 1/2 cup of hot milk mixture, then return to the saucepan. Heat and stir until thickened, but do not boil.\n" +
                "Remove from the heat and stir in chopped chocolate until melted.\n" +
                "Pour into a chilled bowl and refrigerate, stirring occasionally, until cold, about 2 hours.\n" +
                "Stir in cream and vanilla. Pour into an ice cream maker and freeze according to manufacturer's directions.\n" +
                "The End.\n";
        fileContents+= "Ingredients:\n" +
                "1 (16 ounce) package uncooked rotini pasta\n" +
                "1 (16 ounce) bottle Italian salad dressing\n" +
                "2 cucumbers, chopped\n" +
                "6 tomatoes, chopped\n" +
                "1 bunch green onions, chopped\n" +
                "4 ounces grated Parmesan cheese\n" +
                "1 tablespoon Italian seasoning" +
                "Steps:\n" +
                "Bring a large pot of lightly salted water to a boil. Place pasta in the pot, cook for 8 to 12 minutes, until al dente, and drain.\n" +
                "Toss cooked pasta with Italian dressing, cucumbers, tomatoes, and green onions in a large bowl. Mix Parmesan cheese and Italian seasoning in a small bowl, and gently mix into the salad.\n" +
                "Cover, and refrigerate for at least 30 minutes before serving.\n" +
                "The End.\n";
        fileContents+="Ingredients:\n" +
                "1 lb chicken breast, cut into chunks\n" +
                "1 teaspoon garlic powder\n" +
                "1/2 teaspoon salt\n" +
                "1/2 teaspoon pepper\n" +
                "1 tablespoon olive oil\n" +
                "3 cloves garlic, minced\n" +
                "3/4 cup long grain white rice\n" +
                "2 1/2 cups Progresso™ chicken broth\n" +
                "2 cups baby spinach\n" +
                "2 tablespoons heavy cream" +
                "Steps:\n" +
                "Toss the chicken breast with garlic powder, salt, and pepper. Heat olive oil in a 10-inch skillet over medium heat. Add chicken breast and cook until browned, stirring often, about 3 minutes. Add garlic to skillet and cook for 30 seconds more.\n" +
                "Stir in rice and chicken broth. Bring to a boil, reduce to a simmer, and cover. Cook for 20 minutes, stirring occasionally.\n" +
                "Remove pan from heat and place baby spinach on top of the rice. Cover and let sit for 5 minutes.\n" +
                "Remove lid and stir well. Stir in cream and serve.\n" +
                "The End.\n";
        System.out.println(fileContents);
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(fileContents.getBytes());    //FileOutputStream is meant for writing streams of raw bytes.
        outputStream.close();
    }
    public void readFile() throws IOException {
        FileInputStream fis = openFileInput(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        //File read operation
        data = "";
        while((line = br.readLine())!= null){
            data+=line+"\n";
            if(line.equals("The End.")){
                arrayList.add(data);
                data = "";
                max += 1;
            }
        }
        txtOutput.setText(data);
    }
    public void next(View view){
        finish();
    }
    public void nextRecipe(View view){
        counter += 1;
        if(counter<max){
            txtOutput.setText(arrayList.get(counter));
        }else{
            counter=0;
            txtOutput.setText(arrayList.get(counter));
        }
    }
}