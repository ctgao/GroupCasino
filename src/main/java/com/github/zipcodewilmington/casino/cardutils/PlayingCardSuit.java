package com.github.zipcodewilmington.casino.cardutils;

public enum PlayingCardSuit {
    HEARTS("♥"), CLUBS("♣"), DIAMONDS("♦"), SPADES("♠");

    private String name;

    PlayingCardSuit(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
