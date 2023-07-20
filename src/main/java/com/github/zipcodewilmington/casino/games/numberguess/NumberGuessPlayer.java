package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.PlayerClass;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer {

    private int playerScore = 0;
    private int totalGames;

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public void incrementScore() {
        playerScore++;
    }

    public int getScore() {
        return playerScore;}

    public double calcWinRate(int totalGames) {
        double winRate = (double) 100 * ((double) playerScore / totalGames);
        return winRate;
    }

    public void updateGame(boolean isEndCondition) {
       if (true) {
           this.totalGames++;
       }

    }

    public void displayPlayerInfo() {
        System.out.println(playerScore);
        System.out.println(totalGames);
        System.out.println((calcWinRate(totalGames)));
    }

}