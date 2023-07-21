package com.github.zipcodewilmington.casino.games.blackjack;

public class DealerPlayer extends BlackJackPlayer {
    private boolean showFirstCard;

    public DealerPlayer() {
        super(null, null);
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
    public void printHand(){
        // HIDE THAT FIRST CARD!
    }

}
