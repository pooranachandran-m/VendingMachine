package com.pooranachandran.tech.enums;

/**
 * Keeps the Products supported by vending machine with its price in cents
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public enum Product {
    ECLAIRS(1),
    COKE(5),
    MOUNTAINDEW(5),
    DIARYMILK(2),
    NUTS(18);

    Product(int centPrice){
        this.centPrice=centPrice;
    }
    public int centPrice;
}
