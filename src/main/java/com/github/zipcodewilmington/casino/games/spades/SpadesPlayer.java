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
        Collections.sort(this.getHandOfCards());
    }

    @Override
    public <PlayingCard> PlayingCard play() {
        sortHand();
        return null;
    }

    public PlayingCard play(PlayingCardSuit winningSuit, boolean canPlaySpades) {
        if(humanPlayer){
            printHand();
            return humanChoice(winningSuit, canPlaySpades);
        }
        else{
            return computerChoice(winningSuit, canPlaySpades);
        }
    }

    private PlayingCard computerChoice(PlayingCardSuit winningSuit, boolean canPlaySpades) {
        Random rand = new Random();
        int cardIndex;
        // no winning suit? just pick a card
        if(winningSuit == null || getHandOfCards().containsSuit(winningSuit)){
            cardIndex = rand.nextInt(getHandOfCards().size());
        }
        else{
            // if you have the suit, pick a card in that range
            // count the number of cards based on your current suit
            int countSameSuit = 0;
            // save the starting index of said suit
            int startingIndexOfSuit = Integer.MAX_VALUE;
            for(int i = 0; i < getHandOfCards().size(); i++){
                PlayingCard pc = getHandOfCards().get(i);
                if(pc.getSuit().equals(winningSuit)){
                    countSameSuit++;
                    if(startingIndexOfSuit == Integer.MAX_VALUE){
                        // if the var only contains max value, then this is the start of this suit
                        startingIndexOfSuit = i;
                    }
                }
            }
            cardIndex = selectCard(startingIndexOfSuit, countSameSuit, rand);
        }
        return getHandOfCards().get(cardIndex);
    }

    public int selectCard(int startingIndex, int totalCards, Random rand) {
        if(totalCards == 0){
            // NO CARDS!!!
            return rand.nextInt(getHandOfCards().size());
        }
        // we have some of said suit
        return rand.nextInt(totalCards) + startingIndex;
    }

    private PlayingCard humanChoice(PlayingCardSuit winningSuit, boolean canPlaySpades) {
        PlayingCard input;
        do {
            input = this.promptForCard("Which card would you like to play?");
            if(!validateCard(winningSuit, input)){
                this.printToConsole("INVALID CHOICE! Please pick another card.");
            }
        } while(!validateCard(winningSuit, input));
        return null;
    }

    public boolean validateCard(PlayingCardSuit winningSuit, PlayingCard input) {
        // first check to see if the input card exists in your hand
        if(getHandOfCards().cont)
    }

    public void takeATrick(){
        tricksTaken++;
    }

    public void incrementScore(boolean didYouWin){
        if(didYouWin){
            setTotalGamesWon(getTotalGamesWon() + 1);
        }
        setTotalGamesPlayed(getTotalGamesPlayed() + 1);
    }
}
