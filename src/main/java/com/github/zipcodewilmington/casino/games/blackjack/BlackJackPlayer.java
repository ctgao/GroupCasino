package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblerInterface;
import com.github.zipcodewilmington.casino.cardutils.HandOfCards;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import com.github.zipcodewilmington.utils.IOConsole;

public class BlackJackPlayer extends CardPlayer implements GamblerInterface {
    private boolean stayOrNot;

    public BlackJackPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
        stayOrNot = false;
    }

    public BlackJackPlayer(HandOfCards hand) {
        super(hand);
        stayOrNot = false;
    }

    // true means you decided to stay, false means you're good to keep going
    public boolean isStayOrNot() {
        return stayOrNot;
    }
    public void resetStay() {
        stayOrNot = false;
    }

    @Override
    public void sortHand() {
        // this does nothing because there's nothing to sort
    }

    @Override
    public <String> String play() {
        // now ask for input
        String choice = (String) promptPlayerForChoice("Would you like to get HIT or STAY?");
        if(choice.toString().toUpperCase().equals("HIT") || choice.toString().toUpperCase().equals("STAY")) {
            choice = (String) choice.toString().toUpperCase();
            return choice;
        }
        throw new RuntimeException("Not a VALID BlackJack option. SHAME!");
    }

    @Override
    public void makeBet(int bet) {
        // when we make a bet, we update our account with neg money
        this.getCasinoAccount().updateAccBalance(-1 * bet);
    }

    @Override
    public boolean validBet(int bet) {
        return this.getWallet() >= bet && this.getWallet() > 0;
    }

    @Override
    public void depositPayOut(int winnings) {
        this.getCasinoAccount().updateAccBalance(winnings);
    }

    public void hitMe(PlayingCard pc){
        this.receiveCard(pc);
    }

    public void stay(){
        stayOrNot = true;
    }

    public int calculateScore(){
        int result = 0;
        int aceCount = 0;

        // go through each of the cards in the hand
        for(PlayingCard pc : getHandOfCards()){
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

    public boolean isHandBusted() {
        return busted(calculateScore());
    }

    // face cards are J, Q, K
    private boolean isFaceCard(PlayingCard pc){
        PlayingCardValue val = pc.getValue();
        return (val.equals(PlayingCardValue.JACK) || val.equals(PlayingCardValue.QUEEN) || val.equals(PlayingCardValue.KING));
    }

    public String getBustedStatement() {
        return "YOU BUSTED! RIP\n";
    }
}
