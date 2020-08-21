package com.pooranachandran.tech.service;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.exception.InvalidUserOperationException;

import java.util.List;

public interface Cart {
    void add(Product product);

    Product remove(Product product) throws InvalidUserOperationException;

    int getTotalAmountInCents();

    List<Product> getCartList();
}
