package com.github.zipcodewilmington.casino.cardutils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayingCardTest {
    @Test
    public void constructorTest() {
        // Given
        PlayingCardSuit expectedSuit = PlayingCardSuit.HEARTS;
        PlayingCardValue expectedValue = PlayingCardValue.FIVE;
        // When
        PlayingCard pc = new PlayingCard(expectedSuit, expectedValue);
        // When
        PlayingCardSuit actualSuit = pc.getSuit();
        PlayingCardValue actualValue = pc.getValue();
        // Then
        assertEquals(expectedValue, actualValue);
        assertEquals(expectedSuit, actualSuit);
    }

    @Test
    public void getSuitTest() {
        // Given
        PlayingCardSuit expectedSuit = PlayingCardSuit.HEARTS;
        // When
        PlayingCard pc = new PlayingCard(expectedSuit, PlayingCardValue.ACE);
        // When
        PlayingCardSuit actualSuit = pc.getSuit();
        // Then
        assertEquals(expectedSuit, actualSuit);
    }

    @Test
    public void getValueTest() {
        // Given
        PlayingCardValue expectedValue = PlayingCardValue.FIVE;
        // When
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, expectedValue);
        // When
        PlayingCardValue actualValue = pc.getValue();
        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void compareToTest1() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.FIVE);
        // When
        int actual = pc.compareTo(otherCard);
        // Then
        assertEquals(true, actual > 0); // should be greater than
    }
    @Test
    public void compareToTest2() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.THREE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.FIVE);
        // When
        int actual = pc.compareTo(otherCard);
        // Then
        assertEquals(true, actual < 0); // should be less than
    }
    @Test
    public void compareToTest3() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.THREE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.THREE);
        // When
        int actual = pc.compareTo(otherCard);
        // Then
        assertEquals(true, actual == 0); // should be EQUAL
    }
    @Test
    public void compareToTest4() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.THREE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.THREE);
        // When
        int actual = pc.compareTo(otherCard);
        // Then
        assertEquals(true, actual > 0); // should be greater than
    }

    @Test
    public void toStringTest1() {
        // Given
        String expected = "5 of Diamonds";
        PlayingCard pc = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.FIVE);
        // When
        String actual = pc.toString();
        // Then
        assertEquals(expected, actual); // should be equal
    }
    @Test
    public void toStringTest2() {
        // Given
        String expected = "A of Spades";
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        // When
        String actual = pc.toString();
        // Then
        assertEquals(expected, actual); // should be equal
    }
    @Test
    public void toStringTest3() {
        // Given
        String expected = "J of Clubs";
        PlayingCard pc = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.JACK);
        // When
        String actual = pc.toString();
        // Then
        assertEquals(expected, actual); // should be equal
    }
    @Test
    public void toStringTest4() {
        // Given
        String expected = "K of Hearts";
        PlayingCard pc = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING);
        // When
        String actual = pc.toString();
        // Then
        assertEquals(expected, actual); // should be equal
    }

    @Test
    public void equalsTest1() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.FIVE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.FIVE);
        // When
        boolean regularEquals = pc == otherCard;
        boolean dotEquals = pc.equals(otherCard);
        // Then: Cards are not equal
        assertEquals(false, regularEquals);
        assertEquals(false, dotEquals);
    }
    @Test
    public void equalsTest2() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE);
        // When
        boolean regularEquals = pc == otherCard;
        boolean dotEquals = pc.equals(otherCard);
        // Then: Cards ARE equal
        assertEquals(false, regularEquals);
        assertEquals(true, dotEquals);
    }
    @Test
    public void equalsTest3() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE);
        PlayingCard otherCard = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.FIVE);
        // When
        boolean regularEquals = pc == otherCard;
        boolean dotEquals = pc.equals(otherCard);
        // Then: Cards are not equal
        assertEquals(false, regularEquals);
        assertEquals(false, dotEquals);
    }
}