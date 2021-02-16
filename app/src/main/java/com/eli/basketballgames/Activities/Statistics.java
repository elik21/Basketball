package com.eli.basketballgames.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.eli.basketballgames.R;

public class Statistics extends AppCompatActivity {
 private EditText rebounds,assists,fouls,fielGoalP,threeGoalP,looseballs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        init();



    }

    private void init() {
        rebounds=findViewById(R.id.rebounds);
        assists=findViewById(R.id.assists);
        fouls=findViewById(R.id.fouls);
        fielGoalP=findViewById(R.id.twothrowP);
        threeGoalP=findViewById(R.id.threethrowsP);
        looseballs=findViewById(R.id.looseballs);
    }
}