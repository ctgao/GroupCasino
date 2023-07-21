package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblerInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.utils.IOConsole;

public class RoulettePlayer extends PlayerClass implements GamblerInterface {
    public RoulettePlayer(CasinoAccount wallet, Integer totalGamesWon, Integer totalGamesPlayed, IOConsole playerInput) {
        super(wallet, totalGamesWon, totalGamesPlayed, playerInput);
    }



    @Override
    public int makeBet(int bet) {
        return bet;
    }


    @Override
    public boolean validBet(int bet) {
        //return (PlayerClass.getAccBalance > bet) ;
        return true;
    }

    @Override
    public void depositPayOut(int winnings) {

    }

    @Override
    public <SomeReturnType> String play() {
        return null;
    }
}
