package com.ec.springboot.logic;

import org.springframework.stereotype.Service;

@Service
public interface Wallet {
    int getCash();

    void addCash(int money);

    boolean expandCash(int money);

}
