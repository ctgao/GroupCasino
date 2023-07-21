package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public class RouletteGame implements GameInterface, GambleGameInterface {

    Scanner input = new Scanner(System.in);
    private double payOutMult;
    private RouletteTable table;
    private PlayerInterface roulettePlayer;
   // private RoulettePlayer player = new RoulettePlayer(roulettePlayer.getCasinoAccount(), new IOConsole());
    private int ballCurrentNum;

    //RoulettePlayer player = new RoulettePlayer();
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
    int choice = 0;


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

        while (choice != 1) {


            //what would you like to bet on
            System.out.printf("Hello, welcome to the Roulette Table     \n" +
                    "What kind of bet would you like to make? \n" +
                    "1. Bet On Number           2. Inside Bet \n");
            int betSelection = input.nextInt();

            if (betSelection == 1) {

                //bet on the number
                bettingOnNumber();

            } else if (betSelection == 2) {

                // Make an inside Bet
                String insideChoice = insideBetting();

                switch (insideChoice) {
                    case "odd":
                        betOddOrEven = 1;
                        break;
                    case "even":
                        betOddOrEven = 2;
                        break;
                    case "black":
                            betBlackOrRed = 1;
                        break;
                    case "red":
                            betBlackOrRed = 2;
                        break;
                    case "high":
                        betHighOrLow = 1;
                        break;
                    case "low":
                        betHighOrLow = 2;
                        break;
                    case "dozen one":
                        betWhichDoz = 1;
                        break;
                    case "dozen two":
                        betWhichDoz = 2;
                        break;
                    case "dozen three":
                        betWhichDoz = 3;
                        break;
                    case "column one":
                        betWhichColumn = 1;
                        break;
                    case "column two":
                        betWhichColumn = 2;
                        break;
                    case "column three":
                        betWhichColumn = 3;
                        break;
                }

            }

            betRouletteNum = input.nextInt();

            System.out.printf("How much would you like to bet? \n");

            int betAmount = input.nextInt();

//            if (!player.validBet(betAmount)) {
//                System.out.println("You Can't Do That");
//                death;
//            }

            RouletteTable rt = new RouletteTable();
            RouletteNumParam winningNum = rt.throwBall();

            //Betting on Number
            if (betRouletteNum > -1) {
                if (winBet(betRouletteNum, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    tryAgain();
                }
            }

            //Betting on Inside Bet
             if (betOddOrEven > 0) {
                if (winBet(betOddOrEven, winningNum.oddOrEven)) {
                    printWinner();
                    payOutCalc(betAmount, oddOrEvenMult);
                } else {
                    tryAgain();
                }

            } else if (betBlackOrRed > 0) {
                if (winBet(betBlackOrRed, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    tryAgain();
                }

            } else if (betHighOrLow > 0) {
                if (winBet(betHighOrLow, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    tryAgain();
                }

            } else if (betWhichDoz > 0) {
                if (winBet(betWhichDoz, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    tryAgain();
                }

            } else if (betWhichColumn > 0) {
                if (winBet(betWhichColumn, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                } else {
                    tryAgain();
                }
            }

        }
    }


    public void tryAgain() {
        System.out.printf("You Lose   \n" +
                "Try again? \n" +
                "1. Yes     \n" +
                "2. No        ");
        choice = input.nextInt();
        if (choice == 1) {
            run();
        }
    }

    public void bettingOnNumber(){
        System.out.println("What number would you like to bet on?");
        int betRouletteNum = input.nextInt();

        System.out.printf("How much would you like to bet? \n");
        int betAmount = input.nextInt();

        RouletteTable rt = new RouletteTable();
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
                int choice = input.nextInt();
                if (choice == 1) {
                    run();
                }
            }
        }

    }


    public String insideBetting() {

        StringBuilder insideFinalBet = new StringBuilder();

        System.out.printf("Which inside bet would you like? \n" +
                "1. Odd or Even    \n" +
                "2. Black or Red   \n" +
                "3. High or Low    \n" +
                "4. Which Dozen    \n" +
                "5. Which Column   \n");

        int insideChoice = input.nextInt();

        //setting up int to check against roulette number enums
        switch (insideChoice) {
            case 1:
                System.out.println("Would you like to bet Odd or Even? \n" +
                        "1. Odd \n" +
                        "2. Even");
                betOddOrEven = input.nextInt();
                if (betOddOrEven == 1) {
                    insideFinalBet.append("odd");
                } else insideFinalBet.append("even");
                break;
            case 2:
                System.out.println("Would you like to bet Black or Red? \n" +
                        "1. Black \n" +
                        "2. Red");
                betBlackOrRed = input.nextInt();
                if (betBlackOrRed == 1) {
                    insideFinalBet.append("black");
                } else insideFinalBet.append("red");
                break;
            case 3:
                System.out.println("Would you like to bet High or Low? \n" +
                        "1. High \n" +
                        "2. Low");
                betHighOrLow = input.nextInt();
                if (betHighOrLow == 1) {
                    insideFinalBet.append("high");
                } else insideFinalBet.append("low");
                break;
            case 4:
                System.out.println("Would Dozen would you like to bet on? \n" +
                        "1. One \n" +
                        "2. Two \n" +
                        "3. Three ");
                betWhichDoz = input.nextInt();
                if (betWhichDoz == 1) {
                    insideFinalBet.append("dozen one");
                } else if (betWhichDoz == 2) {
                    insideFinalBet.append("dozen two");
                } else insideFinalBet.append("dozen three");
                break;
            case 5:
                System.out.println("Which Column would you like to bet on? \n" +
                        "1. One \n" +
                        "2. Two \n" +
                        "3. Three ");
                betWhichColumn = input.nextInt();
                if (betWhichColumn == 1) {
                    insideFinalBet.append("column one");
                } else if (betWhichColumn == 2) {
                    insideFinalBet.append("column two");
                } else insideFinalBet.append("column three");
                break;
        }

        return insideFinalBet.toString();
    }






    public boolean winBet(int bet, int betParam) {
        return (bet == betParam);
    }


    @Override
    public void printWinner() {
        System.out.printf("You won!");
    }

    @Override
    public boolean isEndCondition() {
        return true;
    }
    public void endGame(){};

    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        return betAmount + (betAmount * payOutMult);
    }
}
