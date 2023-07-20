//package com.github.zipcodewilmington.casino;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//public class PlayerTests {
//
//    @Test
//    public void testGetWallet() {
//        //Given
//        CasinoAccount wallet = new CasinoAccount("Brent", 5600);
//
//        PlayerClass brent = new PlayerClass(wallet, null, null, null);
//
//        Integer expected = wallet.getAccBalance();
//
//        //When
//        Integer actual = brent.getWallet();
//
//        //Then
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testTotalGamesWon() {
//        //Given
//        CasinoAccount wallet = new CasinoAccount("Brent", 5600);
//
//        PlayerClass brent = new PlayerClass(wallet, 12, 15, null);
//
//
//        //When
//        Integer expected = 12;
//        Integer actual = brent.getTotalGamesWon();
//
//        //Then
//        Assert.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void testTotalGamesPlayed() {
//        //Given
//        CasinoAccount wallet = new CasinoAccount("Brent", 5600);
//
//        PlayerClass brent = new PlayerClass(wallet, 12, 15, null);
//
//
//        //When
//        Integer expected = 15;
//        Integer actual = brent.getTotalGamesPlayed();
//
//        //Then
//        Assert.assertEquals(expected, actual);
//
//    }
//}
