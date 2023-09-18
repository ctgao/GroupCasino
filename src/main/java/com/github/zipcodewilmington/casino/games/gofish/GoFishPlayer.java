package com.github.zipcodewilmington.casino.games.gofish;

import com.github.zipcodewilmington.casino.CardPlayer;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.cardutils.PlayingCard;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardSuit;
import com.github.zipcodewilmington.casino.cardutils.PlayingCardValue;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GoFishPlayer extends CardPlayer {

    private Integer books = 0;

    public GoFishPlayer(CasinoAccount wallet, IOConsole console) {
        super(wallet, console);
    }

    public boolean hasCard(PlayingCardValue cardValue) {

        PlayingCard[] affirmative = askForCard(cardValue);
        return affirmative.length == 0;

    }

    public PlayingCard[] askForCard(PlayingCardValue cardValue) {

        ArrayList<PlayingCard> playingcard = new ArrayList<>();

        for (int i = 0; i < getHandOfCards().size(); i++) {
            if (getHandOfCards().get(i).compareValue(cardValue) == 0) {
                playingcard.add(getHandOfCards().get(i));

                useCard(getHandOfCards().get(i));

                i--;
            }

        }

        return playingcard.toArray(new PlayingCard[playingcard.size()]);
    }

    public void recieveCards(PlayingCard[] gotcha) {

        getHandOfCards().addAll(Arrays.asList(gotcha));

    }

    public void remove4(PlayingCardValue card) {

        ArrayList<PlayingCard> remove = new ArrayList<>();

        remove.add(new PlayingCard(PlayingCardSuit.DIAMONDS, card));
        remove.add(new PlayingCard(PlayingCardSuit.SPADES, card));
        remove.add(new PlayingCard(PlayingCardSuit.HEARTS, card));
        remove.add(new PlayingCard(PlayingCardSuit.CLUBS, card));

        getHandOfCards().removeAll(remove);

    }

    public Integer getBooks() {
        return books;
    }

    public void IncrementBooks() {

        books++;

    }

    public PlayingCardValue fourOfAKind() {

        HashMap<PlayingCardValue, Integer> frequency = countFrequencyOfValue();

        for (PlayingCardValue value: frequency.keySet()){

            if (frequency.get(value) == 4){
                return value;
            }
        }

        return null;
    }

    public HashMap countFrequencyOfValue() {

        HashMap<PlayingCardValue, Integer> frequency = new HashMap<>();

        for (PlayingCardValue value: PlayingCardValue.values())

        { int count = 0;

            for (int i = 0; i < getHandOfCards().size(); i++){
                if (getHandOfCards().get(i).compareValue(value) == 0){
                    count++;
                }
            }
            frequency.put(value, count);
        }
        return frequency;
    }

    public void printGoFish() {

        printToConsole("Go Fish!");

    }

    @Override
    public void sortHand() {

    }

    @Override
    public <PlayingCardValue> PlayingCardValue play() {

        PlayingCardValue question = (PlayingCardValue) promptForCardValue("What card do you want to ask for?");

        return question;
    }
}
