package com.github.zipcodewilmington.casino.cardutils;

public enum CardSuit {
    HEARTS("Hearts"), SPADES("Spades"), CLUBS("Clubs"), DIAMONDS("Diamonds");

    private String name;

    CardSuit(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
