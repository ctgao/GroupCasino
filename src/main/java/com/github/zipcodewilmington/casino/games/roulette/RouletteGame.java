package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.GambleGameInterface;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerClass;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public class RouletteGame implements GameInterface, GambleGameInterface {

    private final IOConsole gameMaster = new IOConsole(AnsiColor.PURPLE);
    private RouletteTable table;
    private RoulettePlayer roulettePlayer;
    int betRouletteNum = -1;

    int betOddOrEven = 0;
    int betBlackOrRed = 0;
    int betHighOrLow = 0;
    int betWhichDoz = 0;
    int betWhichColumn = 0;

    int numberGuessMult = 35;
    int oddOrEvenMult = 2;
    int choice = 1;


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

        while (!isEndCondition()) {
            int betSelection = 0;

            //what would you like to bet on
            gameMaster.println("Hello, welcome to the Roulette Table     \n" +
                                    "What kind of bet would you like to make? \n" +
                                    "1. Bet On Number           2. Inside Bet \n");

            betSelection = roulettePlayer.promptPlayerFoMoney("");

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
            printWinningParam(winningNum);



            //Betting on Number
            if (betRouletteNum > -1) {
                if (winBet(betRouletteNum, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                    tryAgain();
                } else {
                    printLoser();
                    tryAgain();
                }
            }

            //Betting on Inside Bet
             if (betOddOrEven > 0) {
                if (winBet(betOddOrEven, winningNum.oddOrEven)) {
                    printWinner();
                    payOutCalc(betAmount, oddOrEvenMult);
                    tryAgain();

                } else {
                    printLoser();
                    tryAgain();
                }

            } else if (betBlackOrRed > 0) {
                if (winBet(betBlackOrRed, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                    tryAgain();

                } else {
                    printLoser();
                    tryAgain();
                }

            } else if (betHighOrLow > 0) {
                if (winBet(betHighOrLow, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                    tryAgain();

                } else {
                    printLoser();
                    tryAgain();
                }

            } else if (betWhichDoz > 0) {
                if (winBet(betWhichDoz, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                    tryAgain();

                } else {
                    printLoser();
                    tryAgain();
                }

            } else if (betWhichColumn > 0) {
                if (winBet(betWhichColumn, winningNum.rouletteNum)) {
                    printWinner();
                    payOutCalc(betAmount, numberGuessMult);
                    tryAgain();
                } else {
                    printLoser();
                    tryAgain();
                }
            }

        }

    }

    public void printWinningParam(RouletteNumParam winningNum) {
        gameMaster.println("The Winning Number Is: \n"
                + winningNum.rouletteNum + "\n");
        if (winningNum.oddOrEven == 1) {
            gameMaster.println("It Is an Odd Number");
        } else if (winningNum.oddOrEven == 2) {
            gameMaster.println("It Is an Even Number");
        }
        if (winningNum.blackOrRed == 1) {
            gameMaster.println("With The Color Black\n");
        } else if (winningNum.blackOrRed == 2) {
            gameMaster.println("With The Color Red\n");
        }
        if (winningNum.highOrLow == 1) {
            gameMaster.println("As a High Number");
        }else if (winningNum.highOrLow == 2) {
            gameMaster.println("As a Low Number");
        }
        if (winningNum.whichDoz == 1) {
            gameMaster.println("That lives in the 1st Dozen");
        }else if (winningNum.whichDoz == 2) {
            gameMaster.println("That lives in the 2nd Dozen");
        }else if (winningNum.whichDoz == 3) {
            gameMaster.println("That lives in the 3rd Dozen");
        }
        if (winningNum.whichColumn == 1) {
            gameMaster.println("Part Of The 1st Column\n");
        }else if (winningNum.whichColumn == 2) {
            gameMaster.println("Part Of The 2nd Column\n");
        }else if (winningNum.whichColumn == 3) {
            gameMaster.println("Part Of The 3rd Column\n");
        }


    }

    public void bettingOnNumber(){
        int betRouletteNum = roulettePlayer.promptPlayerFoMoney("What number would you like to bet on?");

        int betAmount = roulettePlayer.promptPlayerFoMoney("How much would you like to bet?");

        RouletteTable rt = new RouletteTable();
        RouletteNumParam winningNum = rt.throwBall();
        printWinningParam(winningNum);

        if (betRouletteNum > -1) {
            if (winBet(betRouletteNum, winningNum.rouletteNum)) {
                printWinner();
                payOutCalc(betAmount, numberGuessMult);
            } else {
                printLoser();
                tryAgain();
            }
        }
    }

    public void showWinningCondition() {}

    public void printLoser() {
        gameMaster.println("Ouch You Lose \n");
    }

    public void tryAgain() {
        int again = roulettePlayer.promptPlayerFoMoney("Play Again? \n" +
                                                    "1. Yes     \n" +
                                                    "2. No        ");
        if (again == 1) {
            run();
        } else if (again == 2) {
            choice = 2;
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
        if(choice == 2) {
            return true;
        }
        return false;
    }
    public void endGame(){

    }

    @Override
    public int payOutCalc(int betAmount, int payOutMult) {
        int winnings = betAmount + (betAmount * payOutMult);
        gameMaster.println(" Your Winnings Of: \n$" + winnings + "\nHas Been Transferred To Your Account ");
        return winnings;
    }
}
