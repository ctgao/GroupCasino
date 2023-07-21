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
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

    @Override
    public void printHand(){
        // HIDE THAT FIRST CARD!
    }

}
