package com.eli.basketballgames.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eli.basketballgames.Adapters.playersAdapter;
import com.eli.basketballgames.Models.PlayerViewModel;
import com.eli.basketballgames.Models.Player;
import com.eli.basketballgames.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PlayersList extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    public static final int ADD_PlAYER=1;
    public static final int Edit_PlAYER=2;
private ArrayList<Player> players1;
playersAdapter pAdapter;
Intent intent;
FloatingActionButton flButton;
private Player newPlayers;
 RecyclerView rv;
 Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palyers_list);

        initRecyclerView();
        setpAdapter();
        getData();
        DeleteOnSwipe();
        addPlayers();


    }



    public void getData() {
        playerViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getApplication())).get(PlayerViewModel.class);
        playerViewModel.getAllPlayers().observe(this,new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                pAdapter.setPlayers(players);
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
                playerViewModel.delete(pAdapter.getPlayerAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(rv);
    }



    public void initRecyclerView(){
        flButton=findViewById(R.id.addbtn);
        players1=new ArrayList<Player>();
        rv = (RecyclerView) findViewById(R.id.rview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setVisibility(View.VISIBLE);
        intent=getIntent();
        newPlayers= (Player) intent.getSerializableExtra("player");
    }
    public void addPlayers(){
        flButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PlayersList.this,AddPlayer.class);
                startActivityForResult(intent,ADD_PlAYER);
            }
        });

    }
    public void setpAdapter(){
        pAdapter=new playersAdapter();
        rv.setAdapter(pAdapter);
        pAdapter.setOnItemClickListenner(new playersAdapter.OnItemClickLister() {
            @Override
            public void onClick(Player player) {
                Intent intent=new Intent(PlayersList.this,AddPlayer.class);
                intent.putExtra("name",player.getName());
                intent.putExtra(AddPlayer.EXTRA_ID,player.getId());
                startActivityForResult(intent,Edit_PlAYER);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_PlAYER&&resultCode==RESULT_OK){
           String name=data.getStringExtra("name");
           Player player= new Player(name);
           playerViewModel.insert(player);
           Toast.makeText(PlayersList.this,"Note saved",Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==Edit_PlAYER&&resultCode==RESULT_OK) {
            String name=data.getStringExtra("name");
            Toast.makeText(PlayersList.this,"Player updated",Toast.LENGTH_SHORT).show();
            int id= data.getIntExtra(AddPlayer.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(PlayersList.this,"The Player  is not updated",Toast.LENGTH_SHORT).show();
            }
            Player player= new Player(name);
            player.setId(id);
            playerViewModel.update(player);
        }
        else {
            Toast.makeText(PlayersList.this,"Note wasn't sent",Toast.LENGTH_SHORT).show();
        }
    }
}
