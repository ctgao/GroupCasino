package com.github.zipcodewilmington.casino.cardutils;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TheDeckTest {
    @Test
    public void reclaimCardsTest1() {
        //Given
        TheDeck theDeck = new TheDeck();
        PlayingCard[] initialDeck = theDeck.getDeck();
        //When
        theDeck.drawCard();
        theDeck.reclaimCards();
        //When
        PlayingCard[] actualDeck = theDeck.getDeck();
        //Then
        assertEquals(true, Arrays.equals(initialDeck, actualDeck));
    }
    @Test
    public void reclaimCardsTest2() {
        //Given
        int expected = 52;
        TheDeck theDeck = new TheDeck();
        //When
        theDeck.drawCard();
        theDeck.reclaimCards();
        //When
        PlayingCard[] actualDeck = theDeck.getDeck();
        //Then
        assertEquals(expected, actualDeck.length);
    }

    @Test
    public void shuffleTest1() {
        //Given
        TheDeck theDeck = new TheDeck();
        PlayingCard[] initialDeck = theDeck.getDeck();
        //When
        theDeck.shuffle();
        //When
        PlayingCard[] actualDeck = theDeck.getDeck();
        //Then: the two arrays aren't equal because their orders are different
        assertEquals(false, Arrays.equals(initialDeck, actualDeck));
    }
    @Test
    public void shuffleTest2() {
        //Given
        TheDeck actualDeck = new TheDeck();
        PlayingCard[] initialDeck = actualDeck.getDeck();
        //When
        actualDeck.shuffle();
        //Then: the stack contains all the elements in the initial deck array
        assertEquals(true, actualDeck.containsAll(Arrays.asList(initialDeck)));
    }
    @Test
    public void shuffleTest3() {
        //Given
        TheDeck theDeck = new TheDeck();
        TheDeck secondDeck = new TheDeck();
        //When
        theDeck.shuffle();
        secondDeck.shuffle();
        //When
        PlayingCard[] initialDeck = theDeck.getDeck();
        PlayingCard[] otherDeck = secondDeck.getDeck();
        //Then: two decks shuffle differently
        assertEquals(false, Arrays.equals(initialDeck, otherDeck));
    }

    @Test
    public void drawCardTest() {
        //Given
        int expectedSize = 51;
        TheDeck theDeck = new TheDeck();
        //When
        theDeck.drawCard();
        PlayingCard[] actualArray = theDeck.getDeck();
        //Then
        assertEquals(expectedSize, actualArray.length);
    }

    @Test
    public void hasCardsLeftTest1() {
        //Given
        TheDeck theDeck = new TheDeck();
        //When
        boolean actual = theDeck.hasCardsLeft();
        //Then
        assertEquals(true, actual);
    }
    @Test
    public void hasCardsTest2() {
        //Given
        TheDeck theDeck = new TheDeck();
        for(int i = 0; i < 52; i++){
            theDeck.drawCard();
        }
        //When
        boolean actual = theDeck.hasCardsLeft();
        //Then
        assertEquals(false, actual);
    }

    @Test
    public void constructorTest1() {
        //Given
        int expectedSize = 52;
        TheDeck theDeck = new TheDeck();
        //When
        PlayingCard[] actualDeck = theDeck.getDeck();
        //Then
        assertEquals(expectedSize, actualDeck.length);
    }
    @Test
    public void constructorTest2() {
        //Given
        int expectedSize = 52;
        TheDeck theDeck = new TheDeck();
        //When
        HashSet<PlayingCard> initialSet = new HashSet<>(theDeck);
        //Then
        assertEquals(expectedSize, initialSet.size());
    }
}