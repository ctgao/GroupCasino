package com.github.zipcodewilmington.casino.games.gofish;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;

public class GoFishGame extends CardGame {
    private  GoFishPlayer player;
    public Boolean checkHandForCard(GoFishPlayer danielle, PlayingCardValue card){
        return true;
    }

    public GoFishPlayer checkWinner(){
        //get each player
        //get each player score
        //compare
        return null;
    }

    public void printGoFish() {

    }



    @Override
    public void run() {

    }

    @Override
    public void printWinner() {

    }

    @Override
    public boolean isEndCondition() {
        return false;
    }

}
