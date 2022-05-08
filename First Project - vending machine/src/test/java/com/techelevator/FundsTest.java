package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FundsTest {

    Funds fundsBalanceTest = new Funds();
    @Test
    public void fundsTest() {

        BigDecimal expectedResult = new BigDecimal("0.00");
        BigDecimal actualResult = fundsBalanceTest.getBalance();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void addToBalanceTest() {
        BigDecimal expectedResult = new BigDecimal("5.75");
        BigDecimal addthis = new BigDecimal("5.75");
        fundsBalanceTest.addToBalance(addthis);
        BigDecimal actualResult = fundsBalanceTest.getBalance();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void subtractFromBalanceTest() {
        BigDecimal expectedResult = new BigDecimal("-5.75");
        BigDecimal subtractthis = new BigDecimal("5.75");
        fundsBalanceTest.subtractFromBalance(subtractthis);
        BigDecimal actualResult = fundsBalanceTest.getBalance();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void toStringTest() {
        String actualResult = fundsBalanceTest.toString();
        String expectedResult = "balance=0.00";
        assertEquals(expectedResult,actualResult);
    }
}
