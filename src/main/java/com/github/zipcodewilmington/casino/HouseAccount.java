package com.github.zipcodewilmington.casino;

public class HouseAccount {

    private static HouseAccount houseAccount;
    private int accBalance;

    HouseAccount() {}

    private static synchronized HouseAccount getHouseAccount()  {
        if (houseAccount == null) {
            houseAccount = new HouseAccount();
        }
        return houseAccount;
    }

    HouseAccount(int accBalance) {
        this.accBalance = accBalance;
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
