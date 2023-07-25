package com.github.zipcodewilmington.casino.games.gofish;

import com.github.zipcodewilmington.casino.CardGame;
import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;

public class GoFishGame extends CardGame {

    public Boolean checkHandForCard(GoFishPlayer danielle, PlayingCardValue card){


        return danielle.hasCard(card);
    }

    public GoFishPlayer checkWinner(){
        //get each player
        //get each player score
        //compare
        //for (int i = 0; i < getPlayers().size(); i++){

            GoFishPlayer goFishPlayer1 = (GoFishPlayer) getPlayers().get(0);
            GoFishPlayer goFishPlayer2 = (GoFishPlayer) getPlayers().get(1);
        if (goFishPlayer1.getBooks() > goFishPlayer2.getBooks()){
            return goFishPlayer1;
        }
        return goFishPlayer2;
    }



    @Override
    public void run() {
        GoFishPlayer player1 = (GoFishPlayer) getPlayers().get(0);
        GoFishPlayer player2 = (GoFishPlayer) getPlayers().get(1);
        //welcome();
        boolean continueOrNot;
        do {
            dealCards(7);

            while (!isEndCondition()) {
                playertakesturn(player1, player2);
                playertakesturn(player2, player1);
            }

            printWinner();
            continueOrNot = anotherGame();
        } while(continueOrNot);



    }

    private boolean anotherGame() {
        for(CardPlayer cp : super.getPlayers()) {
            GoFishPlayer player = (GoFishPlayer) cp;
                return player.promptPlayerForYesOrNo("Play Another Game?");
            }
        return false;
    }

    private void playertakesturn(GoFishPlayer player1, GoFishPlayer player2) {
        PlayingCardValue aMove = player1.play();

        if (player2.hasCard(aMove)){

            PlayingCard[] gotcha = player2.askForCard(aMove);

            player1.recieveCards (gotcha);
        }
        else {
            player2.printGoFish();
            player1.receiveCard(getTheDeck().drawCard());

        }
        if (player1.fourOfAKind()!= null){
            player1.remove4(player1.fourOfAKind());
        }
    }

    @Override
    public void printWinner() {

    }

    @Override
    public boolean isEndCondition() {
        boolean end = getTheDeck().hasCardsLeft();

        GoFishPlayer goFishPlayer1 = (GoFishPlayer) getPlayers().get(0);
        GoFishPlayer goFishPlayer2 = (GoFishPlayer) getPlayers().get(1);

        boolean player1HasCards = goFishPlayer1.getHandOfCards().isEmpty();
        boolean player2HasCards = goFishPlayer2.getHandOfCards().isEmpty();

        return player1HasCards || player2HasCards || !end;

    }

}
