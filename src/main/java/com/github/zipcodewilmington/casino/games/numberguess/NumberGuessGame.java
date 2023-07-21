package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GameInterface{

    private NumberGuessPlayer player;

    private int targetNumber;

    private int guess;

    private int guessesRemaining = 5;

    Scanner input = new Scanner(System.in);

    public int randomNum() {
        int targetNumber = 1 + (int) (100 * Math.random());
        return targetNumber;
    }

    public void makeGuess(int guess) {
        System.out.println("Enter your guess: ");
        guess = input.nextInt();

    }

    public boolean validateGuess() {
        if (guess == targetNumber) {
            printWinner();
            return true;
        } else if (guess > targetNumber) {
            System.out.println("Your guess is too high :(");
            return false;
        } else {
            System.out.println("Your guess is too low :(");
        }
        return false;
    }

    public void getRemainingGuesses() {
        do {
            System.out.println("You have " + guessesRemaining + " guesses remaining");
            guessesRemaining--;
        } while (guessesRemaining > 0);

    }

    @Override
    public void add(PlayerInterface player) {
        this.player = (NumberGuessPlayer) player;
    }

    @Override
    public void remove(PlayerInterface player) {
        this.player = null;
    }

    @Override
    public void run() {
        randomNum();
        System.out.println("I'm thinking of a number between 1 and 100.\nYou have 5 tries to guess the number.\nGood luck :)");
        do {
            makeGuess(this.guess);
            getRemainingGuesses();
            validateGuess();
        } while (!isEndCondition());
        isEndCondition();



    }

    @Override
    public void printWinner() {
        System.out.println("You guessed the number!\nYou win!");

    }

    @Override
    public boolean isEndCondition() {
        if (guessesRemaining == 0) {
            System.out.println("You ran out of tries :(\nYou lose!");
        }
        return true;
    }
}