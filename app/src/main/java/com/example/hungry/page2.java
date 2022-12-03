package com.example.hungry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
    }
    public void finished(View view){
        finish();
    }
    public void goNext(View view){
        Intent intent = new Intent(this, comment.class);
        startActivity(intent);
    }
}