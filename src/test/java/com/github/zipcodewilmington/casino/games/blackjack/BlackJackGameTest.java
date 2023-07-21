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
        int actual = blackjack.payOutCalc(expected);
        // Then
        assertEquals(expected * 2, actual);
    }

//    @Test
//    void beatDealerTest() {
//        // Given
//        int dealerHand = 12;
//        int yourHand = 20;
//        BlackJackGame blackjack = new BlackJackGame();
//        // When
//        boolean actual = blackjack.beatDealer(dealerHand > 21, dealerHand, yourHand);
//        // Then
//        assertEquals(true, actual);
//    }
//    @Test
//    void beatDealerTest1() {
//        // Given
//        int dealerHand = 22;
//        int yourHand = 20;
//        BlackJackGame blackjack = new BlackJackGame();
//        // When
//        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
//        // Then
//        assertEquals(true, actual);
//    }
//    @Test
//    void beatDealerTest2() {
//        // Given
//        int dealerHand = 12;
//        int yourHand = 1;
//        BlackJackGame blackjack = new BlackJackGame();
//        // When
//        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
//        // Then
//        assertEquals(false, actual);
//    }
//    @Test
//    void beatDealerTest3() {
//        // Given
//        int dealerHand = 1;
//        int yourHand = 22;
//        BlackJackGame blackjack = new BlackJackGame();
//        // When
//        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
//        // Then
//        assertEquals(false, actual);
//    }
}