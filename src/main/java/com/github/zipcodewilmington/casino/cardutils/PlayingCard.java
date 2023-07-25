package com.github.zipcodewilmington.casino.cardutils;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public class PlayingCard implements Comparable<PlayingCard>{
    // for printing in pretty colors
    final IOConsole redCards = new IOConsole(AnsiColor.RED);
    final IOConsole blackCards = new IOConsole(AnsiColor.BLACK);
    final IOConsole whiteBG = new IOConsole(AnsiColor.WHITE_BACKGROUND);
    final IOConsole resetBG = new IOConsole(AnsiColor.AUTO);

    // these local vars are useful
    PlayingCardSuit suit;
    PlayingCardValue value;

    // Constructors
    public PlayingCard(PlayingCardSuit suit, PlayingCardValue value) {
        this.suit = suit;
        this.value = value;
    }

    // Getters
    public PlayingCardSuit getSuit() {
        return suit;
    }
    public PlayingCardValue getValue() {
        return value;
    }

    // Other methods
    @Override
    public String toString() {
        return value + " " + suit;
    }

    @Override
    public boolean equals(Object o) {
        PlayingCard other = (PlayingCard) o;
        return other.getSuit().equals(suit) && other.getValue().equals(value);
    }

    private boolean isRedCard(){
        return suit.equals(PlayingCardSuit.HEARTS) || suit.equals(PlayingCardSuit.DIAMONDS);
    }

    /**
     * same as .compareTo(), but only for the card values
     * @param otherCard
     * @return neg = this less than, 0 = equal to, or pos = this greater than otherCard
     */
    @Override
    public int compareTo(PlayingCard otherCard) {
        int suitsCompared = this.suit.compareTo(otherCard.getSuit());
        int valuesCompared = this.value.compareTo(otherCard.getValue());
        return suitsCompared * 13 + valuesCompared;
    }
    // BIG NOTE : SHOULD I MAKE A COMPARATOR SO THE PLAYING CARDS CAN BE SORTED IN DIFFERENT WAYS?
    // NOT SURE YET - BUT ITS AN IDEA - GoFish and Spades hands are going to be sorted differently
    public int compareValue(PlayingCardValue otherCard){
        return this.value.compareTo(otherCard);
    }

    //for printing cards out in pretty pretty colors
    public void printCardWithColor(){
        whiteBG.print("");
        if(isRedCard()){
            redCards.print(toString());
        }
        else{
            blackCards.print(toString());
        }
        resetBG.print("");
    }
}
