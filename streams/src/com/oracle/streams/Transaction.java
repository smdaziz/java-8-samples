package com.oracle.streams;

/**
 * Created by smdaziz on 5/1/2017.
 */
public class Transaction {

    public static final int GROCERY = 1;
    public static final int RESTRAUNT = 2;
    public static final int CLOTHES = 3;
    public static final int FURNITURE = 4;

    private long id;
    private int type;
    private int value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
