package com.ec.springboot.logic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("wallet")
public class WalletImpl implements Wallet {
    private int cash = 0;

    @Override
    public int getCash() {
        return cash;
    }

    @Override
    public void addCash(int money) {
        cash += money;
    }

    @Override
    public boolean expandCash(int money) {
        if (cash >= money) {
            cash -= money;
            return true;
        }
        return false;
    }
}
