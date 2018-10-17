package com.hisham.strategy;

import java.text.MessageFormat;

/**
 * Created by Hisham on 17/Oct/2018 - 21:52
 */
public abstract class Card implements PaymentMethod {

    private String nameOnCard;
    private String number;
    private String cvv;
    private String expiry;

    public Card(String nameOnCard, String number, String cvv, String expiry) {
        this.nameOnCard = nameOnCard;
        this.number = number;
        this.cvv = cvv;
        this.expiry = expiry;
    }


    @Override
    public String toString() {
        return MessageFormat.format("{0} card[name = {1}, number = {2}, CVV = {3}, expiration = {4}]",
                getType(), nameOnCard, number, cvv, expiry);
    }

    @Override
    public void pay(int cents) {
        System.out.println("Payed " + cents + " cents using " + toString());
    }

    public abstract String getType();
    public abstract void executeTransaction();
}
