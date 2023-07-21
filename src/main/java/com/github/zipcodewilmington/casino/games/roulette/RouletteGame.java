package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public class RouletteGame implements GameInterface, GambleGameInterface {

    Scanner input = new Scanner(System.in);
    private double payOutMult;
    private RouletteTable table;
    private PlayerInterface roulettePlayer;
    private int ballCurrentNum;
    RouletteTable rt = new RouletteTable();
    int betRouletteNum = -1;
    int insideBetSelection = 0;
    //1. oddOrEven   2. blackOrRed   3. highOrLow   4. whichDoz   5. whichColumn

    int betOddOrEven = 0;
    int betBlackOrRed = 0;
    int betHighOrLow = 0;
    int betWhichDoz = 0;
    int betWhichColumn = 0;

    //Payout Multiplier
    int numberGuessMult = 35;
    int oddOrEvenMult = 2;




    public boolean winBet(int bet, int betParam) {
        return (bet == betParam);
    }

    public void endGame(){};

    @Override
    public void add(PlayerInterface player) {
        roulettePlayer = player;
    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

        // ***************** Replace Scanners With Player Input ***************

        //give menu
        add(roulettePlayer);
        int choice = 0;

        while (choice != 1) {

            //what would you like to bet on
            System.out.printf("Hello, welcome to the Roulette Table     \n" +
                    "What kind of bet would you like to make? \n" +
                    "1. Bet On Number           2. Inside Bet \n");

            if (input.nextInt() == 1) {

            } else if (input.nextInt() == 2) {
                //2. Make an inside Bet
                //1. oddOrEven   2. blackOrRed   3. highOrLow   4. whichDoz   5. whichColumn
                System.out.printf("Which inside bet would you like? \n" +
                        "1. Odd or Even    \n" +
                        "2. Black or Red   \n" +
                        "3. High or Low    \n" +
                        "4. Which Dozen    \n" +
                        "5. Which Column   \n");

                int insideChoice = input.nextInt();

                switch (insideChoice) {
                    case 1:
                        System.out.println("Would you like to bet Odd or Even? \n" +
                                "1. Odd \n" +
                                "2. Even");
                        betOddOrEven = input.nextInt();
                        break;
                    case 2:
                        System.out.println("Would you like to bet Black or Red? \n" +
                                "1. Black \n" +
                                "2. Red");
                        betBlackOrRed = input.nextInt();
                        break;
                    case 3:
                        System.out.println("Would you like to bet High or Low? \n" +
                                "1. High \n" +
                                "2. Low");
                        betHighOrLow = input.nextInt();
                        break;
                    case 4:
                        System.out.println("Would Dozen would you like to bet on? \n" +
                                "1. One \n" +
                                "2. Two \n" +
                                "3. Three ");
                        betWhichDoz = input.nextInt();
                        break;
                    case 5:
                        System.out.println("Which Column would you like to bet on? \n" +
                                "1. One \n" +
                                "2. Two \n" +
                                "3. Three ");
                        betWhichColumn = input.nextInt();
                        break;

                }

            } else {
                System.out.println("Invalid input, please select 1 or 2");
            }

            betRouletteNum = input.nextInt();


            //how much would you like to bet
            System.out.printf("How much would you like to bet? \n");

            //take bet amount

            int betAmount = input.nextInt();

            // ********** Add valid bet check here **********

            RouletteNumParam winningNum = rt.throwBall();

            if (betRouletteNum > -1) {
                if (winBet(betRouletteNum, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    System.out.printf("You Lose   \n" +
                                      "Try again? \n" +
                                      "1. Yes     \n" +
                                      "2. No        ");
                    choice = input.nextInt();
                }
            } else if (betOddOrEven > 0) {
                if (winBet(betOddOrEven, winningNum.oddOrEven)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    System.out.printf("You Lose   \n" +
                                      "Try again? \n" +
                                      "1. Yes     \n" +
                                      "2. No        ");
                    choice = input.nextInt();
                }
            } else if (betBlackOrRed > 0) {
                if (winBet(betBlackOrRed, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    System.out.printf("You Lose   \n" +
                                      "Try again? \n" +
                                      "1. Yes     \n" +
                                      "2. No        ");
                    choice = input.nextInt();
                }
            } else if (betHighOrLow > 0) {
                if (winBet(betHighOrLow, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    System.out.printf("You Lose   \n" +
                                      "Try again? \n" +
                                      "1. Yes     \n" +
                                      "2. No        ");
                    choice = input.nextInt();
                }
            } else if (betWhichDoz > 0) {
                if (winBet(betWhichDoz, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    System.out.printf("You Lose   \n" +
                                      "Try again? \n" +
                                      "1. Yes     \n" +
                                      "2. No        ");
                    choice = input.nextInt();
                }
            } else if (betWhichColumn > 0) {
                if (winBet(betWhichColumn, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    System.out.printf("You Lose   \n" +
                                      "Try again? \n" +
                                      "1. Yes     \n" +
                                      "2. No        ");
                    choice = input.nextInt();
                }
            }


        }
    }

    @Override
    public void printWinner() {
        System.out.printf("You won!");
    }

    @Override
    public boolean isEndCondition() {
        return true;
    }

    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        return betAmount + (betAmount * payOutMult);
    }
}
