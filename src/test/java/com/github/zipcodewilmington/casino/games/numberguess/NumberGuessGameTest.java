package com.github.zipcodewilmington.casino.games.numberguess;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessGameTest {

    @Test
    void randomNumTest() {
        //Given
        int targetNumber = (int) Math.floor((Math.random() * (100 - 1 + 1) + 1));

        //When
        int actualNumber = targetNumber;
        //Then
        Assert.assertTrue(actualNumber > 0 && actualNumber <= 100);
    }

    @Test
    void validateGuessTest() {
        //Given
        int targetNumber = 43;
        //When
        //Then
    }

    @Test
    void getRemainingGuessesTest() {
        //Given
        //When
        //Then
    }

    @Test
    void isEndConditionTest() {
    }


}