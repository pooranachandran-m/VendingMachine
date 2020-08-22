package com.pooranachandran.tech;

import com.pooranachandran.tech.entity.CheckOutBag;
import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.enums.VendingMachineType;
import com.pooranachandran.tech.exception.*;
import com.pooranachandran.tech.factory.VendingMachineFactory;
import com.pooranachandran.tech.service.VendingMachine;

import static com.pooranachandran.tech.enums.Coin.DIME;
import static com.pooranachandran.tech.enums.Coin.NICKLE;

/**
 * Class to demonstrate the Vending Machine operation in Deloitte Office
 */
public class DeloitteOffice {
    public static void main(String args[]){
        try {
            //Factory to manufacture vending machines
            VendingMachine deloitteMachine=VendingMachineFactory.manufactureVendingMachine(VendingMachineType.DELOITTE);

            // Insert 15 Cents
            deloitteMachine.insertCoin(DIME);//10
            deloitteMachine.insertCoin(NICKLE);//15

            // Add Product worth 7 Cents
            deloitteMachine.addProductToCart(Product.COKE);//5
            deloitteMachine.addProductToCart(Product.DIARYMILK);//2

            //Received the Bag with Product and Coins
            CheckOutBag checkOutBag=deloitteMachine.checkOut();

            // Bill 7 Cents and Balance 8 Cents
            deloitteMachine.printReceipt(checkOutBag);
        } catch (InvalidProductQuantityException e) {
            e.printStackTrace();
        } catch (VendingMachineDesignNotAvailableException vendingMachineDesignNotAvailableException) {
            vendingMachineDesignNotAvailableException.printStackTrace();
        } catch (OutOfStockException e) {
            e.printStackTrace();
        } catch (ChangeNotAvailableException e) {
            e.printStackTrace();
        } catch (InsufficientFundException e) {
            e.printStackTrace();
        }
    }
}
