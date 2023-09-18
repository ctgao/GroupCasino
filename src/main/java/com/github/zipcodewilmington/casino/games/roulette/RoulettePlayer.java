package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblerInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.utils.IOConsole;

public class RoulettePlayer extends PlayerClass implements GamblerInterface {
    public RoulettePlayer(CasinoAccount wallet, IOConsole playerInput) {
        super(wallet, playerInput);
    }



    @Override
    public void makeBet(int bet) {
        this.getCasinoAccount().updateAccBalance(bet * -1);
    }


    @Override
    public boolean validBet(int bet) {
        return (bet <= this.getWallet() && bet > 0);
    }

    @Override
    public void depositPayOut(int winnings) {
        this.getCasinoAccount().updateAccBalance(winnings);
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}
