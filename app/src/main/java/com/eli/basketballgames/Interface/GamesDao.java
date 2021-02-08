package com.eli.basketballgames.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eli.basketballgames.Models.Game;


import java.util.List;
@Dao
public interface GamesDao {
    @Insert
    void insert(Game game);
    @Update
    void Update(Game game);
    @Delete
    void Delete(Game game);
    @Query("Delete from game_table")
    void deleteAllDocuments();
    @Query("Select* from game_table")
    LiveData<List<Game>> getAllPlayers();
}
