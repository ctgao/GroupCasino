package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.games.roulette.RouletteNumParam;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

class RouletteTableTest {


    @Test
    void throwBallTest() {
        //Given
        Random random = new Random();

        //When
        int expectedNum = random.nextInt(37);

        //Then
        Assert.assertTrue(expectedNum >= 0 && expectedNum <= 36);

    }

    @Test
    void enumCorrelationTest1() {

        //Given
        int one = RouletteNumParam.one.highOrLow;

        //When
        int expectedVal = 1;
        int actualVal = one;

        //Then

        Assert.assertEquals(expectedVal, actualVal);

    }
    @Test
    void enumCorrelationTest2() {

        //Given
        int twentyOne = RouletteNumParam.twenty_one.rouletteNum;

        //When
        int expectedNum = 21;
        int actualNum = twentyOne;

        //Then

        Assert.assertEquals(expectedNum, actualNum);

    }
    @Test
    public void enumZeroCorrelationTest() {

        //Given
        int thirteen = RouletteNumParam.zero.oddOrEven;

        //When
        int expectedOddOrEven = 100;
        int actualOddOrEven = thirteen;

        //Then
        System.out.println(thirteen);
        Assert.assertEquals(expectedOddOrEven, actualOddOrEven);


    }

    @Test
    public void payOutCalcTest() {
        //Given
        RouletteGame roulGame = new RouletteGame();
        int betAmount = 50;
        int payOutMult = 4;

        //When
        int expectedWinnings = 250;
                //You win your bet TIMES the mult AND get your bet back
        int actualWinnings = roulGame.payOutCalc(betAmount, payOutMult);

        //Then
        Assert.assertEquals(expectedWinnings, actualWinnings);

    }

    @Test
    public void winBetTest1() {
        //Given
        RouletteGame roullGame = new RouletteGame();
        int bet = 5;
        int betParam = 8;

        //When

        //Then
        Assert.assertFalse(roullGame.winBet(bet, betParam));
    }
    @Test
    public void winBetTest2() {
        //Given
        RouletteGame roullGame = new RouletteGame();
        int bet2 = 10;
        int betParam2 = 10;

        //When

        //Then
        Assert.assertTrue(roullGame.winBet(bet2, betParam2));
    }
    @Test
    public void winBetZeroTest() {
        //Given
        RouletteGame roullGame = new RouletteGame();
        int bet2 = 0;
        int betParam2 = 0;

        //When

        //Then
        Assert.assertTrue(roullGame.winBet(bet2, betParam2));
    }

    @Test
    public void isEndConditionTrueTest() {
        //Given
        RouletteGame rGame = new RouletteGame();

        //When
        rGame.setChoice(2);

        //Then
        Assert.assertTrue(rGame.isEndCondition());
    }
    @Test
    public void isEndConditionFalseTest() {
        //Given
        RouletteGame rGame2 = new RouletteGame();

        //When
        rGame2.setChoice(1);

        //Then
        Assert.assertFalse(rGame2.isEndCondition());
    }

    @Test
    public void bettingOnNumTest() {
        //Given
        RouletteGame rGame3 = new RouletteGame();

        //When

        //Then
    }


}