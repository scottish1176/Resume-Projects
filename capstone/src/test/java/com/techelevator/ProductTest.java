package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

    BigDecimal testValue = new BigDecimal("0.00");
    Product testProduct = new Product("testName", "testDescription", testValue);

    @Test
    public void subtractStockTest(){

        int expectedResult = 6;
        testProduct.subtractInventory();
        int actualresult = testProduct.getInventory();
        assertEquals(expectedResult,actualresult);
    }

    @Test
    public void toStringTest () {
        String expectedResult = "Product{name='testName', description='testDescription', price=0.00, stock=7}";
        String actualResult = testProduct.toString();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void gettersTest() {


        String expectedResult = "testName testDescription 0.00";
        String actualResult = testProduct.getName() +" "+ testProduct.getDescription() + " "+ testProduct.getPrice();
        assertEquals(expectedResult,actualResult);
    }

}
