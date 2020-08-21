package com.pooranachandran.tech.service;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.InvalidProductQuantityException;
import com.pooranachandran.tech.exception.OutOfStockException;

/**
 * To manage complete list of items such as chocolates, candy, cold-drink of a vending machine.
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public interface ProductInventory {
    boolean exists(Product product);

    Product getProduct(Product product) throws OutOfStockException;

    void putProducts(Product product, int quantity) throws InvalidProductQuantityException;

    void putProduct(Product product) throws InvalidProductQuantityException;
}
