package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.games.roulette.RouletteNumParam;

import java.util.Random;

public class RouletteTable {

    private Random ball = new Random();
    RouletteNumParam ans;

    public RouletteNumParam throwBall() {
        int ballNum = ball.nextInt(36) +1;

        for (RouletteNumParam num :  RouletteNumParam.values()) {
            if (num.rouletteNum == ballNum) {
                ans = num;
            }
        }
       return ans;
    }


}
