package com.hisham.strategy;

/**
 * Created by Hisham on 17/Oct/2018 - 21:54
 */
public class DebitCard extends Card {

    public DebitCard(String nameOnCard, String number, String cvv, String expiry) {
        super(nameOnCard, number, cvv, expiry);
    }

    @Override
    public String getType() {
        return "debit";
    }

    @Override
    public void executeTransaction() {

    }
}
