package com.github.zipcodewilmington.casino.cardutils;

public enum PlayingCardSuit {
    HEARTS("Hearts"), CLUBS("Clubs"), DIAMONDS("Diamonds"), SPADES("Spades");

    private String name;

    PlayingCardSuit(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
