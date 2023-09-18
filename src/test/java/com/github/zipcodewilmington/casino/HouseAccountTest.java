package com.github.zipcodewilmington.casino;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.github.zipcodewilmington.casino.HouseAccount;

class HouseAccountTest {
    @Test
    void testAll(){
        getBalance();
        acceptMoney();
        payout();
    }

    void getBalance() {
        //Given
        HouseAccount houseAccount = HouseAccount.getHouseAccount();
        //When
        int expected = houseAccount.getBalance();
        int actual = 1_000_000;

        //Then
        Assert.assertEquals(expected, actual);
    }

    void payout() {
        //Given
        HouseAccount houseAccount = HouseAccount.getHouseAccount();
        houseAccount.payout(30);
        //When
        int expected = houseAccount.getBalance();
        int actual = 1_000_000 + 40 - 30;
        //Then
        Assert.assertEquals(expected, actual);
    }

    void acceptMoney() {
        //Given
        HouseAccount houseAccount = HouseAccount.getHouseAccount();
        houseAccount.acceptMoney(40);
        //When
        int expected = houseAccount.getBalance();
        int actual = 1_000_000 + 40;

        //Then
        Assert.assertEquals(expected, actual);
    }
}