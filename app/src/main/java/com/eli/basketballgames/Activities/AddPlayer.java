package com.eli.basketballgames.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eli.basketballgames.Models.Game;
import com.eli.basketballgames.Models.Player;
import com.eli.basketballgames.R;

import java.util.ArrayList;


public class AddPlayer extends AppCompatActivity {
    public static final String EXTRA_ID="EXTRA_ID";
ArrayList<Game> players;
    EditText name, team, height, weight;
    Game player;
    Button edit;
    int pos=0;
    String name1;
    Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        players=new ArrayList<Game>();
        click=findViewById(R.id.addP);
        setTitle("Add Player");
       init();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addToList();
            }
        });
     //  passToList();
Intent intent=getIntent();
if(intent.hasExtra(EXTRA_ID)){
    EditPlayer();
}


    }
    public void init(){
        name = findViewById(R.id.name2);
        team = findViewById(R.id.team);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        edit=findViewById(R.id.editP);

    }
    public void addToList(){
     String name1=name.getText().toString();
        Intent intent=getIntent();
        intent.putExtra("name",name1);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            intent.putExtra(EXTRA_ID,id);
        }



        setResult(RESULT_OK,intent);
        finish();
    }
    public void passToList(){
        Intent intent=new Intent(AddPlayer.this,BBGamesList.class);
        intent.putExtra("player",player);
    }
    public void EditPlayer(){
        Intent intent=getIntent();
    name.setText(intent.getStringExtra("name"));
        Toast.makeText(this,intent.getStringExtra("name"),Toast.LENGTH_SHORT);
    }


}