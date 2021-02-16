package com.eli.basketballgames.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.eli.basketballgames.Interfaces.GamesDao;
import com.eli.basketballgames.Models.Game;
import com.eli.basketballgames.DataBase.PlayerDataBase;

import java.util.List;

public class GameRepository {
    private GamesDao gameDao;
    private LiveData<List<Game>> allGames;
    public GameRepository(Application application) {
        PlayerDataBase playerDataBase=PlayerDataBase.getInstance(application);

        gameDao= playerDataBase.gameDao();
        allGames=gameDao.getAllPlayers();
    }
    public void insert(Game game){

        new GameRepository.InsertGameAsyncTask(gameDao).execute(game);
    }
    public void update(Game game){

        new GameRepository.updateGameAsyncTask(gameDao).execute(game);
    }
    public void delete(Game game)
    {

        new GameRepository.deleteGameAsyncTask(gameDao).execute(game);
    }
    public void deleteAllPlayers(){
        new GameRepository.deleteAllGamesAsyncTask(gameDao).execute();

    }

    public LiveData<List<Game>> getAllPlayers() {
        return allGames;
    }
    private static class InsertGameAsyncTask extends AsyncTask<Game,Void,Void> {

        private GamesDao gameDao;
        public InsertGameAsyncTask(GamesDao gamesDao){
            this.gameDao=gamesDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            gameDao.insert(games[0]);
            return null;
        }
    }
    private static class updateGameAsyncTask extends AsyncTask<Game,Void,Void>{
        private GamesDao gamesDao;

        public updateGameAsyncTask(GamesDao gameDao){
            this.gamesDao=gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            gamesDao.Update(games[0]);
            return null;
        }
    }
    private static class deleteGameAsyncTask extends AsyncTask<Game,Void,Void>{
        private GamesDao gameDao;
        public deleteGameAsyncTask(GamesDao gameDao){
            this.gameDao=gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
           gameDao.Delete(games[0]);
            return null;
        }
    }
    private static class deleteAllGamesAsyncTask extends AsyncTask<Void,Void,Void>{
        private GamesDao gameDao;
        public deleteAllGamesAsyncTask(GamesDao gameDao){
            this.gameDao=gameDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gameDao.deleteAllDocuments();
            return null;
        }
    }
}
