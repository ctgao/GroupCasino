package com.github.zipcodewilmington.casino.games.slots;


import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblerInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer extends PlayerClass implements GamblerInterface {

    public SlotsPlayer(CasinoAccount wallet, IOConsole playerInput) {
        super(wallet, playerInput);
    }

    @Override
    public void makeBet(int bet) {
//        if (validBet(bet)) {
//        }
//        return bet;
    }

    @Override
    public boolean validBet(int bet) {
        if (getWallet() > bet) {
            return true;
        }
        return false;
    }

    @Override
    public void depositPayOut(int winnings) {

    }


    @Override
    public <String> String play() {
        return null;
    }

}
