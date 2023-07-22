package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;

import javax.smartcardio.Card;

public class SpadesGame extends CardGame {
    boolean spadesBroken;
    int[] playerOrder;

    public SpadesGame(){
        super();
        // TRY TO CHANGE HOW THE PLAYER ARRAY LIST IS INSTANTIATED PER CARD PLAYER CLASS TO SEE IF IT'LL BE EASIER THAT WAY
        spadesBroken = false;
    }

    @Override
    public void run() {
        // set the player order first
        playerOrder = new int[getPlayers().size()];
        setPlayerOrder(0);

        // deal the cards to all players
        // temporarily hard coded?
        int handSize = 13;
        dealCards(handSize);
        // have all players sort their hands first
        forceSort();

        // play a round until everyone's hands are empty
        while(!isEndCondition()){
            // play through a round
            PlayingCard[] curCards = playRound();
            // get the winner
            int indexOfWinningPlayer = getRoundWinner(curCards);
            SpadesPlayer winner = (SpadesPlayer) getPlayers().get(indexOfWinningPlayer);
            // increase the score and set up for the next round
            winner.takeATrick();
            setPlayerOrder(indexOfWinningPlayer);
        }
        
        // now print the winner!
        // also don't forget to update their score
        SpadesPlayer youWon = getOverallWinner();
        distributeWins(youWon);
        printWinner();
        
    }

    private void forceSort() {
        for(CardPlayer cp : getPlayers()){
            SpadesPlayer player = (SpadesPlayer) cp;
            player.play();
        }
    }

    private SpadesPlayer getOverallWinner() {
        int maxScore = Integer.MIN_VALUE;
        int maxIndex = Integer.MIN_VALUE;
        for(int i = 0; i < getPlayers().size(); i++) {
            SpadesPlayer player = (SpadesPlayer) getPlayers().get(i);
            int finalScore = player.getScore();
            if(finalScore > maxScore){
                maxScore = finalScore;
                maxIndex = i;
            }
        }
        return (SpadesPlayer) getPlayers().get(maxIndex);
    }

    private void distributeWins(SpadesPlayer youWon) {
        for(CardPlayer cp : getPlayers()) {
            SpadesPlayer player = (SpadesPlayer) cp;
            player.incrementScore(player.equals(youWon));
        }
    }

    private PlayingCard[] playRound() {
        PlayingCard[] result = new PlayingCard[getPlayers().size()];
        PlayingCardSuit leadingSuit = null;
        int i = 0;

        // iterate over all the players
        for(CardPlayer cp : getPlayers()){
            SpadesPlayer player = (SpadesPlayer) cp;
            PlayingCard temp = player.play(leadingSuit, spadesBroken);
            result[i++] = temp;
            if(leadingSuit == null){
                leadingSuit = temp.getSuit();
            }
        }
        return result;
    }

    public int getRoundWinner(PlayingCard[] curCards){
        PlayingCard maxPC = null;
        int maxIndex = Integer.MIN_VALUE;
        for(int i = 0; i < curCards.length; i++){
            PlayingCard pc = curCards[i];
            if(maxPC == null || maxPC.compareTo(pc) < 0){
                maxPC = pc;
                maxIndex = i;
            }
        }
        return playerOrder[maxIndex];
    }

    private void setPlayerOrder(int firstPlayerIndex) {
        int n = playerOrder.length;

        for(int i = 0; i < n; i++){
            playerOrder[i] = (firstPlayerIndex + i) % n;
        }
    }

    @Override
    public void printWinner() {
        // currently not sure if this is necessary - so???
        // might need to do some refactoring later
    }

    @Override
    public boolean isEndCondition() {
        return getPlayers().get(0).getHandOfCards().isEmpty();
    }
}
