package com.pooranachandran.tech.exception;

import com.pooranachandran.tech.enums.Product;
/**
 * Exception occurs When Product Is Not available in the Vending Machines Inventory
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class OutOfStockException extends Exception {
    public OutOfStockException(Product product){
        super(product.name() + "is not available in our inventory.");
    }
}
