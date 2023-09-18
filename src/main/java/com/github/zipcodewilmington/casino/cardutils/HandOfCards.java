package com.github.zipcodewilmington.casino.cardutils;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;

public class HandOfCards extends ArrayList<PlayingCard> {
    // Nullary Constructor
    public HandOfCards() {
        super();
    }

    /*
     * Note: not sure how necessary these two functions are since the superclass,
     * ArrayList already has these as methods
     */
    // adds a card to your hand
    public void addCard(PlayingCard pc){
        this.add(pc);
    }
    // removes a card from your hand
    public void removeCard(PlayingCard pc){
        this.remove(pc);
    }

    // checks for a specific value
    public int howManyOfValue(PlayingCardValue targetValue){
        int count = 0;
        for(PlayingCard pc : this){
            if(targetValue.equals(pc.getValue())){
                count++;
            }
        }
        return count;
    }

    // checks for a specific suit
    public boolean containsSuit(PlayingCardSuit targetSuit){
        for(PlayingCard pc : this){
            if(targetSuit.equals(pc.getSuit())){
                return true;
            }
        }
        return false;
    }
}
