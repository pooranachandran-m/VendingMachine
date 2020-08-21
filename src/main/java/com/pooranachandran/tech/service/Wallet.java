package com.pooranachandran.tech.service;

import com.pooranachandran.tech.enums.Coin;

import java.util.Map;

public interface Wallet {
    void creditCoin(Coin coin);

    Coin debitCoin(Coin coin);

    void bulkCredit(Map<Coin, Integer> coins);

    int getBalanceInCents();

    boolean hasCoin(Coin coin);
}
