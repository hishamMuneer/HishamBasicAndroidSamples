package com.hisham.strategy;

/**
 * Created by Hisham on 17/Oct/2018 - 21:51
 */
public class Cash implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.print("Amount paid: " + amount + " - Cash");
    }
}
