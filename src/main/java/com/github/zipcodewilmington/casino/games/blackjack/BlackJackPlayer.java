package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblerInterface;
import com.github.zipcodewilmington.casino.cardutils.TheDeck;
import com.github.zipcodewilmington.utils.IOConsole;

public class BlackJackPlayer extends CardPlayer implements GamblerInterface {
    private boolean stayOrNot;

    public BlackJackPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
        stayOrNot = false;
    }

    // true means you decided to stay, false means you're good to keep going
    public boolean isStayOrNot() {
        return stayOrNot;
    }

    public void setStayOrNot(boolean stayOrNot) {
        this.stayOrNot = stayOrNot;
    }

    @Override
    public void sortHand() {
        // this does nothing because there's nothing to sort
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

    @Override
    public void makeBet(int bet) {

    }

    @Override
    public boolean validBet(int bet) {
        return false;
    }

    @Override
    public void depositPayOut(int winnings) {

    }

    public void hitMe(TheDeck deck){
    }

    public void stay(){
        stayOrNot = true;
    }
}
