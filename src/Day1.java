package com.company;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jdk.nashorn.internal.objects.NativeArray.reduce;

public class Main {

    private static int pariUp(List<Integer> listLineNumbers, int parts) {

        Predicate<Pair<Integer, Integer>> pairFilter = pairElements -> pairElements.getValue() == pairElements.getKey();
        BinaryOperator<Integer> sumPart = (currentVal, nextVal) -> (currentVal + nextVal);

        // This section populate the List<Pair> from the lineNumbers
        List<Pair<Integer, Integer>> listOfPairs = new ArrayList<Pair<Integer, Integer>>();
        int i = 0;
        int result = 0;

        try {
            if (parts == 1) {
                while (i < listLineNumbers.size() - 1) {
                    listOfPairs.add(new Pair<Integer, Integer>(listLineNumbers.get(i), listLineNumbers.get(i + 1)));
                    i = i + 1;
                }
                if (i != 0) {
                    listOfPairs.add(new Pair<Integer, Integer>(listLineNumbers.get(0), listLineNumbers.get(i)));
                }
            } else if (parts == 2) {
                while (i < (listLineNumbers.size() / parts)) {
                    listOfPairs.add(new Pair<Integer, Integer>(listLineNumbers.get(i), listLineNumbers.get(i + (listLineNumbers.size() / parts))));
                    i = i + 1;
                }
            }
            //System.out.println("Pairs: " + listOfPairs);

            result = listOfPairs.stream()
                    .filter(pairFilter)
                    .map(pair -> parts * (pair.getValue()))
                    .reduce(0, sumPart);


        } catch (Exception e) {
            System.out.println("\npariUp: Failed " + e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {

        String fileName = "C://temp//input1.txt";

        try {
            // Read the file, Split it char by char and convert it to List<Integer>
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> {
                Pattern pattern = Pattern.compile("(?<!^)");
                List<Integer> lineNumbers = pattern.splitAsStream(line)
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());

                System.out.println("\nLine as Integer: " + lineNumbers);

                System.out.print("Result of part1: " + pariUp(lineNumbers, 1));
                System.out.print(", and part2: " + pariUp(lineNumbers, 2));
            });

        } catch (Exception e) {
            System.out.println("\nmain: Failed " + e.getMessage());
            //e.printStackTrace();
        }
        System.out.println("\nEnd of main");
    }
}
