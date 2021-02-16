package com.eli.basketballgames.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.eli.basketballgames.Adapters.GamesAdapter;
import com.eli.basketballgames.Models.Game;
import com.eli.basketballgames.Models.GamesViewModel;
import com.eli.basketballgames.DataBase.PlayerDataBase;
import com.eli.basketballgames.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BBGamesList extends AppCompatActivity {


    private GamesViewModel gameViewModel;
    public static final int ADD_PlAYER=1;
    public static final int Edit_PlAYER=2;
    private ArrayList<Game> players1;
    GamesAdapter pAdapter;
    Intent intent;
    FloatingActionButton flButton;
    private Game newPlayers;
    RecyclerView rv;
    Button click;
    SearchView searchGames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_b_games_list);
        initRecyclerView();
        setpAdapter();
        getData();
        DeleteOnSwipe();
        addPlayers();
    }
    public void getData() {
        gameViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getApplication())).get(GamesViewModel.class);
        gameViewModel.getAllPlayers().observe(this,new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                if(games == null)
                    return;
                pAdapter.setPlayers(games);
                searchGames.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        getQuery(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        getQuery(newText);
                        return false;
                    }
                });
                pAdapter.notifyDataSetChanged();
            }
        });

    }
    public void getQuery(String name) {
        PlayerDataBase.getInstance(getApplication()).gameDao().search(name).observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                pAdapter.setPlayers(games);
                pAdapter.notifyDataSetChanged();
            }
        });

    }
    public void DeleteOnSwipe() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                gameViewModel.delete(pAdapter.getPlayerAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(rv);
    }



    public void initRecyclerView(){
        flButton=findViewById(R.id.floating);
        searchGames=findViewById(R.id.searchGames);

        rv = (RecyclerView) findViewById(R.id.bb_games);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setVisibility(View.VISIBLE);
        intent=getIntent();
       newPlayers= (Game) intent.getSerializableExtra("player");
    }
    public void addPlayers(){
        flButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BBGamesList.this,AddPlayer.class);
                startActivityForResult(intent,ADD_PlAYER);
            }
        });

    }
    public void setpAdapter(){
        pAdapter=new GamesAdapter();
        rv.setAdapter(pAdapter);
        pAdapter.setOnItemClickListenner(new GamesAdapter.OnItemClickLister() {
            @Override
            public void onClick(Game game) {
                Intent intent=new Intent(BBGamesList.this,AddPlayer.class);
                intent.putExtra("name",game.getHostTeam());
                intent.putExtra(AddPlayer.EXTRA_ID,game.getId());
                startActivityForResult(intent,Edit_PlAYER);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_PlAYER&&resultCode==RESULT_OK){
            String name=data.getStringExtra("name");
            Game player= new Game(name,name);
            gameViewModel.insert(player);
            Toast.makeText(BBGamesList.this,"Note saved",Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==Edit_PlAYER&&resultCode==RESULT_OK) {
            String name=data.getStringExtra("name");
            Toast.makeText(BBGamesList.this,"Player updated",Toast.LENGTH_SHORT).show();
            int id= data.getIntExtra(AddPlayer.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(BBGamesList.this,"The Player  is not updated",Toast.LENGTH_SHORT).show();
            }
            Game player= new Game(name,name);
            player.setId(id);
            gameViewModel.update(player);
        }
        else {
            Toast.makeText(BBGamesList.this,"Note wasn't sent",Toast.LENGTH_SHORT).show();
        }
    }
}