package com.github.zipcodewilmington.casino.cardutils;

public enum PlayingCardSuit {
    HEARTS("Hearts"), SPADES("Spades"), CLUBS("Clubs"), DIAMONDS("Diamonds");

    private String name;

    PlayingCardSuit(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
