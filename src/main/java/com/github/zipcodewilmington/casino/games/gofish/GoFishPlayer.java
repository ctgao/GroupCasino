package com.github.zipcodewilmington.casino.games.gofish;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.HashMap;

public class GoFishPlayer extends CardPlayer {

    private Integer books = 0;
    public GoFishPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
    }

    public boolean hasCard(PlayingCardValue cardValue){

        if (getHandOfCards().contains(cardValue)){
            return true;
        }
        return false;
    }

    public PlayingCard[] askForCard(PlayingCardValue cardValue){

        return null;
    }

    public void PlayingCard(PlayingCard pc){

        this.receiveCard(pc);
    }

    public void remove4(PlayingCardValue card){



    }
    public void getBooks() {

    }

    public void IncrementBooks(){


    }

     public Boolean fourOfAKind(){

        return true;
     }

     public HashMap countFrequencyOfValue(){

        return null;
     }

    @Override
    public void sortHand() {

    }

    @Override
    public <SomeReturnType> String play() {
        return null;
    }
}
