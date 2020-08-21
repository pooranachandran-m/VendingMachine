package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.InvalidUserOperationException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test cases to verify the CustomerCart Implementation
 */
class CustomerCartTest {
    CustomerCart customerCart=new CustomerCart();

    Product coke=Product.COKE;
    Product dew=Product.MOUNTAINDEW;
    Product diarymilk=Product.DIARYMILK;

    @BeforeEach()
    public void beforeEach(){
        customerCart=new CustomerCart();
    }

    @Test
    void testAddFunctionAddsValidProduct() {
        customerCart.add(diarymilk);
        List<Product> lsProductList=customerCart.getCartList();
        Assert.assertTrue(lsProductList.contains(diarymilk));
    }

    @Test
    void testGetCartListReturnsNumberOfAddedList() {
        customerCart.add(coke);
        customerCart.add(dew);
        customerCart.add(diarymilk);
        List<Product> lsProductList=customerCart.getCartList();
        Assert.assertEquals(lsProductList.size(),3);
    }

    @Test
    void testRemoveOfProduct() throws InvalidUserOperationException {
        customerCart.add(coke);
        customerCart.add(dew);
        customerCart.add(diarymilk);
        Product removedProduct=customerCart.remove(coke);
        Assert.assertEquals(removedProduct,coke);
    }

    @Test
    void testRemoveThrowsInvalidUserOperationWhenRemovingNonExistingProduct() {
        assertThrows(InvalidUserOperationException.class, () -> {
           customerCart.remove(coke);
        });
    }
}