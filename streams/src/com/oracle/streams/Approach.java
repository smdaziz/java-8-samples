package com.oracle.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by smdaziz on 5/1/2017.
 */
public class Approach {

    public static List<Integer> getGroceriesDescValue_Java7(List<Transaction> transactions) {
        List<Transaction> groceryTransactions = new ArrayList<Transaction>();
        for(Transaction t : transactions) {
            if(t.getType() == Transaction.GROCERY) {
                groceryTransactions.add(t);
            }
        }

        groceryTransactions.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return t2.getValue() - t1.getValue();
            }
        });

        List<Integer> transactionIds = new ArrayList<Integer>();
        for(Transaction t : groceryTransactions) {
            transactionIds.add((int)t.getId());
        }
        return transactionIds;
    }

    public static List<Integer> getGroceriesDescValue_Java8(List<Transaction> transactions) {
        //not optimal
        /*return transactions.stream()
                            .filter(t -> t.getType() == Transaction.GROCERY)
                            .sorted(comparing(Transaction::getValue).reversed())
                            .map(Transaction::getId)
                            .collect(Collectors.toList())
                            .stream()
                            .map(t -> t.intValue())
                            .collect(Collectors.toList());*/
        //smarter way
//        return transactions.stream()
//                            .filter(t -> t.getType() == Transaction.GROCERY)
//                            .sorted(comparing(Transaction::getValue).reversed())
//                            .map(Transaction::getId)
//                            .map(t -> t.intValue())
//                            .collect(Collectors.toList());
        return transactions.stream()
                            .filter(t -> t.getType() == Transaction.GROCERY)
                            .sorted(comparing(Transaction::getValue).reversed())
                            .map(Transaction::getId)
                            .map(Long::intValue)
                            .collect(Collectors.toList());
    }

    public static List<Integer> getGroceriesDescValue_Java8_Parallel(List<Transaction> transactions) {
        return transactions.parallelStream()
                            .filter(t -> t.getType() == Transaction.GROCERY)
                            .sorted(comparing(Transaction::getValue).reversed())
                            .map(Transaction::getId)
                            .map(Long::intValue)
                            .collect(Collectors.toList());
    }

}
