package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.utils.IOConsole;

public class DealerPlayer extends BlackJackPlayer {
    private boolean showFirstCard;

    public DealerPlayer(IOConsole console) {
        super(null, console);
        showFirstCard = false;
    }

    public boolean isShowFirstCard() {
        return showFirstCard;
    }

    public void setShowFirstCard(boolean showFirstCard) {
        this.showFirstCard = showFirstCard;
    }

    @Override
    public <SomeReturnType> String play() {
        // make the decision
        if(calculateScore() < 17){
            return "HIT";
        }
        else{
            return "STAY";
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
        super.printToConsole(toString());
    }

    @Override
    public String toString(){
        String result = this.getHandOfCards().toString();
        if(!showFirstCard) {
            // hide that first card
            String firstCard = this.getHandOfCards().get(0).toString();
            result = result.replace(firstCard, "HIDDEN");
        }
        return String.format("\nDealer Hand: %s", result);
    }

    @Override
    public String getBustedStatement() {
        return "Dealer BUSTED! OMG!";
    }
}
