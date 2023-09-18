package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerPlayerTest {
    @Test
    void inheritanceTest(){
        //Given
        BlackJackPlayer dealer = new DealerPlayer();
        //When
        //Then
        assertEquals(true, dealer instanceof DealerPlayer);
    }

    @Test
    void isShowFirstCardTest() {
        //Given
        DealerPlayer dealer = new DealerPlayer();
        //When
        boolean actual = dealer.isShowFirstCard();
        //Then
        assertEquals(false, actual);
    }
    @Test
    void isShowFirstCardTest1() {
        //Given
        DealerPlayer dealer = new DealerPlayer();
        dealer.setShowFirstCard(true);
        //When
        boolean actual = dealer.isShowFirstCard();
        //Then
        assertEquals(true, actual);
    }

    @Test
    void playTest() {
        //Given
        String expected = "HIT";
        BlackJackPlayer dealer = new DealerPlayer();
        // the dealer has a current hand of 11 + 2
        dealer.receiveCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE));
        dealer.receiveCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.TWO));
        //When
        String actualChoice = dealer.play();
        //Then
        assertEquals(expected, actualChoice);
    }
    @Test
    void playTest1() {
        //Given
        String expected = "STAY";
        BlackJackPlayer dealer = new DealerPlayer();
        // the dealer has a current hand of 11 + 6
        dealer.receiveCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE));
        dealer.receiveCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.SIX));
        //When
        String actualChoice = dealer.play();
        //Then
        assertEquals(expected, actualChoice);
    }

    @Test
    void toStringTest() {
        //Given
        String expected = "\nDealer's Hand: [HIDDEN, 6 ♦]";
        BlackJackPlayer dealer = new DealerPlayer();
        dealer.receiveCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.ACE));
        dealer.receiveCard(new PlayingCard(PlayingCardSuit.DIAMONDS, PlayingCardValue.SIX));
        //When
        String actual = dealer.toString();
        //Then
        assertEquals(expected, actual);
    }
}