package com.github.zipcodewilmington.casino.games.slots;


import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblerInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer extends PlayerClass implements GamblerInterface {
    private int playerScore = 0;
    private int totalGames;



    public SlotsPlayer(CasinoAccount wallet, IOConsole playerInput) {

        super(wallet, playerInput);
    }

    @Override
    public void makeBet(int bet) {
        this.getCasinoAccount().updateAccBalance(-1 * bet);
    }

    @Override
    public boolean validBet(int bet) {
        if (getWallet() >= bet && getWallet() > 0) {
            return true;
        }
        return false;
    }
    public void displayPlayerInfo() {printToConsole("Current balance is " + getWallet());
    }

    @Override
    public void depositPayOut(int winnings) {
        getCasinoAccount().updateAccBalance(winnings);

    }




    @Override
    public <SomeReturnType> SomeReturnType play() {
        String choice = promptPlayerForChoice("Would you like to start the GAME ?");
        if(choice.toUpperCase().equals("YES")) {
            return (SomeReturnType) choice.toUpperCase();
        }
        throw new RuntimeException("TRY AGAIN");


    }

}
