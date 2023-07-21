package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {
    @Test
    void calculateScoreTest() {
        // Given
        int expected = 12;
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        curHand.addCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE));
        BlackJackGame blackjack = new BlackJackGame();
        // When
        int actual = blackjack.calculateScore(curHand);
        // Then
        assertEquals(expected, actual);
    }
    @Test
    void calculateScoreTest1() {
        // Given
        int expected = 21;
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        curHand.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING));
        curHand.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.QUEEN));
        BlackJackGame blackjack = new BlackJackGame();
        // When
        int actual = blackjack.calculateScore(curHand);
        // Then
        assertEquals(expected, actual);
    }
    @Test
    void calculateScoreTest2() {
        // Given
        int expected = 20;
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE));
        curHand.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.FIVE));
        curHand.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.JACK));
        BlackJackGame blackjack = new BlackJackGame();
        // When
        int actual = blackjack.calculateScore(curHand);
        // Then
        assertEquals(expected, actual);
    }

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

    @Test
    void beatDealerTest() {
        // Given
        int dealerHand = 12;
        int yourHand = 20;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
        // Then
        assertEquals(true, actual);
    }
    @Test
    void beatDealerTest1() {
        // Given
        int dealerHand = 22;
        int yourHand = 20;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
        // Then
        assertEquals(true, actual);
    }
    @Test
    void beatDealerTest2() {
        // Given
        int dealerHand = 12;
        int yourHand = 1;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
        // Then
        assertEquals(false, actual);
    }
    @Test
    void beatDealerTest3() {
        // Given
        int dealerHand = 1;
        int yourHand = 22;
        BlackJackGame blackjack = new BlackJackGame();
        // When
        boolean actual = blackjack.beatDealer(dealerHand, yourHand);
        // Then
        assertEquals(false, actual);
    }
}