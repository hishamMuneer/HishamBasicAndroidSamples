package com.hisham.strategy;

/**
 * Created by Hisham on 17/Oct/2018 - 22:07
 */
public class App {

    public static void main(String args[]){
        Bill bill = new Bill();
        bill.addLineItem(new LineItem("Milk", 200));
        bill.addLineItem(new LineItem("Bread", 150));

        bill.pay(PaymentMethodFactory.getPaymentMethod("debit"));
    }

}
