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
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Initialize extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    TextView txtOutput;
    Integer counter = 0, max = 0;
    String file = "output.txt";
    String line = "";
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file,false);
            outputStream.write(" ".getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        String filename = "output.txt";
        String fileContents = "Ingredients:\n1 cup milk\n¾ cup sugar\n2 tablespoons unsweetened cocoa powder\n¼ teaspoon salt\n" +
                "3 egg yolk, lightly beaten\n2 ounces semisweet chocolate, chopped\n2 cups heavy cream\n1 teaspoon vanilla extract\n";
        fileContents+="Steps:\nCombine milk, sugar, cocoa powder, and salt in a saucepan over medium heat. Bring to a simmer, stirring constantly.\n" +
                "Place egg yolks into a small bowl. Gradually whisk in about 1/2 cup of hot milk mixture, then return to the saucepan. Heat and stir until thickened, but do not boil.\n" +
                "Remove from the heat and stir in chopped chocolate until melted.\n" +
                "Pour into a chilled bowl and refrigerate, stirring occasionally, until cold, about 2 hours.\n" +
                "Stir in cream and vanilla. Pour into an ice cream maker and freeze according to manufacturer's directions.";
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
            if(line == "Ingredients:"){
                arrayList.add(data);
                data = "";
                max += 1;
            }
            data+=line+"\n";
        }
        txtOutput.setText(data);
    }
    public void next(View view){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Intent intent1 = new Intent(this, SearchResult.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("Search Text", bundle.getString("Search Text"));
        intent1.putExtras(bundle1);
        startActivity(intent1);
    }
    public void nextRecipe(View view){
        counter += 1;
        if(counter<max){
            txtOutput.setText(arrayList.get(counter));
        }else{
//            max = counter;
            counter=0;
            txtOutput.setText(arrayList.get(counter));
        }
    }
}