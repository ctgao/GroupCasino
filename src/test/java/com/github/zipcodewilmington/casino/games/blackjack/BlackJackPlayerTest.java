package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackPlayerTest {

    @Test
    void isStayOrNot() {
    }

    @Test
    void play() {
    }

    @Test
    void makeBet() {
    }

    @Test
    void validBet() {
    }

    @Test
    void depositPayOut() {
    }

    @Test
    void hitMe() {
    }

    @Test
    void stay() {
    }

    @Test
    void calculateScoreTest() {
        // Given
        int expected = 12;
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        curHand.addCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE));
        // When
        int actual = new BlackJackPlayer(curHand).calculateScore();
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
        // When
        int actual = new BlackJackPlayer(curHand).calculateScore();
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
        // When
        int actual = new BlackJackPlayer(curHand).calculateScore();
        // Then
        assertEquals(expected, actual);
    }
}