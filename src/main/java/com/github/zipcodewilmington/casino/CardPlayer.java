package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public abstract class CardPlayer extends PlayerClass{
    HandOfCards curHand;

    // Constructor
    public CardPlayer(CasinoAccount wallet, IOConsole console){
        curHand = new HandOfCards();
        super(wallet, console);
    }

    //
}
