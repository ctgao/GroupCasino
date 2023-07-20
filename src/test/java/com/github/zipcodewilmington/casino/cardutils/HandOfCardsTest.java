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
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(pc);
        // When
        boolean actual = curHand.contains(pc);
        // Then
        assertEquals(true, actual);
    }

    @Test
    public void removeCardTest() {
        // Given
        int expectedSize = 2;
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        PlayingCard pc1 = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.ACE);
        PlayingCard pc2 = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.ACE);
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(pc);
        curHand.addCard(pc1);
        curHand.addCard(pc2);
        // When
        curHand.removeCard(pc1);
        // Then
        assertEquals(false, curHand.contains(pc1));
        assertEquals(expectedSize, curHand.size());
    }

    @Test
    public void howManyOfValueTest() {
        // Given
        int expected = 4;
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        PlayingCard pc1 = new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.ACE);
        PlayingCard pc2 = new PlayingCard(PlayingCardSuit.CLUBS, PlayingCardValue.ACE);
        PlayingCard pc3 = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE);
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(pc);
        curHand.addCard(pc1);
        curHand.addCard(pc2);
        curHand.addCard(pc3);
        // When
        int actual = curHand.howManyOfValue(PlayingCardValue.ACE);
        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void containsSuitTest1() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        PlayingCard pc3 = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE);
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(pc);
        curHand.addCard(pc3);
        // When
        boolean actual = curHand.containsSuit(PlayingCardSuit.DIAMONDS);
        // Then
        assertEquals(true, actual);
    }
    @Test
    public void containsSuitTest2() {
        // Given
        PlayingCard pc = new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE);
        PlayingCard pc3 = new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE);
        HandOfCards curHand = new HandOfCards();
        curHand.addCard(pc);
        curHand.addCard(pc3);
        // When
        boolean actual = curHand.containsSuit(PlayingCardSuit.HEARTS);
        // Then
        assertEquals(false, actual);
    }
}