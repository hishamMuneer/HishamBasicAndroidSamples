package com.hisham.strategy;

/**
 * Created by Hisham on 17/Oct/2018 - 21:53
 */
public class CreditCard extends Card {

    public CreditCard(String nameOnCard, String number, String cvv, String expiry) {
        super(nameOnCard, number, cvv, expiry);
    }

    @Override
    public String getType() {
        return "Credit";
    }

    @Override
    public void executeTransaction() {

    }
}
