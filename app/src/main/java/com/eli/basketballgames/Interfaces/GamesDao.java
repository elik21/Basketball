package com.eli.basketballgames.Interfaces;

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
    @Query("Delete from game2_table")
    void deleteAllDocuments();
    @Query("Select* from game2_table")
    LiveData<List<Game>> getAllPlayers();
    @Query("Select *from game2_table where game2_table.guestTeam Like '%' || :query || '%'")
    LiveData<List<Game>> search(String query);

}
