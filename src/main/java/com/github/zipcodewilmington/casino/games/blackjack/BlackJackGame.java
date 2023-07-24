package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.HouseAccount;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.HashMap;

public class BlackJackGame extends CardGame implements GambleGameInterface {
    DealerPlayer dealer;
    HashMap<BlackJackPlayer, Integer> betAmounts;
    final IOConsole blackjackMenu = new IOConsole(AnsiColor.CYAN);

    public BlackJackGame(){
        super();
        dealer = new DealerPlayer();
        add(dealer);
        betAmounts = new HashMap<>();
    }

    @Override
    public void run() {
        // print a welcome statement!
        welcome();
        boolean continueOrNot;
        do {
            // deal 2 cards per player
            dealCards(2);
            // make bets here
            logPlayerBets();

            // for each player, ask them how they wanna play
            for (CardPlayer cp : super.getPlayers()) {
                BlackJackPlayer temp = (BlackJackPlayer) cp;
                if (!(temp instanceof DealerPlayer)) {
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

            //ask for another round
            continueOrNot = wannaLoseMoreMoney();
        } while(continueOrNot);
    }

    private boolean wannaLoseMoreMoney() {
        for(CardPlayer cp : super.getPlayers()) {
            if(!(cp instanceof DealerPlayer)) {
                BlackJackPlayer player = (BlackJackPlayer) cp;
                return player.promptPlayerToPlayAgain("Wanna try your luck once more?");
            }
        }
        return false;
    }

    private void welcome() {
        blackjackMenu.println("\nWelcome to the illustrious BlackJack table!");
        blackjackMenu.println("You'll be playing against the Dealer in a race to reach a sum of 21");
        blackjackMenu.println("Money's up for grabs, but can you REALLY beat the Dealer?\n");
    }

    private void cleanUp() {
        // reset all bets
        betAmounts.clear();
        // clear your hand of cards
        for(CardPlayer cp : super.getPlayers()){
            cp.clearHand();
            ((BlackJackPlayer) cp).resetStay();
        }
        // reset the deck
        getTheDeck().reclaimCards();
        dealer.setShowFirstCard(false);
    }

    public void playerTurn(BlackJackPlayer player) {
        if(!(player instanceof DealerPlayer)) {
            dealer.printHand();
        }
        do{
            // show the hand first
            player.printHand();

            // check for busted in the beginning of the round so you can see your hand
            if(player.isHandBusted()){
                player.printToConsole(player.getBustedStatement());
                return;
            }
            // while they wanna stay, play
            String playerChoice = player.play();
            if(playerChoice.equals("HIT")){
                player.hitMe(this.getTheDeck().drawCard());
            }
            else{
                player.stay();
            }
            blackjackMenu.println("");
        } while(!player.isStayOrNot());
    }

    void logPlayerBets() {
        for(CardPlayer cp : super.getPlayers()) {
            if(!(cp instanceof DealerPlayer)) {
                BlackJackPlayer temp = (BlackJackPlayer) cp;
                // check to see if they wanna try and play this game with no money bc WTF
                if (temp.getWallet() == 0){
                    Casino.invalidGambler();
                }
                int betValue;
                do {
                    betValue = temp.promptPlayerFoMoney("How much do you wanna bet?");
                    if(!temp.validBet(betValue)){
                        blackjackMenu.println(String.format("STOP IT! You're poor! You only have %d in your account!\n\n", temp.getWallet()));
                    }
                } while (!temp.validBet(betValue));

                // make the bet transactions
                betAmounts.put(temp, betValue);
                temp.makeBet(betValue);
                HouseAccount.getHouseAccount().acceptMoney(betValue);
            }
        }
        blackjackMenu.println("");
    }

    private void decideWhoWinsAndPayThem() {
        // find the dealer's score
        boolean busted = dealer.isHandBusted();
        int dealerScore = dealer.calculateScore();

        for(CardPlayer cp : super.getPlayers()) {
            if(!(cp instanceof DealerPlayer)) {
                BlackJackPlayer temp = (BlackJackPlayer) cp;
                // now calculate funds
                int betAmount = beatDealer(busted, dealerScore, temp) ? betAmounts.get(temp) : 0;
                int payout = payOutCalc(betAmount);
                // NOW TELL THE PLAYER
                if(payout != 0) {
                    blackjackMenu.println("WINNER WINNER, CHICKEN DINNER!\n");
                }
                else{
                    blackjackMenu.println("Pour one out for money now gone :)\n");
                }
                temp.depositPayOut(payout);
                // also remove funds from the house account
                HouseAccount.getHouseAccount().payout(payout);
            }
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
