package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    // Purpose: Finding and dividing the dividable digits
    private static int findResultOfDividing(List<Integer> numbers) {
        int resultOfDividing = 0;

        resultOfDividing =
                IntStream.range(0, numbers.size() - 1)
                        .map(i ->
                                numbers.
                                        stream().
                                        filter(j -> {
                                            return (numbers.get(i) % j == 0) && (numbers.get(i) != j);
                                        }).map(z -> numbers.get(i) / z).findFirst().orElse(0)
                        )
                        .filter(k ->  k != 0)
                        .findFirst().getAsInt();

        return resultOfDividing;
    }

    // purpose: To subtract the maximum and minimum of each row
    private static int findMaxMinDiff(List<Integer> lineValues) {
        return (lineValues.get(0) - lineValues.get(lineValues.size() - 1));
    }

    public static void main(String[] args) {

        String fileName = "C://temp//input2.txt";
        List<Integer> resultOfDivisions = new ArrayList<Integer>();
        List<Integer> resultOfMinMaxDiff = new ArrayList<Integer>();

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> {
                Pattern pattern = Pattern.compile("\\s+");
                List<Integer> lineNumbers = pattern.splitAsStream(line)
                        .map(Integer::valueOf)
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());

                resultOfDivisions.add(findResultOfDividing(lineNumbers));
                resultOfMinMaxDiff.add(findMaxMinDiff(lineNumbers));
            });
        } catch (IOException e) {
            System.out.println("Main failed " + e.getMessage());
        }

        System.out.println("Part1 - The summation of differences of minimum and maximum of all rows: "
                + resultOfDivisions.stream().reduce(0, (x, y) -> (x + y)));

        System.out.println("Part2 - The summation of divisions of the dividable numbers of all the rows: "
                + resultOfDivisions.stream().reduce(0, (x, y) -> (x + y)));

    }
}
