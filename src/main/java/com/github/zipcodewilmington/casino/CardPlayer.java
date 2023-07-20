package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public abstract class CardPlayer extends PlayerClass{
    HandOfCards curHand;

    // Constructor
    public CardPlayer(CasinoAccount wallet, IOConsole console){
        curHand = new HandOfCards();
        super(wallet, console);
    }

    // my other functions
    public HandOfCards getHandOfCards(){
        return curHand;
    }

    public void receiveCard(PlayingCard pc){
        curHand.addCard(pc);
    }

    public void useCard(PlayingCard pc){
        curHand.removeCard(pc);
    }

    public void printHand(){
        // not sure what to put here yet so i'll leave it empty for now
    }

    public void clearHand(){
        curHand.clear();
    }

    public PlayingCard promptForCard(String prompt){
        // not sure what to put here yet so i'll leave it empty for now
        return null;
    }

    // abstract functions here
    public abstract void sortHand();
}
