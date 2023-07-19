package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {

    private String accOwner;
    private int accBalance;

    public CasinoAccount(String accOwner, int accBalance){
        this.accOwner = accOwner;
        this.accBalance = accBalance;

    }

    public int getAccBalance() {return this.accBalance;}

    public void setAccBalance(int accBalance) {
        this.accBalance = accBalance;
    }

    public String getAccOwner() {return this.accOwner;}

    public void updateAccBalance(int winnings) {
        this.accBalance = this.accBalance + winnings;
    }

}
