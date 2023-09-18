package com.github.zipcodewilmington.casino.games.numberguess;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessPlayerTest {

    @Test
    void incrementScoreTest() {
        //Given
        NumberGuessPlayer player = new NumberGuessPlayer(null, null);
        player.incrementScore();
        player.incrementScore();
        player.incrementScore();
        player.incrementScore();
        player.incrementScore();

        //When
        int expected = player.getScore();
        int actual = 5;

        //Then
        Assert.assertEquals(expected, actual);

    }

    @Test
    void setPlayerScore() {
        //Given
        NumberGuessPlayer player = new NumberGuessPlayer(null, null);
        player.setPlayerScore(76);
        //When
        int expected = player.getScore();
        int actual = 76;
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getScoreTest() {
        //Given
        NumberGuessPlayer player = new NumberGuessPlayer(null, null);

        //When
        int expected = player.getScore();
        int actual = 0;

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    void calcWinRateTest() {
        //Given
        NumberGuessPlayer player = new NumberGuessPlayer(null, null);
        player.setPlayerScore(34);

        //When
        double expected = player.calcWinRate(100);
        double actual = 34.00;

        //Then
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    void updateGameTest() {
        //Given
        NumberGuessPlayer player = new NumberGuessPlayer(null, null);
        player.setTotalGames(5);
        //When
        player.updateGame(true);
        int expected = 6;
        int actual = player.getTotalGames();
        //Then
        Assert.assertEquals(expected, actual);
    }


}