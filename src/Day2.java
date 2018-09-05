package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static int performOperation(List<Integer> values, int valueIfEmpty, BinaryOperator<Integer> op) {
        int result = valueIfEmpty;

        for (Integer value : values) {
            result = op.apply(result, value);
        }

        return result;
    }

    public static void main(String[] args) {

        String fileName = "C://temp//input2.txt";
        int sumDiff = 0;
        //BinaryOperator<Integer> minOp = (i1, i2) -> Integer.min(i1, i2);
        //BinaryOperator<Integer> maxOp = (i1, i2) -> Integer.max(i1, i2);
        BinaryOperator<Integer> sumOp = (i1, i2) -> Integer.sum(i1, i2);
        List<Integer> listOfDiff = new ArrayList<Integer>();

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> {

                Pattern pattern = Pattern.compile("\\s+");
                List<Integer> lineNumbers = pattern.splitAsStream(line)
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());

                System.out.println("Line as Integer: " + lineNumbers);

                int minValue = lineNumbers.stream().reduce(Integer::min).get();
                int maxValue = lineNumbers.stream().max(Integer::compare).get();
                //int minValue = performOperation(lineNumbers, 0, minOp);
                //int maxValue = performOperation(lineNumbers, 0, maxOp);
                int diff = maxValue - minValue;
                listOfDiff.add(diff);

                System.out.println("Minimum: " + minValue + ", Maximum: " + maxValue + ", Diff: " + diff);

            });

            sumDiff  = performOperation(listOfDiff, 0, sumOp);
            System.out.println("\n\nsumDiff is: " + sumDiff);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
