package com.github.zipcodewilmington.casino;

public interface GamblerInterface {

    void makeBet(int bet);

    boolean validBet(int bet);

    //The winnings from a bet going into the player's wallet
    void depositPayOut(int winnings);


}
