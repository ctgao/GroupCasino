package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;

import java.util.HashMap;

public class BlackJackGame extends CardGame implements GambleGameInterface {
    DealerPlayer dealer;
    HashMap<BlackJackPlayer, Integer> betAmounts;

    public BlackJackGame(){
        super();
        dealer = new DealerPlayer();
        add(dealer);
    }

    @Override
    public void run() {
        // deal 2 cards per player
        dealCards(2);

        // for each player, ask them how they wanna play
        for(CardPlayer cp : super.getPlayers()){
            BlackJackPlayer temp = (BlackJackPlayer) cp;
            if(!(temp instanceof DealerPlayer)) {
                // play as long as you aren't the dealer
                temp.play();
            }
        }

        // now the dealer takes their turn
        dealer.play();

        // now we can calculate each player's scores
        decideWhoWinsAndPayThem();
    }

    /*
     * player busted = earn nothing gg
     * dealer busted but player didn't bust = good job bruh
     * dealer not busted but greater than player not busted = dealer win
     */
    private void decideWhoWinsAndPayThem() {
        // find the dealer's score
        int dealerScore = calculateScore(dealer.getHandOfCards());

        for(CardPlayer cp : super.getPlayers()) {
            BlackJackPlayer temp = (BlackJackPlayer) cp;
            // get the player's score
            int playerScore = calculateScore(temp.getHandOfCards());
            // now calculate funds
            int payout = beatDealer(dealerScore, playerScore) ? betAmounts.get(temp) : 0;
            temp.depositPayOut(payout);
        }
    }

    public boolean beatDealer(int dealerScore, int playerScore){
        if(busted(playerScore)){
            return false;
        }
        else{
            if(busted(dealerScore)){
                return true;
            }
            else if (dealerScore > playerScore) {
                return false;
            }
            else{
                return true;
            }
        }
    }

    @Override
    public void printWinner() {
        int dealerScore = calculateScore(dealer.getHandOfCards());
        // iterate over the players and see whose score is above the dealer
        for(CardPlayer cp : getPlayers()){
            BlackJackPlayer player = (BlackJackPlayer) cp;
            int playerScore = calculateScore(player.getHandOfCards());
            if(playerScore > dealerScore){
                // then they can get printed out? idk yet
            }
        }

    }

    @Override
    public boolean isEndCondition() {
        // the game of blackjack ends when the dealer has taken their turn
        // not sure if we'll ever use this since run will never use this
        return false;
    }

    public int calculateScore(HandOfCards hand){
        int result = 0;
        int aceCount = 0;

        // go through each of the cards in the hand
        for(PlayingCard pc : hand){
            if(isFaceCard(pc)){
                // face cards are worth 10
                result += 10;
            }
            else if(!pc.getValue().equals(PlayingCardValue.ACE)){
                // not an ace
                result += pc.getValue().getNumericalVal();
            }
            else{
                aceCount++;
            }
        }

        // now deals with aces
        for(int i = 0; i < aceCount; i++){
            if(busted(result + 11)){
                // busted if you add 11 means you add 1
                result += 1;
            }
            else{
                result += 11;
            }
        }
        return result;
    }

    // busted means you have more than 21 as your hand value
    private boolean busted(int i) {
        return (i > 21);
    }

    // face cards are J, Q, K
    private boolean isFaceCard(PlayingCard pc){
        PlayingCardValue val = pc.getValue();
        return (val.equals(PlayingCardValue.JACK) || val.equals(PlayingCardValue.QUEEN) || val.equals(PlayingCardValue.KING));
    }

    public int payOutCalc(int betAmount) {
        return payOutCalc(betAmount, 2);
    }
    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        return betAmount * payOutMult;
    }
}
