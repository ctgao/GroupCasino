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
    public boolean containsValue(PlayingCardValue targetValue){
        return false;
    }

    // checks for a specific suit
    public boolean containsSuit(PlayingCardSuit targetSuit){
        return false;
    }

    // testing to see if this string is what I want when I display this card to the user
    @Override
    public String toString(){
        return this.toString();
    }
}
