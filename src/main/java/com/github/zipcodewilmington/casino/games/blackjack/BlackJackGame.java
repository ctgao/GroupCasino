package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.HouseAccount;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.HashMap;

public class BlackJackGame extends CardGame implements GambleGameInterface {
    DealerPlayer dealer;
    HashMap<BlackJackPlayer, Integer> betAmounts;

    public BlackJackGame(IOConsole console){
        super();
        dealer = new DealerPlayer(console);
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
        dealer.setShowFirstCard(true);
        playerTurn(dealer);

        // now we can calculate each player's scores
        decideWhoWinsAndPayThem();

        // now do some clean up
        cleanUp();
    }

    private void cleanUp() {
        // reset all bets
        betAmounts.clear();
        // clear your hand of cards
        for(CardPlayer cp : super.getPlayers()){
            cp.clearHand();
        }
        // reset the deck
        getTheDeck().reclaimCards();
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
            } while(temp.validBet(betValue));
            betAmounts.put(temp, betValue);
            temp.makeBet(betValue);
            HouseAccount.getHouseAccount().acceptMoney(betValue);
        }
    }

    private void decideWhoWinsAndPayThem() {
        // find the dealer's score
        boolean busted = dealer.isHandBusted();
        int dealerScore = dealer.calculateScore();

        for(CardPlayer cp : super.getPlayers()) {
            BlackJackPlayer temp = (BlackJackPlayer) cp;
            // now calculate funds
            int betAmount = beatDealer(busted, dealerScore, temp) ? betAmounts.get(temp) : 0;
            int payout = payOutCalc(betAmount);
            temp.depositPayOut(payout);
            // also remove funds from the house account
            HouseAccount.getHouseAccount().payout(payout);
        }
    }

    /*
     * player busted = earn nothing gg
     * dealer busted but player didn't bust = good job bruh
     * dealer not busted but greater than player not busted = dealer win
     */
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
        // do you even need this??? - not sure yet
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
