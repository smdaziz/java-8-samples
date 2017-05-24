package com.godaddy.sse.ecommerce.greg.bly;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.of;

/**
 * Created by 847557 on 5/23/2017.
 */
public class StringArrayToIntArray {

    public static void main(String args[]) {
        String input[] = {"1", "0", "0", "1", "0"};
//        System.out.println(input);
        int[] output = stringToIntArray(input);
        for(int i = 0; i < input.length; i++) {
            System.out.println("String : " + input[i] + "; int : " + output[i]);
        }
        String sInput = "1010010101x2Ag08df.@0011";
        System.out.println(sInput);
        int[] iOutput = stringToIntArray(sInput);
        for(int i = 0; i < iOutput.length; i++) {
            System.out.println("int output : " + iOutput[i]);
        }
    }

    private static int[] stringToIntArray(String[] input) {
        List<Integer> list = Stream.of(input)
                .filter(s -> {
//                    System.out.println(s);
                    return "0".equals(s) || "1".equals(s);
                })
                .map(s -> {
                    return Integer.parseInt(s);
                })
                .collect(Collectors.toList());
        return list.stream().mapToInt(i -> i).toArray();
    }

    private static int[] stringToIntArray(String input) {
        return input.chars()
                .filter(s -> s == 48 || s == 49)
                .map(s -> {
                    if(s == 48)
                        return 0;
                    else {
                        return 1;
                    }
                })
                .toArray();
    }

}
