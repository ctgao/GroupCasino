package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SpadesGameTest {
    @Test
    void getPlayerOrderTest() {
        // Given
        SpadesGame game = new SpadesGame();
        game.add(new SpadesPlayer(null));
        game.add(new SpadesPlayer(null));
        game.add(new SpadesPlayer(null));
        // When
        game.setPlayerOrder(2);
        int[] actual = game.getPlayerOrder();
        // Then
        assertEquals(true, Arrays.equals(actual, new int[]{2, 0, 1}));
    }
    @Test
    void getPlayerOrderTest1() {
        // Given
        SpadesGame game = new SpadesGame();
        game.add(new SpadesPlayer(null));
        game.add(new SpadesPlayer(null));
        game.add(new SpadesPlayer(null));
        // When
        game.setPlayerOrder(0);
        int[] actual = game.getPlayerOrder();
        // Then
        assertEquals(true, Arrays.equals(actual, new int[]{0, 1, 2}));
    }

    @Test
    void getRoundWinnerTest() {
        //Given
        SpadesGame game = new SpadesGame();
        game.add(null);
        game.add(null);
        game.add(null);
        game.setPlayerOrder(2);
        //Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.FIVE);
        PlayingCard pc1 = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE);
        PlayingCard pc2 = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.THREE);
        PlayingCard[] cards = new PlayingCard[]{pc, pc1, pc2};
        int expected = 2;
        //When
        int actual = game.getRoundWinner(cards);
        //Then
        assertEquals(expected, actual);
    }
    @Test
    void getRoundWinnerTest1() {
        //Given
        SpadesGame game = new SpadesGame();
        game.add(null);
        game.add(null);
        game.add(null);
        game.setPlayerOrder(1);
        //Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.FIVE);
        PlayingCard pc1 = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        PlayingCard pc2 = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.THREE);
        PlayingCard[] cards = new PlayingCard[]{pc, pc1, pc2};
        int expected = 2;
        //When
        int actual = game.getRoundWinner(cards);
        //Then
        assertEquals(expected, actual);
        assertEquals(true, game.isSpadesBroken());
    }

    @Test
    void isEndConditionTest() {
        //Given
        SpadesGame game = new SpadesGame();
        game.add(new SpadesPlayer(new HandOfCards()));
        game.add(new SpadesPlayer(new HandOfCards()));
        //When
        boolean actual = game.isEndCondition();
        //Then
        assertEquals(true, actual);
    }
    @Test
    void isEndConditionTest1() {
        //Given
        SpadesGame game = new SpadesGame();
        HandOfCards hand = new HandOfCards();
        hand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        game.add(new SpadesPlayer(hand));
        game.add(new SpadesPlayer(hand));
        //When
        boolean actual = game.isEndCondition();
        //Then
        assertEquals(false, actual);
    }

    @Test
    void getOverallWinnerTest(){
        //Given
        SpadesGame game = new SpadesGame();
        SpadesPlayer player = new SpadesPlayer(null);
        player.takeATrick();
        game.add(player);
        game.add(new SpadesPlayer(null));
        //When
        SpadesPlayer winner = game.getOverallWinner();
        //Then
        assertEquals(player.getScore(), winner.getScore());
        assertEquals(player, winner);
    }
}