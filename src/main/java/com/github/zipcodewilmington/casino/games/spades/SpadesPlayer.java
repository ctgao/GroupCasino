package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Collections;
import java.util.Random;

public class SpadesPlayer extends CardPlayer {
    int tricksTaken = 0;
    boolean humanPlayer = false;

    public SpadesPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
        if(wallet != null){
            humanPlayer = true;
        }
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
        Collections.sort(this.getHandOfCards());
    }

    @Override
    public <PlayingCard> PlayingCard play() {
        sortHand();
        return null;
    }

    public PlayingCard play(PlayingCardSuit winningSuit, boolean canPlaySpades) {
        PlayingCard playerInput;
        do{     // keep getting a card until it's correct
            if (humanPlayer) {
                printHand();
                playerInput = humanChoice();

                if(!validateCard(winningSuit, playerInput, canPlaySpades)){
                    this.printToConsole("INVALID CHOICE!");
                }
            } else {
                try {       // let the user pretend that the computer is thinking
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                playerInput = computerChoice(winningSuit);
            }
        } while(!validateCard(winningSuit, playerInput, canPlaySpades));

        return playerInput;
    }

    private PlayingCard computerChoice(PlayingCardSuit winningSuit) {
        Random rand = new Random();
        int cardIndex;
        // no winning suit at all or none in hand? just pick a card, ANY CARD
        if(winningSuit == null || !getHandOfCards().containsSuit(winningSuit)){
            cardIndex = rand.nextInt(getHandOfCards().size());
        }
        else{
            // if you have the suit, pick a card in that range
            // count the number of cards based on your current suit
            // save the starting index of said suit
            int countSameSuit = 0;
            int startingIndexOfSuit = Integer.MAX_VALUE;
            for(int i = 0; i < getHandOfCards().size(); i++){
                PlayingCard pc = getHandOfCards().get(i);
                if(pc.getSuit().equals(winningSuit)){
                    countSameSuit++;
                    if(startingIndexOfSuit == Integer.MAX_VALUE){
                        // if the var only contains max value, then this is the start of the suit
                        startingIndexOfSuit = i;
                    }
                }
            }
            cardIndex = rand.nextInt(countSameSuit) + startingIndexOfSuit;
        }
        return getHandOfCards().get(cardIndex);
    }

    private PlayingCard humanChoice() {
        return this.promptForCard("Which card would you like to play?");
    }

    public boolean validateCard(PlayingCardSuit winningSuit, PlayingCard input, boolean canPlaySpades) {
        // first check to see if the input card exists in your hand
        if(!getHandOfCards().contains(input)){
            return false;
        }
        if(winningSuit == null && canPlaySpades) {
            // no leading suit and can play spades, means that any card is free game
            return true;
        }
        else if(winningSuit == null && !canPlaySpades){
            // no leading suit and can't play spades, check for spades
            return !input.getSuit().equals(PlayingCardSuit.SPADES);
        }
        // check if you don't contain suit, and if you don't then check input for the suit
        return !getHandOfCards().containsSuit(winningSuit) || input.getSuit().equals(winningSuit);
    }

    public void takeATrick(){
        tricksTaken++;
    }

    public void incrementWins(boolean didYouWin){
        if(didYouWin){
            setTotalGamesWon(getTotalGamesWon() + 1);
        }
        setTotalGamesPlayed(getTotalGamesPlayed() + 1);
    }
}
