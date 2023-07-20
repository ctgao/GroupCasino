package com.github.zipcodewilmington.casino;
import com.github.zipcodewilmington.utils.IOConsole;

public abstract class PlayerClass implements PlayerInterface {

    private CasinoAccount wallet ;
    private Integer totalGamesWon ;
    private Integer totalGamesPlayed ;
    private IOConsole playerInput ;

    public PlayerClass ( CasinoAccount wallet ,Integer totalGamesWon,Integer totalGamesPlayed ,IOConsole playerInput) {
        this.wallet = wallet;
        this.totalGamesWon = totalGamesWon;
        this.totalGamesPlayed = totalGamesPlayed;
        this.playerInput = playerInput;

    }

    public PlayerClass (CasinoAccount wallet,IOConsole playerInput) {
        this.wallet = wallet;
        this.playerInput = playerInput;
        this.totalGamesWon = 0;
        this.totalGamesPlayed = 0;

    }
    public Integer getWallet () {

        return wallet.getAccBalance();
    }
    public void setWallet (CasinoAccount wallet) {
        this.wallet = wallet;
    }

    public Integer getCasinoAccount() {
         return wallet.getAccBalance();
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

    public Double getWinRate () {
        return (double)  this.totalGamesWon /this.totalGamesPlayed * 100;
    }

    public String  promptPlayerForChoice (String promptChoiceString) {
         String playerAnswer = playerInput.getStringInput(promptChoiceString);

         return playerAnswer;
    }

    public Integer  promptPlayerFoMoney (String money) {
        Integer playerWantToSpend = playerInput.getIntegerInput(money);
        return playerWantToSpend;
    }



}
