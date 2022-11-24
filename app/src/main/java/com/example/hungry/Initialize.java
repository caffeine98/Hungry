package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Initialize extends AppCompatActivity {
    TextView txtOutput;
    String file = "output.txt";
    String line = "";
    String data = "";
    FileInputStream fis;
    InputStreamReader isr;
    BufferedReader br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeFile();
        txtOutput = findViewById(R.id.textView2);
        fis = null;  //A FileInputStream obtains input bytes from a file in a file system
        try {
            fis = openFileInput(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        isr = new InputStreamReader(fis); //An InputStreamReader is a bridge from byte streams to character streams
        br = new BufferedReader(isr);    //Reads text from a character-input stream,
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(){
        String filename = "output.txt";
        String fileContents = "Ingredients:\n1 cup milk\n¾ cup sugar\n2 tablespoons unsweetened cocoa powder\n¼ teaspoon salt\n" +
                "3 egg yolk, lightly beaten\n2 ounces semisweet chocolate, chopped\n2 cups heavy cream\n1 teaspoon vanilla extract\n";
        fileContents+="Combine milk, sugar, cocoa powder, and salt in a saucepan over medium heat. Bring to a simmer, stirring constantly.\n" +
                "Place egg yolks into a small bowl. Gradually whisk in about 1/2 cup of hot milk mixture, then return to the saucepan. Heat and stir until thickened, but do not boil.\n" +
                "Remove from the heat and stir in chopped chocolate until melted.\n" +
                "Pour into a chilled bowl and refrigerate, stirring occasionally, until cold, about 2 hours.\n" +
                "Stir in cream and vanilla. Pour into an ice cream maker and freeze according to manufacturer's directions.";
        System.out.println(fileContents);
        FileOutputStream outputStream;  //Allow a file to be opened for writing
        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());    //FileOutputStream is meant for writing streams of raw bytes.
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readFile() throws IOException {
        //File read operation
        data = "";
        while((line = br.readLine())!= null){
            data+=line+"\n";
        }
        txtOutput.setText(data);
    }
}