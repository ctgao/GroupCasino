package com.github.zipcodewilmington.casino;

public class HouseAccount {

    private static HouseAccount houseAccount;
    private int accBalance;

    private HouseAccount() {
        accBalance = 1_000_000;
    }

    public static synchronized HouseAccount getHouseAccount()  {
        if (houseAccount == null) {
            houseAccount = new HouseAccount();
        }
        return houseAccount;
    }

    public int getBalance() {
        return this.accBalance;}

    public int payout(int losses) {
        //Casino is losing money
        this.accBalance = accBalance - losses;
        return losses;
    }

    public void acceptMoney(int winnings) {
        //Casino is gaining money
        this.accBalance = accBalance + winnings;
    }

}
