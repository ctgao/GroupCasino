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

        // make bets here
        logPlayerBets();

        // for each player, ask them how they wanna play
        for(CardPlayer cp : super.getPlayers()){
            BlackJackPlayer temp = (BlackJackPlayer) cp;
            if(!(temp instanceof DealerPlayer)) {
                // play as long as you aren't the dealer
                playerTurn(temp);
            }
        }

        // now the dealer takes their turn
        dealer.play();

        // now we can calculate each player's scores
        decideWhoWinsAndPayThem();

        // now do some clean up
        betAmounts.clear();
    }

    public void playerTurn(BlackJackPlayer player) {
        do{
            // while they wanna stay, play
            String playerChoice = player.play();
            if(playerChoice.equals("HIT")){
                player.hitMe(this.getTheDeck().drawCard());
                if(player.isHandBusted()){
                    player.printToConsole("YOU BUSTED! RIP");
                    player.stay();
                }
            }
            else{
                player.stay();
            }
        } while(!player.isStayOrNot());
    }

    void logPlayerBets() {
        for(CardPlayer cp : super.getPlayers()) {
            BlackJackPlayer temp = (BlackJackPlayer) cp;
            int betValue;
            do {
                betValue = temp.promptPlayerFoMoney("How much do you wanna bet?");
            }while(temp.validBet(betValue));
            betAmounts.put(temp, betValue);
            temp.makeBet(betValue);
        }
    }

    /*
     * player busted = earn nothing gg
     * dealer busted but player didn't bust = good job bruh
     * dealer not busted but greater than player not busted = dealer win
     */
    private void decideWhoWinsAndPayThem() {
        // find the dealer's score
        boolean busted = dealer.isHandBusted();
        int dealerScore = dealer.calculateScore();

        for(CardPlayer cp : super.getPlayers()) {
            BlackJackPlayer temp = (BlackJackPlayer) cp;
            // now calculate funds
            int payout = beatDealer(busted, dealerScore, temp) ? betAmounts.get(temp) : 0;
            temp.depositPayOut(payout);
        }
    }

    public boolean beatDealer(boolean dealerBusted, int dealerScore, BlackJackPlayer player){
        // get the player's score
        int playerScore = player.calculateScore();

        if(player.isHandBusted()){
            return false;
        }
        else{
            if(dealerBusted){
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
        int dealerScore = dealer.calculateScore();
        // iterate over the players and see whose score is above the dealer
        for(CardPlayer cp : getPlayers()){
            BlackJackPlayer player = (BlackJackPlayer) cp;
            int playerScore = player.calculateScore();
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

    public int payOutCalc(int betAmount) {
        return payOutCalc(betAmount, 2);
    }
    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        return betAmount * payOutMult;
    }
}
