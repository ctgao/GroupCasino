package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public class RouletteGame implements GameInterface, GambleGameInterface {

    private final IOConsole gameMaster = new IOConsole(AnsiColor.RED);
    private double payOutMult;
    private RouletteTable table;
    private RoulettePlayer roulettePlayer;
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
        roulettePlayer = (RoulettePlayer) player;
    }

    @Override
    public void remove(PlayerInterface player) {
        roulettePlayer = null;
    }

    @Override
    public void run() {

        //Note To Self: Don't Use Blue or Green

        // ***************** Replace Scanners With Player Input ***************

        //give menu


        while (choice != 1) {


            //what would you like to bet on
            gameMaster.println("Hello, welcome to the Roulette Table     \n" +
                                    "What kind of bet would you like to make? \n" +
                                    "1. Bet On Number           2. Inside Bet \n");

            int betSelection = roulettePlayer.promptPlayerFoMoney("");

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

            int betAmount = roulettePlayer.promptPlayerFoMoney("How much would you like to bet?");

            //Confirm the bet is a valid bet

            RouletteTable rt = new RouletteTable();
            RouletteNumParam winningNum = rt.throwBall();

            gameMaster.println("And The Winning Number Is: \n"
                                    + winningNum.toString());


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


    public void showWinningCondition() {}

    public void tryAgain() {
        choice = roulettePlayer.promptPlayerFoMoney("You Lose   \n" +
                "Try again? \n" +
                "1. Yes     \n" +
                "2. No        ");
        if (choice == 1) {
            run();
        }
    }

    public void bettingOnNumber(){
        int betRouletteNum = roulettePlayer.promptPlayerFoMoney("What number would you like to bet on?");

        int betAmount = roulettePlayer.promptPlayerFoMoney("How much would you like to bet?");

        RouletteTable rt = new RouletteTable();
        RouletteNumParam winningNum = rt.throwBall();

        if (betRouletteNum > -1) {
            if (winBet(betRouletteNum, winningNum.rouletteNum)) {
                printWinner();
                payOutCalc(betAmount, numberGuessMult);
            } else {
                int choice = roulettePlayer.promptPlayerFoMoney("You Lose   \n" +
                        "Try again? \n" +
                        "1. Yes     \n" +
                        "2. No        ");
                if (choice == 1) {
                    run();
                }
            }
        }

    }

    public String insideBetting() {

        StringBuilder insideFinalBet = new StringBuilder();

        int insideChoice = roulettePlayer.promptPlayerFoMoney("Which inside bet would you like? \n" +
                "1. Odd or Even    \n" +
                "2. Black or Red   \n" +
                "3. High or Low    \n" +
                "4. Which Dozen    \n" +
                "5. Which Column   \n");


        //setting up int to check against roulette number enums
        switch (insideChoice) {
            case 1:
                betOddOrEven = roulettePlayer.promptPlayerFoMoney("Would you like to bet Odd or Even? \n" +
                                                                  "1. Odd \n" +
                                                                  "2. Even");

                if (betOddOrEven == 1) {
                    insideFinalBet.append("odd");
                } else insideFinalBet.append("even");
                break;
            case 2:
                betBlackOrRed = roulettePlayer.promptPlayerFoMoney("Would you like to bet Black or Red? \n" +
                                                        "1. Black \n" +
                                                        "2. Red");
                if (betBlackOrRed == 1) {
                    insideFinalBet.append("black");
                } else insideFinalBet.append("red");
                break;
            case 3:
                betHighOrLow = roulettePlayer.promptPlayerFoMoney("Would you like to bet High or Low? \n" +
                                                                  "1. High \n" +
                                                                  "2. Low");
                if (betHighOrLow == 1) {
                    insideFinalBet.append("high");
                } else insideFinalBet.append("low");
                break;
            case 4:
                betWhichDoz = roulettePlayer.promptPlayerFoMoney("Would Dozen would you like to bet on? \n" +
                        "1. One \n" +
                        "2. Two \n" +
                        "3. Three ");
                if (betWhichDoz == 1) {
                    insideFinalBet.append("dozen one");
                } else if (betWhichDoz == 2) {
                    insideFinalBet.append("dozen two");
                } else insideFinalBet.append("dozen three");
                break;
            case 5:
                betWhichColumn = roulettePlayer.promptPlayerFoMoney("Which Column would you like to bet on? \n" +
                        "1. One \n" +
                        "2. Two \n" +
                        "3. Three ");
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
