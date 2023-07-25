package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GambleGameInterface, GameInterface {
    private SlotsPlayer playerOfSlots ;
    private Integer [] numbersTable = new Integer[5] ;
    private Integer resultOfSpin ;
    private  int bet = 10;
    private ArrayList <Integer> newTable = new ArrayList<>(Arrays.asList(numbersTable));




    @Override
    public void run() {
        String startGameString = "Hello , Welcome To SUPER SLOTS ! The Best Place To Lose Money";
        String question  = "Do you want to play \n [Yes] or [NO]";
        String makeASpin = "Press Enter to Spin The Machine";
        String answerToSeeAccount  = "If you wnat to see your account balance \n " +
                "press [Yes] if no press [No]";

        boolean runGame = true;

        playerOfSlots.printToConsole(startGameString);
        while (runGame) {

                String answer = playerOfSlots.promptPlayerForChoice(question);
                if (answer.equalsIgnoreCase("no")) {
//                    runGame = false;
                    break;
                }
                if(answer.equalsIgnoreCase("yes")) {
//                    playerOfSlots.validBet(bet);
                    if (playerOfSlots.validBet(bet) == true) {
                        playerOfSlots.makeBet(bet);
                        //playerOfSlots.printToConsole(startGameString);
                         }
                    else  {
                        playerOfSlots.printToConsole("You dont have money anymore");
                        return;
                    }
                }
                playerOfSlots.promptPlayerForChoice(makeASpin);
                machineSpin();
                checkingWinningCondition(resultOfSpin);
                // to pay player for game
            int wonResult = payOutCalc(bet,resultOfSpin);
            playerOfSlots.depositPayOut(wonResult);
            String seeAcc = playerOfSlots.promptPlayerForChoice(answerToSeeAccount);
            if (seeAcc.equalsIgnoreCase("yes")) {
                playerOfSlots.getCasinoAccount();
            }

        }
    }

    public void startInvitations () {
        String startGameString = "Hello , Welcome To SUPER SLOTS ! The Best Place To Lose Money";
        String question  = "Do you want to play \n [Yes] or [NO]";
        String makeASpin = "Press Enter-Twice to Run The Game";

        playerOfSlots.printToConsole(startGameString);
        String answer = playerOfSlots.promptPlayerForChoice(question);
        String answer2 = playerOfSlots.promptPlayerForChoice(makeASpin);
    }


    public void machineSpin() {
        Random rand = new Random();

        // Generate random numbers for each slot
        newTable.clear();
        for (int i = 0; i < 5; i++) {
            int randomNumber  = rand.nextInt(10) + 1; // Generate a random number between 1 and 10 (inclusive)
            newTable.add(randomNumber);
        }
        String output_string = "Numbers are";
        for (int numb : newTable ) {
            output_string += " [" + numb + "]";
        }
        System.out.println(output_string);

        // Calculate the result of the spin (the maximum occurrence of a number)
        resultOfSpin = calculateResultOfSpin(newTable);
    }

    private int calculateResultOfSpin(List<Integer> table) {
        int maxOccurrence = 0;
        for (int i = 0; i < table.size(); i++) {
            int currentNum = table.get(i);
            int counter = 0;
            for (int j = 0; j < table.size(); j++) {
                if (currentNum == table.get(j)) {
                    counter++;
                }
            }
            if (counter > maxOccurrence) {
                maxOccurrence = counter;
            }
        }
        return maxOccurrence;
    }


    public boolean checkingWinningCondition (Integer resultOfSpin) {
        if (resultOfSpin >= 4 && resultOfSpin <= 5 ) {
            System.out.println("Congratulations! Your numbers match!!! YOUR  multiplier IS " + resultOfSpin);
            return true;
        }
        else {System.out.println("Sorry, the numbers do not match.");
        }
        return false;
    }



    @Override
    public void add(PlayerInterface player) {
        playerOfSlots = (SlotsPlayer) player;

    }

    @Override
    public void remove(PlayerInterface player) {
        this.playerOfSlots = null;
    }


    @Override
    public void printWinner() {
//        System.out.println("YOU WON ALL THIS MONEY ");
    }

    @Override
    public boolean isEndCondition() {
        if( playerOfSlots.getWallet() <= 0 ) {
//            System.out.println("Sorry but you lose all your money");
            return true;}
        return false;
    }
    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        int temp = betAmount * payOutMult;
        return temp;
    }
}
