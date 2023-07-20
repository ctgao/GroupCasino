package com.github.zipcodewilmington.casino;

public class RouletteGame implements GameInterface{

    private double payOutMult;
    private RouletteTable table;
    private PlayerInterface roulettePlayer;
    private int ballCurrentNum;


    public boolean winBet(String betParam) {return true;}

    public void endGame(){};

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

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
