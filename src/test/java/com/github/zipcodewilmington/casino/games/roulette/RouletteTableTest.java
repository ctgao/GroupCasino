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
    void enumCorrelationTest3() {

        //Given
        int thirteen = RouletteNumParam.thirteen.blackOrRed;

        //When
        int expectedBlackOrRed = 1;
        int actualBlackOrRed = thirteen;

        //Then
        Assert.assertEquals(expectedBlackOrRed, actualBlackOrRed);

    }


}