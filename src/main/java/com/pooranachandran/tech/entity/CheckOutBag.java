package com.pooranachandran.tech.entity;

import com.pooranachandran.tech.enums.Coin;
import com.pooranachandran.tech.enums.Product;

import java.util.List;

/**
 * POJO To stored the checkout data
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-20200
 */
public class CheckOutBag {
    private int billValueInCents;
    private int balanceInCents;
    private List<Coin> balanceCoins;
    private List<Product> purchasedProducts;

    public int getBillValueInCents() {
        return billValueInCents;
    }

    public void setBillValueInCents(int billValueInCents) {
        this.billValueInCents = billValueInCents;
    }

    public int getBalanceInCents() {
        return balanceInCents;
    }

    public void setBalanceInCents(int balanceInCents) {
        this.balanceInCents = balanceInCents;
    }

    public List<Coin> getBalanceCoins() {
        return balanceCoins;
    }

    public void setBalanceCoins(List<Coin> balanceCoins) {
        this.balanceCoins = balanceCoins;
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
