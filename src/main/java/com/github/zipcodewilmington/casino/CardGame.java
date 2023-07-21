package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.cardutils.TheDeck;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class CardGame implements GameInterface{
    ArrayList<CardPlayer> players;
    TheDeck theDeck;
    HashMap<CardPlayer, Integer> playerScores;

    // Constructors
    public CardGame() {
        players = new ArrayList<>();
        theDeck = new TheDeck();
        playerScores = new HashMap<>();
    }

    public TheDeck getTheDeck() {
        return theDeck;
    }

    public ArrayList<CardPlayer> getPlayers() {
        return players;
    }

    @Override
    public void add(PlayerInterface player) {
        players.add((CardPlayer) player);
        playerScores.put((CardPlayer) player, 0);
    }

    @Override
    public void remove(PlayerInterface player) {
        players.remove((CardPlayer) player);
        playerScores.remove((CardPlayer) player);
    }

    public void dealCards(int cardsForEach){
        for(int i = 0; i < cardsForEach; i++){
            // each round of dealing
            for(CardPlayer cp : players){
                // for each player, give one card
                cp.receiveCard(theDeck.drawCard());
            }
        }
    }
}
