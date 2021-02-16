package com.eli.basketballgames.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.eli.basketballgames.Interfaces.GamesDao;
import com.eli.basketballgames.Interfaces.PlayerDao;
import com.eli.basketballgames.Models.Game;
import com.eli.basketballgames.Models.Player;

@Database(entities = {Player.class, Game.class},version=4,exportSchema = false)
public  abstract class PlayerDataBase extends RoomDatabase {
    public static PlayerDataBase instance2;
    public abstract PlayerDao playerDao();
    public abstract GamesDao gameDao();
    public static synchronized PlayerDataBase getInstance(Context context){
        if(instance2==null){
            instance2= Room.databaseBuilder(context
                    .getApplicationContext()
                    ,PlayerDataBase.class,"game4_DB")
                    .fallbackToDestructiveMigration().
                  addCallback(roomCallback1).build();
        }
        return instance2;
    }
    private static RoomDatabase.Callback roomCallback1=new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db1) {
            super.onCreate(db1);
            new PopulateDBAsyncTask(instance2).execute();
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
