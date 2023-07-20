package com.github.zipcodewilmington.casino.cardutils;

import java.util.ArrayList;

public class HandOfCards extends ArrayList<PlayingCard> {
    // Nullary Constructor
    public HandOfCards() {
        super();
    }

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
        return 0;
    }

    // checks for a specific suit
    public boolean containsSuit(PlayingCardSuit targetSuit){
        return false;
    }
}
