package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public class DealerPlayer extends BlackJackPlayer {
    private boolean showFirstCard;

    public DealerPlayer() {
        super(null, new IOConsole(AnsiColor.PURPLE));
        showFirstCard = false;
    }

    public boolean isShowFirstCard() {
        return showFirstCard;
    }

    public void setShowFirstCard(boolean showFirstCard) {
        this.showFirstCard = showFirstCard;
    }

    @Override
    public <String> String play() {
        // make the decision
        if(calculateScore() < 17){
            return (String) "HIT";
        }
        else{
            return (String) "STAY";
        }
    }

    @Override
    public void hitMe(PlayingCard pc){
        super.hitMe(pc);
        printToConsole("Dealer chose to HIT");
    }

    @Override
    public void stay(){
        super.stay();
        printToConsole("Dealer chose to STAY");
    }

    @Override
    public void printHand(){
        // print the status part with auto
        this.getPlayerInput().print("Dealer's Hand: ");
        printHand(!showFirstCard);
    }

    @Override
    public String toString(){
        String result = this.getHandOfCards().toString();
        if(!showFirstCard) {
            // hide that first card
            String firstCard = this.getHandOfCards().get(0).toString();
            result = result.replace(firstCard, "HIDDEN");
        }
        return String.format("\nDealer's Hand: %s", result);
    }

    @Override
    public String getBustedStatement() {
        return "Dealer BUSTED! OMG!\n";
    }
}
