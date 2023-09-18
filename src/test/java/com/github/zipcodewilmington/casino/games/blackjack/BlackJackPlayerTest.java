package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.HouseAccount;
import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackPlayerTest {
    @Test
    void compareToTest(){
        //Given
        int expected = 0;
        HandOfCards hand = new HandOfCards();
        hand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        hand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING));
        //Given
        BlackJackPlayer player = new BlackJackPlayer(hand);
        DealerPlayer dealer = new DealerPlayer(hand);
        //When
        int actual = player.compareTo(dealer);
        //Then
        assertEquals(expected, actual);
    }
    @Test
    void compareToTest1(){
        //Given
        int expected = -1;
        HandOfCards hand = new HandOfCards();
        hand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        hand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING));
        HandOfCards dealerHand = new HandOfCards();
        hand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        hand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.QUEEN));
        //Given
        BlackJackPlayer player = new BlackJackPlayer(hand);
        DealerPlayer dealer = new DealerPlayer(dealerHand);
        //When
        int actual = player.compareTo(dealer);
        //Then
        assertEquals(expected, actual);
    }
    @Test
    void compareToTest2(){
        //Given
        int expected = -1;
        HandOfCards hand = new HandOfCards();
        hand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        hand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.THREE));
        HandOfCards dealerHand = new HandOfCards();
        dealerHand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        dealerHand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.QUEEN));
        //Given
        BlackJackPlayer player = new BlackJackPlayer(hand);
        DealerPlayer dealer = new DealerPlayer(dealerHand);
        //When
        int actual = player.compareTo(dealer);
        //Then
        assertEquals(expected, actual);
    }
    @Test
    void compareToTest3(){
        //Given
        int expected = -1;
        HandOfCards busted = new HandOfCards();
        busted.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        busted.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        busted.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.THREE));
        HandOfCards dealerHand = new HandOfCards();
        dealerHand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        dealerHand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.QUEEN));
        //Given
        BlackJackPlayer player = new BlackJackPlayer(busted);
        DealerPlayer dealer = new DealerPlayer(dealerHand);
        //When
        int actual = player.compareTo(dealer);
        //Then
        assertEquals(expected, actual);
    }
    @Test
    void compareToTest4(){
        //Given
        int expected = -1;
        HandOfCards busted = new HandOfCards();
        busted.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        busted.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        busted.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.THREE));
        //Given
        BlackJackPlayer player = new BlackJackPlayer(busted);
        DealerPlayer dealer = new DealerPlayer(busted);
        //When
        int actual = player.compareTo(dealer);
        //Then
        assertEquals(expected, actual);
    }
    @Test
    void compareToTest5(){
        //Given
        int expected = 1;
        HandOfCards hand = new HandOfCards();
        hand.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.ACE));
        hand.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.QUEEN));
        HandOfCards busted = new HandOfCards();
        busted.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        busted.add(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.KING));
        busted.add(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.THREE));
        //Given
        BlackJackPlayer player = new BlackJackPlayer(hand);
        DealerPlayer dealer = new DealerPlayer(busted);
        //When
        int actual = player.compareTo(dealer);
        //Then
        assertEquals(expected, actual);
    }

    @Test
    void isStayOrNotTest() {
        //Given
        BlackJackPlayer player = new BlackJackPlayer(null);
        //When
        boolean actual = player.isStayOrNot();
        //Then
        assertEquals(false, actual);
    }
    @Test
    void stayTest() {
        //Given
        BlackJackPlayer player = new BlackJackPlayer(null);
        //When
        player.stay();
        //When
        boolean actual = player.isStayOrNot();
        //Then
        assertEquals(true, actual);
    }

    @Test
    void makeBet() {
        // Given
        int expected = 100;
        CasinoAccount account = new CasinoAccount(null, null, expected);
        BlackJackPlayer player = new BlackJackPlayer(account, null);
        // When
        player.makeBet(10);
        int actualWallet = player.getWallet();
        // Then
        assertEquals(expected - 10, actualWallet);
    }

    @Test
    void validBetTest() {
        // Given
        CasinoAccount account = new CasinoAccount(null, null, 0);
        BlackJackPlayer player = new BlackJackPlayer(account, null);
        // When
        boolean actual = player.validBet(100);
        // Then
        assertEquals(false, actual);
    }
    @Test
    void validBetTest1() {
        // Given
        CasinoAccount account = new CasinoAccount(null, null, 100);
        BlackJackPlayer player = new BlackJackPlayer(account, null);
        // When
        boolean actual = player.validBet(100);
        // Then
        assertEquals(true, actual);
    }

    @Test
    void hitMeTest() {
        //Given
        BlackJackPlayer player = new BlackJackPlayer(new HandOfCards());
        //When
        player.hitMe(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE));
        //When
        HandOfCards hand = player.getHandOfCards();
        //Then
        assertEquals(1, hand.size());
    }
    @Test
    void hitMeTest1() {
        //Given
        BlackJackPlayer player = new BlackJackPlayer(new HandOfCards());
        player.receiveCard(new PlayingCard(PlayingCardSuit.HEARTS, PlayingCardValue.KING));
        //When
        player.hitMe(new PlayingCard(PlayingCardSuit.SPADES, PlayingCardValue.FIVE));
        //When
        HandOfCards hand = player.getHandOfCards();
        //Then
        assertEquals(2, hand.size());
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