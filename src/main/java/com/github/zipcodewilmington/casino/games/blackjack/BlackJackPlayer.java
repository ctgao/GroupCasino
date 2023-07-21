package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;

public class BlackJackPlayer extends CardPlayer {
    public BlackJackPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
    }

    @Override
    public void sortHand() {

    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
}
