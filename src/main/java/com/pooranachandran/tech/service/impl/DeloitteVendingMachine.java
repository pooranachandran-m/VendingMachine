package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.entity.CheckOutBag;
import com.pooranachandran.tech.enums.Coin;
import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.*;
import com.pooranachandran.tech.service.VendingMachine;
import com.pooranachandran.tech.service.Wallet;

import java.util.*;
import java.util.function.Predicate;

/**
 * Vending Machine which provides user option to
 * 1.Insert Coin
 * 2.Select Product
 * 3.CheckOut
 * 4.Clear Cart
 * 5.Print Receipt
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */

public class DeloitteVendingMachine implements VendingMachine {
    private DeloitteInventory deloitteInventory;
    private Wallet vendingMachinesCoinBag;
    private CustomerCart customerCart;
    private Wallet customerCoinBag;

    public DeloitteVendingMachine() throws InvalidProductQuantityException {
        deloitteInventory = new DeloitteInventory();

        // Vending Machine's Wallet with Coins for providing Change during transactions
        HashMap<Coin, Integer> initialCoinMap = new HashMap<>();
        initialCoinMap.put(Coin.QUARTER, 10);
        initialCoinMap.put(Coin.DIME, 1000);
        initialCoinMap.put(Coin.NICKLE, 10);
        initialCoinMap.put(Coin.CENT, 1000);
        vendingMachinesCoinBag = new WalletImplementation(initialCoinMap);

        //Wallet to keep customers coin
        customerCoinBag = new WalletImplementation();
        customerCart = new CustomerCart();
    }

    @Override
    public void insertCoin(Coin customerCoin) {
        customerCoinBag.creditCoin(customerCoin);
    }

    @Override
    public void insertCoins(Map<Coin, Integer> customerCoins) {
        customerCoinBag.bulkCredit(customerCoins);
    }

    @Override
    public void addProductToCart(Product product) throws OutOfStockException {
        Product product1 = deloitteInventory.getProduct(product);
        customerCart.add(product1);
    }

    @Override
    public void removeProductFromCart(Product product) throws InvalidUserOperationException, InvalidProductQuantityException {
        Product productRemoved = customerCart.remove(product);
        deloitteInventory.putProduct(productRemoved);
    }

    @Override
    public CheckOutBag checkOut() throws InsufficientFundException, ChangeNotAvailableException {
        CheckOutBag checkOutBag = new CheckOutBag();
        int cartTotal = customerCart.getTotalAmountInCents();
        int userCoinsTotal = customerCoinBag.getBalanceInCents();

        int changeBalanceInCents = userCoinsTotal - cartTotal;
        checkOutBag.setBillValueInCents(cartTotal);
        checkOutBag.setBalanceInCents(changeBalanceInCents);
        List<Coin> coinChanges;
        if (changeBalanceInCents >= 0) {
            coinChanges = new ArrayList<>();
            int balance = changeBalanceInCents;
            while (balance > 0) {
                if (balance >= Coin.QUARTER.getCentValue()
                        && vendingMachinesCoinBag.hasCoin(Coin.QUARTER)) {
                    Coin debittedCoin = vendingMachinesCoinBag.debitCoin(Coin.QUARTER);
                    coinChanges.add(debittedCoin);
                    balance = balance - debittedCoin.getCentValue();
                    continue;

                } else if (balance >= Coin.DIME.getCentValue()
                        && vendingMachinesCoinBag.hasCoin(Coin.DIME)) {
                    Coin debittedCoin = vendingMachinesCoinBag.debitCoin(Coin.DIME);
                    coinChanges.add(debittedCoin);
                    balance = balance - debittedCoin.getCentValue();
                    continue;

                } else if (balance >= Coin.NICKLE.getCentValue()
                        && vendingMachinesCoinBag.hasCoin(Coin.NICKLE)) {
                    Coin debittedCoin = vendingMachinesCoinBag.debitCoin(Coin.NICKLE);
                    coinChanges.add(debittedCoin);
                    balance = balance - debittedCoin.getCentValue();
                    continue;

                } else if (balance >= Coin.CENT.getCentValue()
                        && vendingMachinesCoinBag.hasCoin(Coin.CENT)) {
                    Coin debittedCoin = vendingMachinesCoinBag.debitCoin(Coin.CENT);
                    coinChanges.add(debittedCoin);
                    balance = balance - debittedCoin.getCentValue();
                    continue;

                } else {
                    clearCart();
                    throw new ChangeNotAvailableException(changeBalanceInCents);
                }
            }
            checkOutBag.setBalanceCoins(coinChanges);
        } else {
            clearCart();
            throw new InsufficientFundException(cartTotal, userCoinsTotal);
        }
        checkOutBag.setPurchasedProducts(customerCart.getCartList());
        return checkOutBag;
    }

    @Override
    public void clearCart() {

        customerCart.getCartList().forEach(product -> {
            try {
                deloitteInventory.putProduct(product);
            } catch (InvalidProductQuantityException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void printReceipt(CheckOutBag checkOutBag) {
        Set<Product> productSet = new TreeSet(checkOutBag.getPurchasedProducts());;
        String border="----------------------------------------------------------";
        System.out.println("------------------DELOITTE VENDING MACHINE---------------------");
        System.out.println(border);
        System.out.printf("|%-20s |%-10s |%-10s |%-10s|\n", "Name", "Price","Qty.","Total");
        System.out.println(border);
        productSet.forEach(product -> {
            long quantity=checkOutBag.getPurchasedProducts().stream().filter(product1 -> (product == product1)).count();
            System.out.printf("|%-20s |%-10s |%-10s |%-10s|\n", product.name(), product.centPrice,quantity,product.centPrice*quantity);
        });

        System.out.println(border);
        System.out.printf("%-33s Grand Total : %-10s\n","",checkOutBag.getBillValueInCents());

        System.out.printf("%-15s : %-10s\n","INSERTED COINS",checkOutBag.getBillValueInCents()+checkOutBag.getBalanceInCents());
        System.out.printf("%-15s : %-10s\n","BILLABLE COINS",checkOutBag.getBillValueInCents());
        System.out.printf("%-15s : %-10s\n","CHANGE COINS",checkOutBag.getBalanceCoins());
        System.out.println("NOTE : All currencies are displayed in CENTS");
        System.out.println("Copyright (c) to pooranachandran.com");
        System.out.println("------------------THANKS FOR VISITING---------------------");
    }
}
