package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SpadesPlayerTest {

    @Test
    void isHumanPlayerTest() {
        // Given
        SpadesPlayer player = new SpadesPlayer(null);
        // When
        boolean actual = player.isHumanPlayer();
        // Then
        assertEquals(false, actual);
    }
    @Test
    void isHumanPlayerTest1() {
        // Given
        SpadesPlayer player = new SpadesPlayer(null, null);
        // When
        boolean actual = player.isHumanPlayer();
        // Then
        assertEquals(false, actual);
    }
    @Test
    void isHumanPlayerTest2() {
        // Given
        SpadesPlayer player = new SpadesPlayer(new CasinoAccount("", "", 0), null);
        // When
        boolean actual = player.isHumanPlayer();
        // Then
        assertEquals(true, actual);
    }

    @Test
    void setHumanPlayerTest() {
        // Given
        SpadesPlayer player = new SpadesPlayer(null);
        // When
        player.setHumanPlayer(true);
        // When
        boolean actual = player.isHumanPlayer();
        // Then
        assertEquals(true, actual);
    }

    @Test
    void sortHandTest() {
        //Given
        HandOfCards hand = new HandOfCards();
        hand.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE));
        hand.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING));
        hand.addCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.THREE));
        hand.addCard(new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.QUEEN));
        hand.addCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.JACK));
        hand.addCard(new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.ACE));
        SpadesPlayer player = new SpadesPlayer(hand);
        //Given
        HandOfCards expectedHand = new HandOfCards();
        expectedHand.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING));
        expectedHand.addCard(new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.QUEEN));
        expectedHand.addCard(new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.ACE));
        expectedHand.addCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.THREE));
        expectedHand.addCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.JACK));
        expectedHand.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE));
        //When
        player.sortHand();
        //Then
        assertEquals(true, expectedHand.equals(player.getHandOfCards()));
    }

    @Test
    void validateCardTest() {
        // Given
        HandOfCards hands = new HandOfCards();
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING);
        hands.addCard(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.QUEEN));
        SpadesPlayer player = new SpadesPlayer(hands);
        // When - test for a card that doesn't exist in hand
        boolean actual = player.validateCard(null, pc, false);
        // Then
        assertEquals(false, actual);
    }
    @Test
    void validateCardTest1() {
        // Given
        HandOfCards hands = new HandOfCards();
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING);
        hands.addCard(pc);
        SpadesPlayer player = new SpadesPlayer(hands);
        // When - no leading suit and can play spades
        boolean actual = player.validateCard(null, pc, true);
        // Then
        assertEquals(true, actual);
    }
    @Test
    void validateCardTest2() {
        // Given
        HandOfCards hands = new HandOfCards();
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING);
        hands.addCard(pc);
        SpadesPlayer player = new SpadesPlayer(hands);
        // When - no leading suit and can't play spades
        boolean actual = player.validateCard(null, pc, false);
        // Then
        assertEquals(false, actual);
    }
    @Test
    void validateCardTest3() {
        // Given
        HandOfCards hands = new HandOfCards();
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING);
        hands.addCard(pc);
        SpadesPlayer player = new SpadesPlayer(hands);
        // When - is leading suit and can play spades
        boolean actual = player.validateCard(PlayingCardSuit.SPADES, pc, true);
        // Then
        assertEquals(true, actual);
    }
    @Test
    void validateCardTest4() {
        // Given
        HandOfCards hands = new HandOfCards();
        PlayingCard pc = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.KING);
        hands.addCard(pc);
        hands.addCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.ACE));
        SpadesPlayer player = new SpadesPlayer(hands);
        // When - isn't the leading suit but contains said suit
        boolean actual = player.validateCard(PlayingCardSuit.HEARTS, pc, true);
        // Then
        assertEquals(false, actual);
    }
    @Test
    void validateCardTest5() {
        // Given
        HandOfCards hands = new HandOfCards();
        PlayingCard pc = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.KING);
        hands.addCard(pc);
        SpadesPlayer player = new SpadesPlayer(hands);
        // When - isn't leading suit and can't play spades
        boolean actual = player.validateCard(PlayingCardSuit.HEARTS, pc, false);
        // Then - i have to play this card despite the fact that the leading suit doesn't match
        assertEquals(true, actual);
    }

    @Test
    void getScoreTest() {
        // Given
        int expected = 0;
        SpadesPlayer player = new SpadesPlayer(null);
        // When
        int actual = player.getScore();
        // Then
        assertEquals(expected, actual);
    }

    @Test
    void takeATrickTest() {
        //Given
        int expected = 1;
        SpadesPlayer player = new SpadesPlayer(null);
        //When
        player.takeATrick();
        int actual = player.getScore();
        //Then
        assertEquals(expected, actual);
    }

    @Test
    void incrementWinsTest() {
        // Given
        int expected = 1;
        int expected1 = 1;
        SpadesPlayer player = new SpadesPlayer(null);
        // When
        player.incrementWins(true);
        // When
        int actual = player.getTotalGamesWon();
        int actual1 = player.getTotalGamesPlayed();
        // Then
        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }
    @Test
    void incrementWinsTest1() {
        // Given
        int expected = 0;
        int expected2 = 1;
        SpadesPlayer player = new SpadesPlayer(null);
        // When
        player.incrementWins(false);
        // When
        int actual = player.getTotalGamesWon();
        int actual2 = player.getTotalGamesPlayed();
        // Then
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);
    }
}