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
        int expectedNum = random.nextInt(36) +1;

        //Then
        Assert.assertTrue(expectedNum >= 1 && expectedNum <= 36);

    }

    @Test
    void enumCorrelationTest() {

        //Given
        int one = RouletteNumParam.one.highOrLow;

        //When
        int expectedVal = 2;
        int actualVal = one;

        //Then

        Assert.assertEquals(expectedVal, actualVal);

    }

}