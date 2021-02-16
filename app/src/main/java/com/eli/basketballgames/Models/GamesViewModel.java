package com.eli.basketballgames.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eli.basketballgames.repositories.GameRepository;

import java.util.List;

public class GamesViewModel  extends AndroidViewModel {
    private GameRepository repository;
    private LiveData<List<Game>> allPlayers;
    public GamesViewModel(@NonNull Application application) {
        super(application);
        repository=new GameRepository(application);
        allPlayers=repository.getAllPlayers();
    }
    public void insert(Game game){
        repository.insert(game);

    }
    public void update(Game game){
        repository.update(game);

    }
    public void delete(Game game){
        repository.delete(game);

    }

    public LiveData<List<Game>> getAllPlayers() {
        return allPlayers;
    }

}
