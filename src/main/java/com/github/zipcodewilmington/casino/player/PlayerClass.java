package com.github.zipcodewilmington.casino.player;
import com.github.zipcodewilmington.casino.CasinoAccount;

import java.util.Scanner;

public class PlayerClass {

    private CasinoAccount wallet;

    private Integer totalGamesWon;

    private Integer totalGamesPlayed;

    private Scanner playerInput;

    public PlayerClass (CasinoAccount wallet, Integer totalGamesWon, Integer totalGamesPlayed, Scanner playerInput) {

        this.wallet = wallet;
        this.totalGamesWon = totalGamesWon;
        this.totalGamesPlayed = totalGamesPlayed;
        this.playerInput = playerInput;
    }

    public PlayerClass (CasinoAccount wallet, Scanner playerInput){

        this.wallet = wallet;
        this.playerInput = playerInput;
        this.totalGamesPlayed = 0;
        this.totalGamesWon = 0;
    }

    public CasinoAccount getWallet() {
        return wallet;
    }

    public void setWallet(CasinoAccount wallet) {
        this.wallet = wallet;
    }

    public Integer getTotalGamesWon() {
        return totalGamesWon;
    }

    public void setTotalGamesWon(Integer totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    public Scanner getPlayerInput() {
        return playerInput;
    }

    public void setPlayerInput(Scanner playerInput) {
        this.playerInput = playerInput;
    }

    public Integer getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(Integer totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public void
}
