package com.github.zipcodewilmington.casino.games.spades;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.casino.games.blackjack.DealerPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public class SpadesGame extends CardGame {
    // for printing pretty pretty
    IOConsole spadesGame = new IOConsole(AnsiColor.MAGENTA);
    // other useful game variables
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
        // welcome statement!
        welcome();
        boolean continueOrNot;
        do {
            setUp();
            // play a round until everyone's hands are empty
            while (!isEndCondition()) {
                // play through a round
                PlayingCard[] curCards = playRound();

                // get the winner
                int indexOfWinningPlayer = getRoundWinner(curCards);
                SpadesPlayer winner = (SpadesPlayer) getPlayers().get(indexOfWinningPlayer);
                spadesGame.println(String.format("\nPlayer %d won this trick!\n", indexOfWinningPlayer + 1));

                // increase the trick count and set up for the next round
                winner.takeATrick();
                setPlayerOrder(indexOfWinningPlayer);
            }
            // NOTE FOR FUTURE SELF
            // NO SPACE WHEN YOUR HAND GETS PRINTED OUT AFTER SOMEONE ELSE'S TURN

            // now print the winner!
            printWinner();
            cleanUp();
            //ask for another round
            continueOrNot = wannaContinue();
        } while(continueOrNot);

        printStats();
    }

    private void printStats() {
        for(CardPlayer cp : getPlayers()) {
            SpadesPlayer player = (SpadesPlayer) cp;
            if(!player.isHumanPlayer()){
                continue;
            }
            String endMessage = "Thank you for playing! You played %d games and have a win rate of %.2f percent!";
            spadesGame.println(String.format(endMessage, player.getTotalGamesPlayed(), player.getWinRate()));
        }
    }

    private void welcome() {
        spadesGame.println("\nWelcome to the Spades Game!");
        spadesGame.println("Win tricks by having the highest card possible!");
        spadesGame.println("The leading suit (first suit played) or Spades will always determine the winner!");
        spadesGame.println("Good luck!\n");
    }

    private void setUp() {
        // see how many users are playing - should be 4
        while(getPlayers().size() < 4){
            this.add(new SpadesPlayer(null, new IOConsole(AnsiColor.DULL_CYAN)));
        }
        // set the player order first
        setPlayerOrder(0);

        // deal the cards to all players
        // temporarily hard coded?
        int handSize = 13;
        dealCards(handSize);
        // have all players sort their hands first
        forceSort();
    }

    private void forceSort() {
        for(CardPlayer cp : getPlayers()){
            SpadesPlayer player = (SpadesPlayer) cp;
            player.play();
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
            player.getPlayerInput().print(String.format("Player %d played ", i+1));
            temp.printCardWithColor();
            spadesGame.println("");

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
    public boolean isEndCondition() {
        return getPlayers().get(0).getHandOfCards().isEmpty();
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

    @Override
    public void printWinner() {
        SpadesPlayer youWon = getOverallWinner();
        if(youWon.isHumanPlayer()){
            spadesGame.println("CONGRATS ON THE WIN!!!\n");
        }
        else{
            spadesGame.println("A computer player won over you.");
            new IOConsole(AnsiColor.YELLOW).println("Wah wah wah wah-oh-wah\n");
        }
        distributeWins(youWon);
    }

    private void distributeWins(SpadesPlayer youWon) {
        for(CardPlayer cp : getPlayers()) {
            SpadesPlayer player = (SpadesPlayer) cp;
            player.incrementWins(player.equals(youWon));
        }
    }

    private void cleanUp() {
        // all the cards have already been played, so players hands are empty anyways
        getTheDeck().reclaimCards();
    }

    private boolean wannaContinue() {
        for(CardPlayer cp : super.getPlayers()) {
            SpadesPlayer player = (SpadesPlayer) cp;
            if(player.isHumanPlayer()) {
                return player.promptPlayerToPlayAgain("Wanna play again?");
            }
        }
        return false;
    }
}
