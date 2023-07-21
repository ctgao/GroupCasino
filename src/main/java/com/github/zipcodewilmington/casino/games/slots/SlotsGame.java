package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GambleGameInterface, GameInterface {
    private SlotsPlayer playerOfSlots;
    private Integer [] numbersTable ;
    private Integer resultOfSpin ;


    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        int temp = betAmount * payOutMult;
        return temp;
    }


    //    public Integer[] userInput() {
//        int counter = 0;
//        String promptToPlayer = "Enter your numbers";
//        Integer[] userInput = new Integer[3];
//        for (int i = 0; i < 3; i++) {
//            System.out.println("You can enter " + counter + "numbers not higher than 100");
//            userInput[i] = playerInput.getIntegerInput(promptToPlayer);
//            counter++;
//        }return userInput();
//    }
    @Override
    public void run() {
        String startGameString = "Hello , type SPACE to start the Game and try your luck";

        boolean runGame = true;

        while (runGame) {
            add(playerOfSlots);
            System.out.println(startGameString);
            checkThePlayerv(playerOfSlots);
            machineSpin();
            checkingWinningCondition(resultOfSpin);
            if (!isEndCondition()) {runGame = false;}

        }
    }




    public void machineSpin() {

        Random rand1 = new Random(10);
        Random rand2 = new Random(10);
        Random rand3 = new Random(10);
        Random rand4 = new Random(10);
        Random rand5 = new Random(10);

        newTable.add(rand1.nextInt());
        newTable.add(rand2.nextInt());
        newTable.add(rand3.nextInt());
        newTable.add(rand4.nextInt());
        newTable.add(rand5.nextInt());

        //   for (Integer num : userInput()) {
//           userInputList.add(num);
//       }

        int counterOccur = 0;
        int counterResult = 0;

        for (int i = 0 ; i < newTable.size(); i++) {
            int ckeck = newTable.get(i);
            int counterFreq = 0;
            for (int j = 0 ; j < newTable.size() ; j++) {

                if (newTable.get(i).equals(newTable.get(j))) {
                    counterFreq++;
                }
            }
            if ( counterFreq > counterOccur) {
                counterOccur = counterFreq;
            }
        }
        resultOfSpin = counterOccur;
    }

    public boolean checkingWinningCondition (Integer resultOfSpin) {
        if (resultOfSpin >= 3 && resultOfSpin <= 5 ) {
            System.out.println("Congratulations! Your numbers match!!! YOUR  multiplier IS " + resultOfSpin);
            return true;
        }
        else {System.out.println("Sorry, the numbers do not match.");
        }
        return false;
    }

    public void endOfGame () {
    }

    public void checkThePlayerv(SlotsPlayer player) {
        playerOfSlots.validBet(0);
        playerOfSlots.makeBet(0);
    }


    private ArrayList <Integer> newTable = new ArrayList<>(Arrays.asList(numbersTable));
    ArrayList <Integer>  userInputList = new ArrayList<>();

    @Override
    public void add(PlayerInterface player) {
        playerOfSlots = (SlotsPlayer) player;

    }

    @Override
    public void remove(PlayerInterface player) {
    }


    @Override
    public void printWinner() {

    }

    @Override
    public boolean isEndCondition() {
        if( playerOfSlots.getWallet() <= 0 ) {
            return true;
        } else {checkThePlayerv(playerOfSlots);}
        return false;
    }
}
