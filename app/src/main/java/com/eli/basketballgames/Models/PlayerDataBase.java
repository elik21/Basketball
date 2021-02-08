package com.eli.basketballgames.Models;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.eli.basketballgames.Interface.GamesDao;
import com.eli.basketballgames.Interface.PlayerDao;

@Database(entities = {Player.class,Game.class},version=3,exportSchema = false)
public  abstract class PlayerDataBase extends RoomDatabase {
    public static PlayerDataBase instance1;
    public abstract PlayerDao playerDao();
    public abstract GamesDao gameDao();
    public static synchronized PlayerDataBase getInstance(Context context){
        if(instance1==null){
            instance1= Room.databaseBuilder(context.getApplicationContext(),PlayerDataBase.class,"game_DB")
                    .fallbackToDestructiveMigration().
                  addCallback(roomCallback1).build();
        }
        return instance1;
    }
    private static RoomDatabase.Callback roomCallback1=new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db1) {
            super.onCreate(db1);
            new PopulateDBAsyncTask(instance1).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private PlayerDao playerDao;
        private GamesDao gamesDao;
        public PopulateDBAsyncTask(PlayerDataBase db1){
            playerDao=db1.playerDao();
            gamesDao=db1.gameDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.insert(new Player("yes"));
            gamesDao.insert(new Game("Maccabi","Hapoel"));

            return null;
        }
    }
}
