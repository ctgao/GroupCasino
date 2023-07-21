package com.github.zipcodewilmington.casino;

public class RouletteGame implements GameInterface, GambleGameInterface{

    private double payOutMult;
    private RouletteTable table;
    private PlayerInterface roulettePlayer;
    private int ballCurrentNum;


    public boolean winBet(int bet, int betParam) {
        return (bet == betParam);
    }

    public void endGame(){};

    @Override
    public void add(PlayerInterface player) {
        roulettePlayer = player;
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

    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        return 0;
    }
}
