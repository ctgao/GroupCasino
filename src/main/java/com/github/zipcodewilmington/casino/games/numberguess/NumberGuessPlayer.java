package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer extends PlayerClass{

    private int playerScore = 0;
    private int totalGames;

    public NumberGuessPlayer(CasinoAccount wallet, IOConsole playerInput) {
        super(wallet, playerInput);
    }


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
        return playerScore;
    }

    public double calcWinRate(int totalGames) {
        return (double) 100 * ((double) playerScore / totalGames);
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

    @Override
    public <String> String play() {
        return null;
    }
}