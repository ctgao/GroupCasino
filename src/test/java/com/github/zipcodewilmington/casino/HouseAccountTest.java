package com.github.zipcodewilmington.casino;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.github.zipcodewilmington.casino.HouseAccount;

class HouseAccountTest {

    @Test
    void getBalance() {
        //Given
        HouseAccount houseAccount = new HouseAccount(32);
        //When
        int expected = houseAccount.getBalance();
        int actual = 32;

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    void payout() {
        //Given
        HouseAccount houseAccount = new HouseAccount(32);
        houseAccount.payout(30);
        //When
        int expected = houseAccount.getBalance();
        int actual = 2;
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    void acceptMoney() {
        //Given
        HouseAccount houseAccount = new HouseAccount(32);
        houseAccount.acceptMoney(40);
        //When
        int expected = houseAccount.getBalance();
        int actual = 72;

        //Then
        Assert.assertEquals(expected, actual);
    }
}