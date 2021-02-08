package com.eli.basketballgames.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eli.basketballgames.Models.Player;

import java.util.List;

@Dao
public interface PlayerDao {
    @Insert
    void insert(Player player);
    @Update
    void Update(Player player);
    @Delete
    void Delete(Player player);
    @Query("Delete from plays_table")
    void deleteAllDocuments();
    @Query("Select* from plays_table")
    LiveData<List<Player>> getAllPlayers();

}
