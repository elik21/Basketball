package com.eli.basketballgames.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "plays_table")
public class Player implements Serializable {
    private String name;
    private int TShirtNumber;
    public double height;
    private Double weight;
    private int pointsPerGame;
    private int assistsPerGame;
    private int reboundsPerGame;
    private String team;
    private boolean rookie;
    @PrimaryKey(autoGenerate = true)
    private int id;



    public Player(String name) {
        this.name = name;
    }

    public String getName()  {

        return name;
    }
    public int getTShirtNumber() {
        return TShirtNumber;
    }

    public void setTShirtNumber(int TShirtNumber) {
        this.TShirtNumber = TShirtNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }



    public int getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(int pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public int getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(int assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public int getReboundsPerGame() {
        return reboundsPerGame;
    }

    public void setReboundsPerGame(int reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public boolean isRookie() {
        return rookie;
    }

    public void setRookie(boolean rookie) {
        this.rookie = rookie;
    }


}
