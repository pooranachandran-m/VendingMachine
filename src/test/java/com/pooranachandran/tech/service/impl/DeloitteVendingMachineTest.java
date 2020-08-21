package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.entity.CheckOutBag;
import com.pooranachandran.tech.enums.Coin;
import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.ChangeNotAvailableException;
import com.pooranachandran.tech.exception.InsufficientFundException;
import com.pooranachandran.tech.exception.InvalidProductQuantityException;
import com.pooranachandran.tech.exception.OutOfStockException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class DeloitteVendingMachineTest {
    private DeloitteVendingMachine deloitteVendingMachine;

    @BeforeEach
    public void beforeEach() throws InvalidProductQuantityException, OutOfStockException {
        deloitteVendingMachine = new DeloitteVendingMachine();
        HashMap<Coin, Integer> initialCoinMap = new HashMap<>();


        initialCoinMap.put(Coin.QUARTER, 1);
        initialCoinMap.put(Coin.NICKLE, 2);
        deloitteVendingMachine.insertCoins(initialCoinMap);
        deloitteVendingMachine.addProductToCart(Product.COKE);
        deloitteVendingMachine.addProductToCart(Product.COKE);
        deloitteVendingMachine.addProductToCart(Product.MOUNTAINDEW);
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);
    }

    @Test
    public void testInstantiationOfDeloitteVendingMachine() throws InvalidProductQuantityException {
        deloitteVendingMachine = new DeloitteVendingMachine();
        Assert.assertNotNull(deloitteVendingMachine);
    }

    @Test
    public void testCheckOutIsWorkingAsExpected() throws InsufficientFundException, ChangeNotAvailableException, OutOfStockException {
        CheckOutBag cBag = deloitteVendingMachine.checkOut();
        Assertions.assertEquals(cBag.getBalanceCoins().size(), 5);
        Assertions.assertEquals(cBag.getPurchasedProducts().size(), 6);
        Assertions.assertEquals(cBag.getBillValueInCents(), 21);
        Assertions.assertEquals(cBag.getBalanceInCents(), 14);
    }


}