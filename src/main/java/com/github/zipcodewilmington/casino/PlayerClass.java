package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.casino.games.blackjack.DealerPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public abstract class PlayerClass implements PlayerInterface {

    private CasinoAccount wallet;
    private Integer totalGamesWon;
    private Integer totalGamesPlayed;
    private IOConsole playerInput;

    public PlayerClass(CasinoAccount wallet, Integer totalGamesWon, Integer totalGamesPlayed, IOConsole playerInput) {
        this.wallet = wallet;
        this.totalGamesWon = totalGamesWon;
        this.totalGamesPlayed = totalGamesPlayed;
        this.playerInput = playerInput;
    }

    public PlayerClass(CasinoAccount wallet, IOConsole playerInput) {
        this.wallet = wallet;
        this.playerInput = playerInput;
        this.totalGamesWon = 0;
        this.totalGamesPlayed = 0;
    }

    public IOConsole getPlayerInput() {
        return playerInput;
    }

    public Integer getWallet() {
        return wallet.getAccBalance();
    }

    public void setWallet(CasinoAccount wallet) {
        this.wallet = wallet;
    }

    public CasinoAccount getCasinoAccount() {
        return wallet;
    }


    public Integer getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(Integer totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public Integer getTotalGamesWon() {
        return totalGamesWon;
    }

    public void setTotalGamesWon(Integer totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    public Double getWinRate() {
        return (double) this.totalGamesWon / this.totalGamesPlayed * 100;
    }

    public String promptPlayerForChoice(String promptChoiceString) {
        String playerAnswer = playerInput.getStringInput(promptChoiceString);
        return playerAnswer;
    }

    public Integer promptPlayerFoMoney(String money) {
        Integer playerWantToSpend = playerInput.getIntegerInput(money);
        return playerWantToSpend;
    }

    public boolean promptPlayerToPlayAgain(String prompt) {
        do {
            String choice = promptPlayerForChoice(prompt);
            if (choice.toUpperCase().contains("Y")) {
                return true;
            } else if (choice.toUpperCase().contains("N")) {
                return false;
            }
            else{
                Casino.errorMessage.println("INVALID RESPONSE! Try again :)");
            }
        } while(true);
    }

    public void printToConsole(String prompt) {
        playerInput.println(prompt);
    }
}
