package com.eli.basketballgames.Models;

import android.os.CountDownTimer;

import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "game2_table")

public class Game implements Serializable {
    private String hostTeam;
    private String guestTeam;
    private int score1;
    private int score2;
    private int quarter;
    private int foulsH;
    private int foulsG;
    @Ignore
    CountDownTimer clock;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Game(String hostTeam, String guestTeam) {
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(String hostTeam) {
        this.hostTeam = hostTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getFoulsH() {
        return foulsH;
    }

    public void setFoulsH(int foulsH) {
        this.foulsH = foulsH;
    }

    public int getFoulsG() {
        return foulsG;
    }

    public void setFoulsG(int foulsG) {
        this.foulsG = foulsG;
    }

    public CountDownTimer getClock() {
        return clock;
    }

    public void setClock(CountDownTimer clock) {
        this.clock = clock;
    }
}
