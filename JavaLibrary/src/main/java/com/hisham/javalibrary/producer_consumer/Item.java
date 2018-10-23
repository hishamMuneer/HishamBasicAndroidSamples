package com.hisham.javalibrary.producer_consumer;

/**
 * Created by Hisham on 23/Oct/2018 - 18:42
 */
public class Item {

    public Item(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }
}
