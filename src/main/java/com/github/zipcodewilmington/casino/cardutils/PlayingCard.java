package com.github.zipcodewilmington.casino.cardutils;

public class PlayingCard {
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
    /**
     * same as Integer.compareTo(), but only for the card values
     * @param otherCard
     * @return neg = less than, 0 = equal to, or pos = greater than otherCard
     */
    public int compareValue(PlayingCard otherCard){
        return this.value.compareTo(otherCard.getValue());
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        PlayingCard other = (PlayingCard) o;
        return other.getSuit().equals(suit) && other.getValue().equals(value);
    }
}
