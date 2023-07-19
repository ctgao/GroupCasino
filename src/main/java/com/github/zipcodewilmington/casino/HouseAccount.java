package com.github.zipcodewilmington.casino;

public class HouseAccount {

    private static HouseAccount houseAccount;
    private int accBalance;

    private HouseAccount(int accBalance) {
        this.accBalance = accBalance;
    }

    public int getBalance() {
        return this.accBalance;}

    public int payout(int losses) {return 0;}

    public void acceptMoney(int winnings) {}

}
