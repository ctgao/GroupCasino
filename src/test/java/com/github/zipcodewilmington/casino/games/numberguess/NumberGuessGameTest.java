package com.github.zipcodewilmington.casino.games.numberguess;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessGameTest {

    @Test
    void randomNumTest() {
        //Given
        NumberGuessGame game = new NumberGuessGame();
        int targetNumber = (int) Math.floor((Math.random() * (100 - 1 + 1) + 1));

        //When
        //Then
        Assert.assertTrue(targetNumber > 0 && targetNumber <= 100);
    }

    @Test
    void validateGuessTest() {
        //Given
        NumberGuessGame game = new NumberGuessGame();
        int targetNumber = game.setTargetNumber(34);
        int guess = game.setGuess(34);
        //When
        game.setTargetNumber(targetNumber);
        game.setGuess(guess);

        //Then
        Assert.assertTrue( game.validateGuess());
    }

    @Test
    void validateGuessTooHighTest(){
        //Given
        NumberGuessGame game = new NumberGuessGame();
        int targetNumber = 34;
        int guess = 56;
        //When
        game.setTargetNumber(targetNumber);
        game.setGuess(guess);

        //Then
        Assert.assertFalse(game.validateGuess());
    }
    @Test
    void validateGuessTooLowTest() {
        NumberGuessGame game = new NumberGuessGame();
        int targetNumber = 87;
        int guess = 23;
        //When
        game.setTargetNumber(targetNumber);
        game.setGuess(guess);

        //Then
        Assert.assertFalse(game.validateGuess());
    }

    @Test
    void isEndConditionTrueTest() {
        //Given
        NumberGuessGame game = new NumberGuessGame();

        //When
        game.setGuessesRemaining(0);
        //Then
        Assert.assertTrue(game.isEndCondition());
    }

    @Test
    void isEndConditionFalseTest() {
        //Given
        NumberGuessGame game = new NumberGuessGame();

        //When
        game.setGuessesRemaining(4);
        //Then
        Assert.assertFalse(game.isEndCondition());
    }


}