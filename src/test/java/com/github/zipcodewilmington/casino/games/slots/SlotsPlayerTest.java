package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotsPlayerTest {

    @Test
    public void testMakeBet() {
        CasinoAccount wallet = new CasinoAccount("Brent","", 500);
        IOConsole console = new IOConsole();
        SlotsPlayer brent = new SlotsPlayer(wallet,console);
        Integer expected = 1000;
//        Integer actual = brent.makeBet(expected);
//        Assert.assertEquals(expected,actual);

    }

    @Test
    public void testValidBet () {
        CasinoAccount wallet = new CasinoAccount("Brent","" ,1000);
        IOConsole console = new IOConsole();
        SlotsPlayer brent = new SlotsPlayer(wallet,console);
        Integer expected = 1000;
        Integer actual = brent.getWallet();
        Assert.assertEquals(expected,actual);

    }

    // need help!!!
    @Test
    public void testDepositPayOut() {
        CasinoAccount wallet = new CasinoAccount("Brent","", 1000);
        IOConsole console = new IOConsole();
        SlotsPlayer brent = new SlotsPlayer(wallet,console);

    }

}