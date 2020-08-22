package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.InvalidProductQuantityException;
import com.pooranachandran.tech.exception.OutOfStockException;
import com.pooranachandran.tech.service.ProductInventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to hold the Vending Machines product list and the stock availability of the product
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class DeloitteInventory implements ProductInventory {
    Map<Product, Integer> productMap;

    DeloitteInventory() throws InvalidProductQuantityException {
        productMap = new HashMap();
        putProducts(Product.COKE, 10);
        putProducts(Product.DIARYMILK, 35);
        putProducts(Product.MOUNTAINDEW, 2);
        putProducts(Product.NUTS, 5);
    }

    @Override
    public boolean exists(Product product) {
        if (productMap.containsKey(product) && productMap.get(product) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Product getProduct(Product product) throws OutOfStockException {
        if (exists(product)) {
            int revisedQuantity = productMap.get(product) - 1;
            if (revisedQuantity > 0)
                productMap.put(product, revisedQuantity);
            return product;
        } else {
            throw new OutOfStockException(product);
        }
    }

    @Override
    public void putProducts(Product product, int quantity) throws InvalidProductQuantityException {
        if (quantity > 0)
            productMap.put(product, quantity);
        else
            throw new InvalidProductQuantityException(quantity);
    }

    @Override
    public void putProduct(Product product) throws InvalidProductQuantityException {
        putProducts(product, 1);
    }
}
