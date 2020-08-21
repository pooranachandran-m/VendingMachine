package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.InvalidProductQuantityException;
import com.pooranachandran.tech.exception.OutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeloitteInventoryTest {
    DeloitteInventory deloitteInventory;

    Product coke=Product.COKE;
    Product eclairs=Product.ECLAIRS;

    @BeforeEach()
    public void beforeEach() throws InvalidProductQuantityException {
        deloitteInventory=new DeloitteInventory();
    }

    @Test
    void testDeloitteInventoryIsStartingAsExpected() throws InvalidProductQuantityException {
        deloitteInventory=new DeloitteInventory();
        assertNotNull(deloitteInventory);
    }

    @Test
    void testGetProductReturnsProductWhenAdded() throws InvalidProductQuantityException, OutOfStockException {
        deloitteInventory.putProducts(coke,2);
        Product product=deloitteInventory.getProduct(coke);
        assertEquals(coke,product);
        Product product2=deloitteInventory.getProduct(coke);
        assertEquals(coke,product2);
    }

    @Test
    void testGetProductThrowsOutOfStockExceptionWhenNoProductExists() throws InvalidProductQuantityException {
        deloitteInventory.putProducts(coke,2);
        assertThrows(OutOfStockException.class, () -> {
            Product product2=deloitteInventory.getProduct(eclairs);
        });
    }

    @Test
    void testExistsReturnsFalseWhenProductDoesntExist() {
        assertFalse(deloitteInventory.exists(eclairs));
    }

    @Test
    void testExistsReturnsTrueWhenProductExist() throws InvalidProductQuantityException {
        deloitteInventory.putProducts(eclairs,2);
        assertTrue(deloitteInventory.exists(eclairs));
    }

    @Test
    void testPutProductThrowsInvalidProductQuantityExceptionWhenInvalidQuantityIsProvided() throws InvalidProductQuantityException {
        assertThrows(InvalidProductQuantityException.class, () -> {
            deloitteInventory.putProducts(eclairs,-10);
        });
    }

}