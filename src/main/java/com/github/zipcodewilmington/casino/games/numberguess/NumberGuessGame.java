package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import java.lang.Math;
import java.util.Random;
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
    static Random rand = new Random();


    public int randomNum() {
        targetNumber = (int) Math.floor((Math.random() * (100 - 1 + 1) + 1));
        return targetNumber;
    }

    public void makeGuess() {
        System.out.println("Enter your guess: ");
        guess = input.nextInt();

    }

    public boolean validateGuess() {
        if (guess == targetNumber) {
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
            System.out.println("You have " + guessesRemaining + " guesses remaining");
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
            getRemainingGuesses();
            makeGuess();
            if (validateGuess()) {
                printWinner();
                break;
            } else {
                guessesRemaining--;
            }
        } while (!isEndCondition());

        }


    @Override
    public void printWinner() {
        System.out.println("You guessed the number!\nYou win!");

    }

    @Override
    public boolean isEndCondition() {
        if (guessesRemaining == 0) {
            System.out.println("You ran out of tries :(\nYou lose!");
            return true;
        }
        return false;
    }
}