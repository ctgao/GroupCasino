package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import com.github.zipcodewilmington.utils.IOConsole;

public class SpadesPlayer extends CardPlayer {
    int tricksTaken = 0;
    boolean humanPlayer = false;

    public SpadesPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
        humanPlayer = true;
    }

    public SpadesPlayer(HandOfCards hand) {
        super(hand);
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }
    public void setHumanPlayer(boolean humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public int getScore(){
        return tricksTaken;
    }

    @Override
    public void sortHand() {
//        HandOfCards hand = getHandOfCards();
//        int curIndex = 0;
//        // go through all the suits, and spades are last
//        for(PlayingCardSuit suit : PlayingCardSuit.values()){
//            int swapIndex = curIndex;
//            while(swapIndex < hand.size()){
//                // look for the card that matches the current suit
//                if(hand.get(swapIndex).com)
//            }
//        }
    }

    @Override
    public <SomeReturnType> String play() {
        return null;
    }

    public void takeATrick(){
        tricksTaken++;
    }
}
