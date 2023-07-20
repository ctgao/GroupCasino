package com.github.zipcodewilmington.casino.cardutils;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandOfCardsTest {
    /*
     * Note: this test is unnecessary, but I wrote it in to test if I understood how extension works
     * I also do think this works only because I implemented .equals() for PlayingCard
     */
    @Test
    public void containsTest(){
        // Given
        HandOfCards curHand = new HandOfCards();
        curHand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        // When
        boolean actual = curHand.contains(pc);
        // Then
        assertEquals(true, actual);
    }

    @Test
    public void addCardTest() {
    }

    @Test
    public void removeCardTest() {
    }

    @Test
    public void containsValueTest() {
    }

    @Test
    public void containsSuitTest() {
    }
}