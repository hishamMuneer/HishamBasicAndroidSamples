package com.hisham.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hisham on 17/Oct/2018 - 22:00
 */
public class Bill {
    private List<LineItem> lineItems = new ArrayList<>();

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }
    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    private int getSum(){
        int sum = 0;
        for (LineItem lineItem : lineItems) {
            int costInCents = lineItem.getCostInCents();
            sum += costInCents;
        }
        return sum;
    }

    public void pay(PaymentMethod method){
        method.pay(getSum());
    }
}
