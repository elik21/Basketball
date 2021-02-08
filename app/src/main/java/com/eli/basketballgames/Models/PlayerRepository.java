package com.eli.basketballgames.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.eli.basketballgames.Interface.PlayerDao;

import java.util.List;

public class PlayerRepository {
    private PlayerDao playerDao;
    private LiveData<List<Player>> allPlayers;
    public PlayerRepository(Application application) {
        PlayerDataBase playerDataBase1=PlayerDataBase.getInstance(application);

        playerDao= playerDataBase1.playerDao();
        allPlayers=playerDao.getAllPlayers();
    }
    public void insert(Player player){

        new InsertPlayerAsyncTask(playerDao).execute(player);
    }
    public void update(Player player){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new updatePlayerAsyncTask(playerDao).execute(player);
    }
    public void delete(Player player)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new deletePlayerAsyncTask(playerDao).execute(player);
    }
    public void deleteAllPlayers(){
        new deletePlayerAsyncTask(playerDao).execute();

    }

    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }
    private static class InsertPlayerAsyncTask extends AsyncTask<Player,Void,Void>{
        private PlayerDao playerDao;
        public InsertPlayerAsyncTask(PlayerDao playerDao){
            this.playerDao=playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insert(players[0]);
            return null;
        }
    }
    private static class updatePlayerAsyncTask extends AsyncTask<Player,Void,Void>{
        private PlayerDao playerDao;
        public updatePlayerAsyncTask(PlayerDao playerDao){
            this.playerDao=playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.Update(players[0]);
            return null;
        }
    }
    private static class deletePlayerAsyncTask extends AsyncTask<Player,Void,Void>{
        private PlayerDao playerDao;
        public deletePlayerAsyncTask(PlayerDao playerDao){
            this.playerDao=playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.Delete(players[0]);
            return null;
        }
    }
    private static class deleteAllPlayerAsyncTask extends AsyncTask<Void,Void,Void>{
        private PlayerDao playerDao;
        public deleteAllPlayerAsyncTask(PlayerDao playerDao){
            this.playerDao=playerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.deleteAllDocuments();
            return null;
        }
    }
}
