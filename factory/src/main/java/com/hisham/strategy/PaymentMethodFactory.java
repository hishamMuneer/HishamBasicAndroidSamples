package com.hisham.strategy;

/**
 * Created by Hisham on 17/Oct/2018 - 21:59
 */
public class PaymentMethodFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        switch (type) {
            case "credit":
                return createCreditCard();
            case "debit":
                return createDebitCard();
            case "cash":
                return createCash();
        }
        throw new IllegalArgumentException();
    }

    public static CreditCard createCreditCard() {
        return new CreditCard("John Doe", "4111111111111111", "123", "01/22");
    }

    public static DebitCard createDebitCard() {
        return new DebitCard("John Doe", "4111111111111111", "123", "01/22");
    }

    public static Cash createCash() {
        return new Cash();
    }
}