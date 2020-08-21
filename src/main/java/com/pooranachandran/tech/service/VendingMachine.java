package com.pooranachandran.tech.service;

import com.pooranachandran.tech.entity.CheckOutBag;
import com.pooranachandran.tech.enums.Coin;
import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.*;

import java.util.Map;

public interface VendingMachine {
    void insertCoin(Coin coin);

    void insertCoins(Map<Coin, Integer> customerCoins);

    void addProductToCart(Product product) throws OutOfStockException, InvalidProductQuantityException;

    void removeProductFromCart(Product product) throws OutOfStockException, InvalidProductQuantityException, InvalidUserOperationException;

    CheckOutBag checkOut() throws InsufficientFundException, ChangeNotAvailableException;

    void clearCart();

}
