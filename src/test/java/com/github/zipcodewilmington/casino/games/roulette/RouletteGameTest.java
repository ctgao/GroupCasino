package com.github.zipcodewilmington.casino.games.roulette;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteGameTest {

    @Test
    public void winBetTest() {
        //Given
        RouletteNumParam one = RouletteNumParam.one;
        String expectedBetType = "highOrLow";

        //When
        int expectedValue = 1;
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
    public void winBetTest2() {
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
    public void winBetTest3() {
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
    public void runTest() {
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

    @Test
    public void printWinningParamTest1() {
        //Given
        RouletteGame game = new RouletteGame();

        //When
        String expctedString = "odd red high 1doz 1col ";
        String actualString = game.printWinningParam(RouletteNumParam.one);

        //Then
        Assert.assertEquals(expctedString, actualString);
    }

    @Test
    public void printWinningParamTest2() {
        //Given
        RouletteGame game = new RouletteGame();

        //When
        String expctedString2 = "even red high 1doz 3col";
        String actualString2 = game.printWinningParam(RouletteNumParam.twelve);

        //Then
        Assert.assertEquals(expctedString2, actualString2);
    }

    @Test
    public void printWinningParamTest3() {
        //Given
        RouletteGame game = new RouletteGame();

        //When
        String expctedString3 = "odd black low 3doz 2col ";
        String actualString3 = game.printWinningParam(RouletteNumParam.thirty_five);

        //Then
        Assert.assertEquals(expctedString3, actualString3);
    }

    @Test
    public void printWinningParamZeroTest() {
        //Given
        RouletteGame game = new RouletteGame();

        //When
        String expctedZeroString = "";
        String actualZeroString = game.printWinningParam(RouletteNumParam.zero);

        //Then
        Assert.assertEquals(expctedZeroString, actualZeroString);

    }

    }

