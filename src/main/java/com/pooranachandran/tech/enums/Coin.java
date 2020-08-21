package com.pooranachandran.tech.enums;

/**
 * Keeps the Coins supported by vending machine with its cent value
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public enum Coin {
    NICKLE(5),
    DIME(10),
    QUARTER(25),
    CENT(1);

    Coin(int centValue){
        this.centValue=centValue;
    }

    private int centValue;

    public int getCentValue() {
        return centValue;
    }
}
