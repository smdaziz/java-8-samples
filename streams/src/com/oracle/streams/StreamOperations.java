package com.oracle.streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by smdaziz on 5/3/2017.
 */
public class StreamOperations {

    public static void main(String args[]) {
        StreamOperations instance = new StreamOperations();
        instance.lazyOperationsDemo();
        instance.matchOperationsDemo();
        instance.findOperations();
        instance.optionalDemo();
        instance.functionMapDemo();
        instance.reduceDemo();
        instance.aggregateDemo();
        instance.rangeDemo();
        instance.fileStreamsDemo();
        instance.limitInfiniteStreams();
        instance.streamInterference();
        instance.parallelStreamInterference();
        instance.threadSafeInterference();
        instance.threadSafeParallelInterference();
        //running this would result in an exception, just to demo
        //why we need to avoid state, when working with streams
//        instance.statefulLambda();
        instance.processStreamAndAdd();
        instance.streamOrderingEffect();
        instance.processParallelStreamAndAdd();
        instance.processParallelStreamNoSideEffects();
    }

    public void lazyOperationsDemo() {
        System.out.println("lazyOperationsDemo...");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> twoEvenSquares = numbers.stream()
                                                .filter(n -> {
                                                    System.out.println("Filtering " + n);
                                                    return n%2 == 0;
                                                })
                                                .map(n -> {
                                                    System.out.println("Mapping " + n);
                                                    return n*n;
                                                })
                                                .limit(3)
                                                .collect(toList());
        System.out.println("Input : " + numbers);
        System.out.println("Output : " + twoEvenSquares);
        System.out.println();
    }

    public void matchOperationsDemo() {
        System.out.println("matchOperationsDemo...");
        List<Integer> numbers = Arrays.asList(1, 6, 3, 2);
        boolean anyNegative = numbers.stream()
                                    .anyMatch(n -> n < 0);
        boolean allPositive = numbers.stream()
                                    .allMatch(n -> n > 0);
        boolean zero = numbers.stream()
                            .noneMatch(n -> n == 0);
        System.out.print(numbers);
        if(anyNegative) {
            System.out.print(" contains negative integers");
        } else {
            System.out.print(" contains no negative integers");
        }
        if(allPositive) {
            System.out.print(", contains all positive integers");
        } else {
            System.out.print(" contains no positive integers");
        }
        if(!zero) {
            System.out.print(" and contains a zero");
        }
        System.out.println("");
    }

    public void findOperations() {
        System.out.println("findOperations...");
        List<Transaction> transactions = ApproachTester.createTransactions();
        transactions.stream()
                    .filter(t -> t.getType() == Transaction.CLOTHES)
                    .findAny()
                    .ifPresent(System.out::println);
        System.out.println();
    }

    public void optionalDemo() {
        System.out.println("optionalDemo...");
        List<Transaction> transactions = ApproachTester.createTransactions();
        Optional<Transaction> groceryTransactions = transactions.stream()
                .filter(t -> t.getType() == Transaction.GROCERY)
                .findAny();
//        System.out.println(groceryTransactions);
        if(groceryTransactions.isPresent())
            System.out.println(groceryTransactions);
        System.out.println();
    }

    public void functionMapDemo() {
        System.out.println("functionMapDemo...");
        List<String> fruits = Arrays.asList("Apple", "Orange", "Mango", "Strawberry");
        List<Integer> furitLengths = fruits.stream()
                                            .map(String::length)
                                            .collect(toList());
        System.out.println(fruits + " : " + furitLengths);
        System.out.println();
    }

    public void reduceDemo() {
        System.out.println("reduceDemo...");
        List<Integer> numbers = Arrays.asList(1, 6, 3, 2);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a+b);
//        int product = numbers.stream()
//                .reduce(1, (a, b) -> a*b);
        int product = numbers.stream()
                .reduce(1, Integer::max);
        System.out.println("Sum of " + numbers + " : " + sum);
        System.out.println("Product of " + numbers + " : " + product);
        System.out.println();
    }

    public void aggregateDemo() {
        System.out.println("aggregateDemo...");
        List<Transaction> transactions = ApproachTester.createTransactions();
        int totalValue = transactions.stream()
                                        .mapToInt(Transaction::getValue)
                                        .sum();
        System.out.println("Total Value of transactions : " + totalValue);
        System.out.println();
    }

    public void rangeDemo() {
        System.out.println("rangeDemo...");
        IntStream.range(1, 100)
                .forEach(System.out::print);
        int oddCount = (int) IntStream.range(1, 100)
                                    .filter(n -> n%2 == 1)
                                    .count();
        System.out.println("Odds in [1, 100] : " + oddCount);
        System.out.println();
    }

    public void fileStreamsDemo() {
        System.out.println("fileStreamsDemo...");
//        long numberOfLines = 0;
        try {
//            numberOfLines = Files.lines(Paths.get("countries.txt"), Charset.defaultCharset())
//                                        .count();
//            System.out.println("No. of lines in countries.txt : " + numberOfLines);
            Files.lines(Paths.get("countries.txt"), Charset.defaultCharset())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void limitInfiniteStreams() {
        System.out.println("limitInfiniteStreams...");
        Stream<Integer> integerStream = Stream.iterate(10, n -> n + 10);
        integerStream.limit(10)
                    .forEach(System.out::println);
        System.out.println();
    }

    public void streamInterference() {
        System.out.println("streamInterference...");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
//        List<Integer> list = Arrays.asList(1, 2, 3);
        Stream<Integer> stream = list.stream();
        list.add(4);
        List<Integer> resultList = stream.collect(toList());
        System.out.println("input : " + list);
        System.out.println("output : " + resultList);
        System.out.println();
    }

    public void parallelStreamInterference() {
        System.out.println("parallelStreamInterference...");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        Stream<Integer> stream = list.parallelStream();
        list.add(4);
        List<Integer> resultList = stream.collect(toList());
        System.out.println("input : " + list);
        System.out.println("output : " + resultList);
        System.out.println();
    }

    public void threadSafeInterference() {
        System.out.println("threadSafeInterference...");
        List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Stream<Integer> stream = list.stream();
        list.add(4);
        List<Integer> resultList = stream.collect(toList());
        System.out.println("input : " + list);
        System.out.println("output : " + resultList);
        System.out.println();
    }

    public void threadSafeParallelInterference() {
        System.out.println("threadSafeParallelInterference...");
        List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Stream<Integer> stream = list.parallelStream();
        list.add(4);
        List<Integer> resultList = stream.collect(toList());
        System.out.println("input : " + list);
        System.out.println("output : " + resultList);
        System.out.println();
    }

    public void statefulLambda() {
        System.out.println("statefulLambda...");
        Set<Integer> seen = Collections.synchronizedSet(new HashSet<>());
//        Set<Integer> seen = new HashSet<>();
        seen.add(1);
        seen.add(2);
        seen.add(3);
        seen.add(4);
        System.out.println("input : " + seen);
//        List<Integer> list = seen.stream()
//                .parallel()
//                .map(x -> {
//                    if(seen.add(x*x))
//                        return x*x;
//                    else return x;
//                })
//                .collect(toList());
        List<Integer> list = seen.stream()
                .map(x -> {
                    if(seen.add(x*x))
                        return x*x;
                    else return x;
                })
                .collect(toList());
        System.out.println("output : " + list);
        System.out.println();
    }

    public void processStreamAndAdd() {
        System.out.println("processStreamAndAdd...");
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("input : " + input);
        List<Integer> output = new ArrayList<>();
        input.stream()
            .map(x -> x*x)
            .forEach(x -> output.add(x));
        System.out.println("output : " + output);
        System.out.println();
    }

    public void streamOrderingEffect() {
        System.out.println("streamOrderingEffect...");
        System.out.print("Parallel Sample list : ");
        IntStream.range(0, 5)
                .parallel()
                .map(x -> x*2)
                .forEach(System.out::print);
        System.out.println();
        System.out.print("Sequential Sample list : ");
        IntStream.range(0, 5)
                .map(x -> x*2)
                .forEach(System.out::print);
        System.out.println();
        System.out.println();
    }

    public void processParallelStreamAndAdd() {
        System.out.println("processParallelStreamAndAdd...");
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("input : " + input);
        List<Integer> output = new ArrayList<>();
        input.parallelStream()
                .map(x -> x*x)
                .forEach(e -> output.add(e));
        //potential side-effects
        System.out.println("output : " + output);
        System.out.println();
    }

    public void processParallelStreamNoSideEffects() {
        System.out.println("processParallelStreamNoSideEffects...");
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("input : " + input);
        List<Integer> output = input.parallelStream()
                                    .map(x -> x*x)
                                    .collect(toList());
        //no side-effects
        //because it is a safer reduction operation
        System.out.println("output : " + output);
        System.out.println();
    }

}
