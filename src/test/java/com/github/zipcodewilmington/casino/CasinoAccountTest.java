//package com.github.zipcodewilmington.casino;
//
//import com.github.zipcodewilmington.Casino;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CasinoAccountTest {
//
//    @Test
//    void getAccBalance() {
//        //Given
//        CasinoAccount bob = new CasinoAccount("Bob", 5_000);
//
//        //When
//        int expectedNum = bob.getAccBalance();
//        int actualNum = 5_000;
//
//        //Then
//        Assert.assertEquals(expectedNum, actualNum);
//
//    }
//
//    @Test
//    void setAccBalance() {
//        //Given
//        CasinoAccount fred = new CasinoAccount("Fred", 100);
//        fred.setAccBalance(3_000);
//
//        //When
//        int expectedNum = fred.getAccBalance();
//        int actualNum = 3_000;
//
//        //Then
//        Assert.assertEquals(expectedNum, actualNum);
//
//    }
//
//    @Test
//    void getAccOwner() {
//        //Given
//        CasinoAccount jawn = new CasinoAccount("Jawn", 100);
//
//        //When
//        String expectdOwner = "Jawn";
//        String actualOwner = jawn.getAccOwner();
//
//        //Then
//        Assert.assertEquals(expectdOwner, actualOwner);
//
//    }
//
//    @Test
//    void updateAccBalance() {
//        //Given
//        CasinoAccount sam = new CasinoAccount("Sam", 100);
//        sam.updateAccBalance(100);
//
//        //When
//        int expectedNum = 200;
//        int actualNum = sam.getAccBalance();
//
//        //Then
//        Assert.assertEquals(expectedNum, actualNum);
//
//    }
//}