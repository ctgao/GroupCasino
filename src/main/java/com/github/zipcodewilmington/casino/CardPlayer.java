package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.cardutils.*;
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
        super.printToConsole(curHand.toString());
    }

    public void clearHand(){
        curHand.clear();
    }

    public PlayingCard promptForCard(String prompt){
        String response = super.promptPlayerForChoice(prompt);
        String[] cardPieces = response.toUpperCase().split(" ");

        PlayingCardValue pcv = parseCardValue(cardPieces);
        PlayingCardSuit pcs = parseCardSuit(cardPieces);

        if(pcv == null || pcs == null){
            // or some exception??? dunno yet
            return null;
        }
        return new PlayingCard(pcs, pcv);
    }

    private PlayingCardSuit parseCardSuit(String[] cardPieces) {
        for(String s : cardPieces){
            // iterating through the stuff split into spaces
            for(PlayingCardSuit suit : PlayingCardSuit.values()){
                if(s.equals(suit.name())){
                    return suit;
                }
            }
        }
        return null;
    }

    private PlayingCardValue parseCardValue(String[] cardPieces) {
        for(String s : cardPieces){
            // iterating through the stuff split into spaces
            for(PlayingCardValue value : PlayingCardValue.values()){
                if(s.equals(value.toString())){
                    return value;
                }
            }
        }
        return null;
    }

    // abstract functions here
    public abstract void sortHand();
}
