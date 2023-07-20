package com.github.zipcodewilmington.playertests;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerClass;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTests {


    @Test
    public void testGetWallet () {
        //Given
        CasinoAccount wallet = new CasinoAccount("Brent",22);
        PlayerClass brent  = new PlayerClass(wallet,null,null,null);
        CasinoAccount expected = wallet;
        //When
        CasinoAccount actual = brent.getWallet();
        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void  testTotalGamesWon () {
        CasinoAccount wallet = new CasinoAccount("Brent",22);
        PlayerClass brent  = new PlayerClass(wallet,12,14,null);
        Integer expected = 12;
        Integer actual = brent.getTotalGamesWon();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testTotalGamesPlayed () {
        CasinoAccount wallet = new CasinoAccount("Brent",22);
        PlayerClass brent  = new PlayerClass(wallet,12,14,null);
        Integer expected = 14 ;
        Integer actual = brent.getTotalGamesPlayed();
        Assert.assertEquals(expected,actual);
    }


}
