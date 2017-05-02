package com.oracle.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smdaziz on 5/1/2017.
 */
public class ApproachTester {

    public static void main(String args[]) {
        List<Transaction> transactions = createTransactions();
        List<Integer> groceriesDescValue = Approach.getGroceriesDescValue_Java7(transactions);
        System.out.println(groceriesDescValue);
        groceriesDescValue = Approach.getGroceriesDescValue_Java8(transactions);
        System.out.println(groceriesDescValue);
        groceriesDescValue = Approach.getGroceriesDescValue_Java8_Parallel(transactions);
        System.out.println(groceriesDescValue);
    }

    public static List<Transaction> createTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction t1 = new Transaction();
        t1.setId(1);
        t1.setType(Transaction.CLOTHES);
        t1.setValue(10);
        Transaction t2 = new Transaction();
        t2.setId(2);
        t2.setType(Transaction.GROCERY);
        t2.setValue(20);
        Transaction t3 = new Transaction();
        t3.setId(3);
        t3.setType(Transaction.GROCERY);
        t3.setValue(30);
        Transaction t4 = new Transaction();
        t4.setId(4);
        t4.setType(Transaction.FURNITURE);
        t4.setValue(40);
        Transaction t5 = new Transaction();
        t5.setId(5);
        t5.setType(Transaction.CLOTHES);
        t5.setValue(50);
        Transaction t6 = new Transaction();
        t6.setId(6);
        t6.setType(Transaction.GROCERY);
        t6.setValue(60);
        Transaction t7 = new Transaction();
        t7.setId(7);
        t7.setType(Transaction.RESTRAUNT);
        t7.setValue(70);
        transactions.add(t1);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);
        transactions.add(t5);
        transactions.add(t6);
        transactions.add(t7);
        return transactions;
    }

}
