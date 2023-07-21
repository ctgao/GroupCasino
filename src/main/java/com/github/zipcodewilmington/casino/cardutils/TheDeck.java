package com.github.zipcodewilmington.casino.cardutils;

import java.util.Random;
import java.util.Stack;

public class TheDeck extends Stack<PlayingCard>{
    public TheDeck(){
        fillDeck();
    }

    private void fillDeck(){
        for(PlayingCardValue value : PlayingCardValue.values()) {
            for(PlayingCardSuit suit : PlayingCardSuit.values()){
                this.push(new PlayingCard(suit, value));
            }
        }
        shuffle();
    }

    public void reclaimCards(){
        this.clear();
        fillDeck();
    }

    /**
     * Using the modern algorithm of Fisher-Yates shuffle
     * The modern algorithm is more suited for computers
     */
    public void shuffle(){
        Random rand = new Random();
        int n = this.size();

        for(int i = 0; i < n - 2; i++){
            int j = rand.nextInt(n - i) + i;
            PlayingCard temp = this.get(j);
            this.set(j, this.get(i));
            this.set(i, temp);
        }
    }

    public PlayingCard drawCard(){
        return this.pop();
    }

    public boolean hasCardsLeft(){
        return !this.isEmpty();
    }

    // This getter is pretty much only for the tests
    public PlayingCard[] getDeck(){
        return this.toArray(new PlayingCard[this.size()]);
    }
}