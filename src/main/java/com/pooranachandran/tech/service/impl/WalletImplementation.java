package com.pooranachandran.tech.service.impl;

import com.pooranachandran.tech.enums.Coin;
import com.pooranachandran.tech.service.Wallet;

import java.util.HashMap;
import java.util.Map;

/**
 * Acts as an wallet for Both user and vending machine to keep the Coins in a Bag
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class WalletImplementation implements Wallet {
    Map<Coin, Integer> coinMap;

    WalletImplementation() {
        this.coinMap = new HashMap<>();
    }

    WalletImplementation(Map<Coin, Integer> coinMap) {
        this.coinMap = coinMap;
    }

    @Override
    public void creditCoin(Coin coin) {
        int coinQuantity = coinMap.containsKey(coin) ? coinMap.get(coin) : 0;
        coinMap.put(coin, coinQuantity + 1);
    }

    @Override
    public Coin debitCoin(Coin coin) {
        if (hasCoin(coin)) {
            int coinCount = coinMap.get(coin) - 1;
            if (coinCount > 0)
                coinMap.put(coin, coinCount);
        }
        return coin;
    }

    @Override
    public void bulkCredit(Map<Coin, Integer> coins) {
        for (Map.Entry<Coin, Integer> coinEntry : coins.entrySet()) {
            Coin coin = coinEntry.getKey();
            for (int coinCount = coinEntry.getValue(); coinCount > 0; coinCount--) {
                creditCoin(coin);
            }
        }
    }


    @Override
    public boolean hasCoin(Coin coin) {
        if (coinMap.containsKey(coin) && coinMap.get(coin) > 0)
            return true;
        else
            return false;
    }

    @Override
    public int getBalanceInCents() {
        return coinsToCent(coinMap);
    }

    public static int coinsToCent(Map<Coin, Integer> coins) {
        int cents = 0;
        for (Map.Entry<Coin, Integer> coinEntry : coins.entrySet()) {
            Coin coin = coinEntry.getKey();
            Integer quantity = coinEntry.getValue();
            cents += (coin.getCentValue() * quantity);
        }
        return cents;
    }
}
