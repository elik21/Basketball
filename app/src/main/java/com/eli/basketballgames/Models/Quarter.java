package com.eli.basketballgames.Models;

public class Quarter extends Game{
  int fouls;
  int assists;
  int points;
  String hostTeam;
  String guestTeam;
  public Quarter(String hostTeam, String guestTeam) {
    this.hostTeam=guestTeam;
    this.guestTeam=guestTeam;
  }



  @Override
  public void setHostTeam(String hostTeam) {


  }

  @Override
  public void setGuestTeam(String guestTeam) {

  }

  @Override
  public void setScore1(int score1) {

  }

  @Override
  public void setScore2(int score2) {

  }

  @Override
  public void setQuarter(int quarter) {

  }

  @Override
  public void setFoulsH(int foulsH) {

    this.fouls=foulsH;
  }
}
