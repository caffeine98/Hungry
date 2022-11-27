package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;//upvoted vs recent spinner
    TextView comment1;//comment 1 content view
    TextView comment2;//comment 2 content view
    CheckBox like1;//like checkbox1
    CheckBox like2;//like checkbox2
    TextView pTime1;//posted time 1 view
    TextView pTime2;//posted time 2 view
    EditText commentNew;// the new comment to be added
    ArrayList<String> comments;//all the comments on a specific recipe
    ArrayList<Integer> likes;//the number of likes at index i corresponds to the comment at index i in comments
    ArrayList<Integer> postTimes;//the posted time for all comments


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up spinner
        spinner = findViewById(R.id.spinner);
        String[] sValues = {"Most Recent", "Most Upvoted"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //get view Id's
        comment1 = findViewById(R.id.comment1);
        comment2 = findViewById(R.id.comment2);
        like1 = findViewById(R.id.likeCheck1);
        like2 = findViewById(R.id.likeCheck2);
        pTime1 = findViewById(R.id.postTime1);
        pTime2 = findViewById(R.id.postTime2);
        commentNew = findViewById(R.id.addComment);
        //initialize array lists
        comments = new ArrayList<String>();
        likes = new ArrayList<Integer>();
        postTimes = new ArrayList<Integer>();
    }

    //post your comment
    public void post(View view){
        //save user input to array list
        String com = commentNew.getText().toString();
        comments.add(com);
        likes.add(0);
        postTimes.add(0);
        //display comment
        comment1.setText(com);
        like1.setChecked(false);
        pTime1.setText("Posted 0 hours ago");
        //clear input box
        commentNew.setText("");
    }

    //filter button
    public void filter(View v){
        String spinVal = spinner.getSelectedItem().toString();
        if(spinVal == "Most Recent")
            displayRecent();
        else
            displayLiked();
    }

    //most recent
    public void displayRecent(){
        //most recent = last two comments of arraylist
        int size = comments.size();
        String com1 = comments.get(size - 1);//most recent comment
        comment1.setText(com1);
        String lik1 = likes.get(size - 1).toString();//number of likes
        like1.setText(lik1);
        String post1 = postTimes.get(size - 1).toString();//posted time
        pTime1.setText(post1);
        //second most recent comment
        String com2 = comments.get(size - 2);//2nd most recent comment
        comment2.setText(com2);
        String lik2 = likes.get(size - 2).toString();//number of likes
        like2.setText(lik2);
        String post2 = postTimes.get(size - 2).toString();//posted time
        pTime2.setText(post2);
    }

    //most liked
    public void displayLiked(){
        int most = 0;//most likes index
        int second = 0;//second most likes index
        int current = 1;//current likes index
        int size = likes.size();//size of likes
        //get most liked and second most liked
        while(current < size)
        {
            if(likes.get(current) >= likes.get(most))
            {
                second = most;
                most = current;
            }
            current++;
        }
        String com1 = comments.get(most);//most recent comment
        comment1.setText(com1);
        String lik1 = likes.get(most).toString();//number of likes
        like1.setText(lik1);
        String post1 = postTimes.get(most).toString();//posted time
        pTime1.setText(post1);
        //second most recent comment if more than one comment
        if(second != most) {
            String com2 = comments.get(second);//2nd most recent comment
            comment2.setText(com2);
            String lik2 = likes.get(second).toString();//number of likes
            like2.setText(lik2);
            String post2 = postTimes.get(second).toString();//posted time
            pTime2.setText(post2);
        }

    }
    //increase likes
    public void increaseLike(){

    }

    public void save(){
        String filename = "output.txt";
        String fileContent = "\n";
        FileOutputStream outputStream;//Allow a file to be opened for writing

        try{
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContent.getBytes());
            outputStream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}