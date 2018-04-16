package com.ec.springboot.controller;

import com.ec.springboot.logic.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wallet")
public class MainController {
    @Autowired
    private Wallet wallet;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    String getStatus() {
        return "You have " + wallet.getCash() + " money.";
    }

    @RequestMapping(value = "/cash/{money}", method = RequestMethod.PUT)
    @ResponseBody
    String addMoney(@PathVariable int money) {
        wallet.addCash(money);
        return "You add " + money + " money.";
    }

    @RequestMapping(value = "/cash/{money}", method = RequestMethod.DELETE)
    @ResponseBody
    String expandMoney(@PathVariable int money) {
        boolean canWaist = wallet.expandCash(money);
        if (canWaist) {
            return "You expand " + money + " money.";
        }
        return "You have not enough money.";
    }
}
