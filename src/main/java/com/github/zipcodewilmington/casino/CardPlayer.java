package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.cardutils.*;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public abstract class CardPlayer extends PlayerClass{
    final IOConsole whiteBG = new IOConsole(AnsiColor.WHITE_BACKGROUND);
    final IOConsole resetBG = new IOConsole(AnsiColor.AUTO);
    // var for meself
    HandOfCards curHand;

    // Constructor
    public CardPlayer(CasinoAccount wallet, IOConsole console){
        super(wallet, console);
        curHand = new HandOfCards();
    }
    public CardPlayer(HandOfCards hand){
        super(null, null);
        curHand = hand;
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
        this.getPlayerInput().print("Your Hand: ");
        printHand(false);
    }

    protected void printHand(boolean noShowFirstCard){
        // WITH FORMATTING AND COLORS!!!
        this.getPlayerInput().print("[");

        for(PlayingCard pc : curHand){
            if(noShowFirstCard){
                whiteBG.print("");
                this.getPlayerInput().print("HIDDEN");
                noShowFirstCard = false;
                resetBG.print("");
            }
            else{
                pc.printCardWithColor();
            }
            if(curHand.indexOf(pc) != curHand.size() - 1) {
                this.getPlayerInput().print(", ");
            }
        }
        this.getPlayerInput().println("]");
    }

    public void clearHand(){
        curHand.clear();
    }

    protected PlayingCard promptForCard(String prompt){
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

    protected PlayingCardValue promptForCardValue(String prompt){
        String response = super.promptPlayerForChoice(prompt);

        PlayingCardValue pcv = parseCardValue(response);

        if(pcv == null){
            Casino.errorMessage.println("invalid card");

            return promptForCardValue(prompt);
        }
        return pcv;
    }

    private PlayingCardValue parseCardValue(String response) {
        for (PlayingCardValue value : PlayingCardValue.values()) {
            if (response.equals(value.toString())) {
                return value;
            }
        }
        return null;
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
    protected abstract void sortHand();
}
