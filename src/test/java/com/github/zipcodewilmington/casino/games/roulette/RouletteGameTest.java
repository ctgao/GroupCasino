package com.github.zipcodewilmington.casino.games.roulette;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteGameTest {

    @Test
    void winBetTest() {
        //Given
        RouletteNumParam one = RouletteNumParam.one;
        String expectedBetType = "highOrLow";

        //When
        int expectedValue = 2;
        int actualValue = 0;

        switch(expectedBetType) {
            case "highOrLow":
                actualValue = one.highOrLow;
                break;
        }

        //Then
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    void winBetTest2() {
        //Given
        int playerBet = 3;

        //When
        int expectedBetParam = RouletteNumParam.three.whichColumn;
        int expectedBet = playerBet;

        int actualBetParam = 3;
        int actualBet = 3;

        //Then

        Assert.assertEquals(expectedBet, actualBet);
        Assert.assertEquals(expectedBetParam, actualBetParam);

    }
    @Test
    void winBetTest3() {
        //Given
        RouletteGame rg = new RouletteGame();


        //When
        int expectedValue1 = 1;
        int actualValue1 = RouletteNumParam.one.rouletteNum;

        int expectedColumnValue = 1;
        int actualColumnValue = RouletteNumParam.one.whichColumn;

        //Then
        Assert.assertTrue(rg.winBet(expectedValue1, actualValue1));
        Assert.assertTrue(rg.winBet(expectedColumnValue, actualColumnValue));

    }

    @Test
    void runTest() {
        //Given
        RouletteTable rt = new RouletteTable();
        RouletteGame rg = new RouletteGame();


        //When
        int expectedValue = 1;
        int actualValue = RouletteNumParam.one.rouletteNum;


        //Then
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertTrue(rg.winBet(expectedValue, actualValue));


    }


}