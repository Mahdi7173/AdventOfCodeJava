package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Main {

    public static int sumOfDigits(List<Integer> values, int valueIfEmpty, BinaryOperator<Integer> operation) {
        int result = valueIfEmpty;

        for (Integer value : values) {
            result = operation.apply(result, value);
        }

        return result;
    }

    public static void main(String[] args) {

        String fileName = "C://temp//input.txt";

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> {
                System.out.println("Line: " + line);
                char[] temp = line.toCharArray();
                List<Integer> digitsToSumUp = new ArrayList<Integer>();

                for (int i = 0; i < temp.length - 1; i++) {
                    if (temp[i] == temp[i + 1]) {
                        digitsToSumUp.add(Integer.parseInt(String.valueOf(temp[i])));
                    }
                }
                if (temp[0] == temp[temp.length - 1]) {
                    digitsToSumUp.add(Integer.parseInt(String.valueOf(temp[0])));
                }

                //digitsToSumUp.forEach(System.out::println);

                BinaryOperator<Integer> sumOp = (inp1, inp2) -> inp1 + inp2;
                int sum = sumOfDigits(digitsToSumUp, 0, sumOp);
                System.out.println("Summation is: " + sum);

                System.out.println();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
