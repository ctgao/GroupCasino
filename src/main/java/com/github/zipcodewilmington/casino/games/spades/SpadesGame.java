package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;

public class SpadesGame extends CardGame {
    boolean spadesBroken;
    int[] playerOrder;

    public SpadesGame(){
        super();
        // TRY TO CHANGE HOW THE PLAYER ARRAY LIST IS INSTANTIATED PER CARD PLAYER CLASS TO SEE IF IT'LL BE EASIER THAT WAY
        spadesBroken = false;
        playerOrder = null;
    }

    public void setPlayerOrder(int firstPlayerIndex) {
        int n = getPlayers().size();

        // NEED THIS JUST IN CASE YOU HAVEN'T DONE ANYTHING!
        if(playerOrder == null){
            playerOrder = new int[getPlayers().size()];
        }
        for(int i = 0; i < n; i++){
            playerOrder[i] = (firstPlayerIndex + i) % n;
        }
    }
    public int[] getPlayerOrder() {
        return playerOrder;
    }
    public boolean isSpadesBroken(){
        return spadesBroken;
    }

    @Override
    public void run() {
        // see how many users are playing - should be 4
        while(getPlayers().size() < 4){
            this.add(new SpadesPlayer(null, getPlayers().get(0).getPlayerInput()));
        }

        // set the player order first
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
            winner.printToConsole(String.format("\nPlayer %d won this trick!\n", indexOfWinningPlayer+1));

            // increase the score and set up for the next round
            winner.takeATrick();
            setPlayerOrder(indexOfWinningPlayer);
        }
        // NOTE FOR FUTURE SELF
        // WHEN THE PLAYER WINS THE TRICK, THERE ARE TWO EMPTY LINES BEFORE THEIR HAND GETS SHOWN
        // WHEN THE NPC WINS THE TRICK, THERE ARE NO EMPTY LINES BEFORE THE STATUS UPDATE
        
        // now print the winner!
        // also don't forget to update their score
        SpadesPlayer youWon = getOverallWinner();
        if(youWon.isHumanPlayer()){
            youWon.printToConsole("YOU WINNIN' SON!\n");
        }
        else{
            youWon.printToConsole("A computer player won over you.\nWah-wah-wah wah-oh-wah\n");
        }
        distributeWins(youWon);
        printWinner();
    }

    private void forceSort() {
        for(CardPlayer cp : getPlayers()){
            SpadesPlayer player = (SpadesPlayer) cp;
            player.play();
        }
    }

    public SpadesPlayer getOverallWinner() {
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
            player.incrementWins(player.equals(youWon));
        }
    }

    private PlayingCard[] playRound() {
        PlayingCard[] result = new PlayingCard[getPlayers().size()];
        PlayingCardSuit leadingSuit = null;
        int index = 0;

        // iterate over all the players
        for(int i : playerOrder){
            SpadesPlayer player = (SpadesPlayer) getPlayers().get(i);
            PlayingCard temp = player.play(leadingSuit, spadesBroken);

            //use and print the card that got played
            player.useCard(temp);
            player.printToConsole(String.format("Player %d played %s", i+1, temp.toString()));

            // save the card played in an array
            result[index++] = temp;
            if(leadingSuit == null){
                leadingSuit = temp.getSuit();
            }
        }
        return result;
    }

    public int getRoundWinner(PlayingCard[] curCards){
        PlayingCard maxPC = curCards[0];
        int maxIndex = 0;
        for(int i = 0; i < curCards.length; i++){
            PlayingCard pc = curCards[i];
            if(compareToFirstCard(maxPC, pc)){
                maxPC = pc;
                maxIndex = i;
            }
        }
        // DO A CHECK HERE! If the winning card is a spade, then have to change spades broken
        if(maxPC.getSuit().equals(PlayingCardSuit.SPADES)){
            spadesBroken = true;
        }
        return playerOrder[maxIndex];
    }

    private boolean compareToFirstCard(PlayingCard maxPC, PlayingCard pc) {
        // if they are the same suit, check
        if(maxPC.getSuit().equals(pc.getSuit())){
            return maxPC.compareTo(pc) < 0;
        }
        else{
            return pc.getSuit().equals(PlayingCardSuit.SPADES);
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
