package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {
    @Test
    void payOutCalcTest() {
        // Given
        int expected = 14;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        int actual = blackjack.payOutCalc(expected, 2);
        // Then
        assertEquals(expected * 2, actual);
    }
    @Test
    void payOutCalcTest1() {
        // Given
        int expected = 14;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        int actual = blackjack.payOutCalc(expected, 1);
        // Then
        assertEquals(expected * 1, actual);
    }
    @Test
    void payOutCalcTest2() {
        // Given
        int expected = 14;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        int actual = blackjack.payOutCalc(expected, 0);
        // Then
        assertEquals(expected * 0, actual);
    }
}