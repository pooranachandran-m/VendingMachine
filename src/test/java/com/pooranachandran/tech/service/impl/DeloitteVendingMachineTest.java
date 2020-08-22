package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.entity.CheckOutBag;
import com.pooranachandran.tech.enums.Coin;
import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test Customer Interaction with vending Machine
 *
 * @author Pooranachandran Muthusamy
 * @since 22-Aug-2020
 */
class DeloitteVendingMachineTest {
    private DeloitteVendingMachine deloitteVendingMachine;

    @BeforeEach
    public void beforeEach() throws InvalidProductQuantityException, OutOfStockException {
        deloitteVendingMachine = new DeloitteVendingMachine();
    }

    @Test
    public void testInstantiationOfDeloitteVendingMachine() throws InvalidProductQuantityException {
        deloitteVendingMachine = new DeloitteVendingMachine();
        Assert.assertNotNull(deloitteVendingMachine);
    }

    @Test
    public void testCheckOutIsWorkingAsExpected() throws InsufficientFundException, ChangeNotAvailableException, OutOfStockException, InvalidUserOperationException, InvalidProductQuantityException {
        // Insert 35 cents Nickle(5) x 2 + Quarter(25) x 1
        deloitteVendingMachine.insertCoin(Coin.NICKLE);//5
        deloitteVendingMachine.insertCoin(Coin.NICKLE);//5
        deloitteVendingMachine.insertCoin(Coin.QUARTER);//25

        // Select products worth 21 Cents
        deloitteVendingMachine.addProductToCart(Product.COKE);//5
        deloitteVendingMachine.addProductToCart(Product.COKE);//5
        deloitteVendingMachine.addProductToCart(Product.MOUNTAINDEW);//5
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2
        deloitteVendingMachine.removeProductFromCart(Product.NUTS);

        CheckOutBag checkOutBag = deloitteVendingMachine.checkOut();
        deloitteVendingMachine.printReceipt(checkOutBag);
        Assertions.assertEquals(checkOutBag.getBalanceCoins().size(), 5);
        Assertions.assertEquals(checkOutBag.getPurchasedProducts().size(), 6);
        Assertions.assertEquals(checkOutBag.getBillValueInCents(), 21);
        Assertions.assertEquals(checkOutBag.getBalanceInCents(), 14);
    }

    @Test
    public void testCustomerIsAbleToCheckOutAfterAddingSufficientFunds() throws OutOfStockException, InsufficientFundException, ChangeNotAvailableException, InvalidUserOperationException {
        // Insert 10 cents Nickle(5) x 2
        deloitteVendingMachine.insertCoin(Coin.NICKLE);//5
        deloitteVendingMachine.insertCoin(Coin.NICKLE);//5

        // Select products worth 21 Cents
        deloitteVendingMachine.addProductToCart(Product.COKE);//5
        deloitteVendingMachine.addProductToCart(Product.COKE);//5
        deloitteVendingMachine.addProductToCart(Product.MOUNTAINDEW);//5
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2

        //Expect Insufficient Fund Exception as 21 > 10 Cents
        assertThrows(InsufficientFundException.class, () -> {
            deloitteVendingMachine.checkOut();
        });

        //Add One More Quarter . Now Sum becomes 35
        deloitteVendingMachine.insertCoin(Coin.QUARTER);//25

        //Checkout Success and validate the Bag
        CheckOutBag checkOutBag=deloitteVendingMachine.checkOut();
        deloitteVendingMachine.printReceipt(checkOutBag);
        CheckOutBag cBag = deloitteVendingMachine.checkOut();
        Assertions.assertEquals(cBag.getBalanceCoins().size(), 5);
        Assertions.assertEquals(cBag.getPurchasedProducts().size(), 6);
        Assertions.assertEquals(cBag.getBillValueInCents(), 21);
        Assertions.assertEquals(cBag.getBalanceInCents(), 14);
    }

    @Test
    public void testCustomerIsAbleToCheckoutByRemoveProductFromCartOnInsufficientFunds() throws OutOfStockException, InsufficientFundException, ChangeNotAvailableException, InvalidUserOperationException, InvalidProductQuantityException {
        // Insert 10 cents Nickle(5) x 2
        deloitteVendingMachine.insertCoin(Coin.NICKLE);//5
        deloitteVendingMachine.insertCoin(Coin.NICKLE);//5

        // Select products worth 25 Cents
        deloitteVendingMachine.addProductToCart(Product.COKE);//5
        deloitteVendingMachine.addProductToCart(Product.DIARYMILK);//2
        deloitteVendingMachine.addProductToCart(Product.NUTS);//18

        //Expect Insufficient Fund Exception as 25 > 10 Cents
        assertThrows(InsufficientFundException.class, () -> {
            deloitteVendingMachine.checkOut();
        });

        //Remove Nuts (18 Cents) So that user can collect other products worth (7 Cents)
        deloitteVendingMachine.removeProductFromCart(Product.NUTS);// 18

        //Checkout Success and validate the Bag
        CheckOutBag checkOutBag=deloitteVendingMachine.checkOut();
        deloitteVendingMachine.printReceipt(checkOutBag);

        CheckOutBag cBag = deloitteVendingMachine.checkOut();
        Assertions.assertEquals(cBag.getBalanceCoins().size(), 3);
        Assertions.assertEquals(cBag.getPurchasedProducts().size(), 2);
        Assertions.assertEquals(cBag.getBillValueInCents(), 7);
        Assertions.assertEquals(cBag.getBalanceInCents(), 3);
    }

    @Test
    public void testCustomerCheckoutWithoutAddingAnyProductThrowsInvalidUserException() {
        assertThrows(InvalidUserOperationException.class, () -> {
            deloitteVendingMachine.checkOut();
        });
    }

    @Test
    public void testRemoveNonExistingProductShouldThrowInvalidUserException(){
        assertThrows(InvalidUserOperationException.class, () -> {
            deloitteVendingMachine.removeProductFromCart(Product.NUTS);
        });
    }

    @Test
    public void testAddProductThrowsOutOfStockExceptionWhenProductNotAvailableInInventory(){
        assertThrows(OutOfStockException.class, () -> {
            deloitteVendingMachine.addProductToCart(Product.ECLAIRS);
        });
    }
}