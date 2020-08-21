package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.InvalidUserOperationException;
import com.pooranachandran.tech.service.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold the Customers product list and to calculate the total bill info
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class CustomerCart implements Cart {
    private List<Product> productList;

    CustomerCart() {
        productList = new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public Product remove(Product product) throws InvalidUserOperationException {
        if (productList.remove(product))
            return product;
        else
            throw new InvalidUserOperationException("Product you are trying to remove doesnt exist in your cart");
    }

    @Override
    public int getTotalAmountInCents() {
        int totalBill = 0;
        for (Product product : productList) {
            totalBill += product.centPrice;
        }
        return totalBill;
    }

    @Override
    public List<Product> getCartList() {
        return productList;
    }

}
