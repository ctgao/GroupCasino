package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public abstract class CardPlayer extends PlayerClass{
    HandOfCards curHand;

    // Constructor
    public CardPlayer(CasinoAccount wallet, IOConsole console){
        super(wallet, console);
        curHand = new HandOfCards();
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
        String response = super.promptPlayerForChoice(prompt);
        String[] cardPieces = response.toUpperCase().split(" ");

        PlayingCardValue pcv = parseCardValue(cardPieces);

        return null;
    }

    private PlayingCardValue parseCardValue(String[] cardPieces) {
        for(String s : cardPieces){
            // iterating through the stuff split into spaces
            for(PlayingCardValue value : PlayingCardValue.values()){
                if(s.equals(value.toString())){

                }
            }
        }
        return null;
    }

    // abstract functions here
    public abstract void sortHand();
}
