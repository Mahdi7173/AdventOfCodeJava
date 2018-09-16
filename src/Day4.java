package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static boolean correctPassPhrase(List<String> pharaseStrings) {
        Set<String> uniqueSet = new HashSet<String>(pharaseStrings);
        return uniqueSet.stream().map(s -> Collections.frequency(pharaseStrings, s)).allMatch(occurence -> occurence == 1);

    }

    private static List<String> sortElementOfPhrase(List<String> phrases) {

        List<String> passPhraseWithSortedElements =
                phrases.
                        stream().
                        map(element -> Pattern.compile("").
                                splitAsStream(element).
                                sorted().
                                collect(Collectors.joining(""))).
                        collect(Collectors.toList());

        System.out.println("Sorted: " + passPhraseWithSortedElements);
        return passPhraseWithSortedElements;
    }

    public static void main(String[] args) {

        String fileName = "C://temp//input4.txt";
        List<Integer> countCorrectPhrase = new ArrayList<Integer>();
        List<Integer> countCorrectPhrase2 = new ArrayList<Integer>();

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> {
                Pattern pattern = Pattern.compile("\\s+");
                List<String> lineStrings = pattern.splitAsStream(line)
                        .collect(Collectors.toList());

                System.out.println("Pass Phrase: " + lineStrings);

                //PART1:
                if (correctPassPhrase(lineStrings)) {
                    countCorrectPhrase.add(1);
                }

                //PART2:
                if (correctPassPhrase(sortElementOfPhrase(lineStrings))) {
                    countCorrectPhrase2.add(1);
                }
            });

        } catch (IOException e) {
            System.out.println("Main failed " + e.getMessage());
        }

        System.out.println("\nNO of correct passwords based on part1 rule: " + countCorrectPhrase.size());
        System.out.println("NO of correct passwords based on part2 rule: " + countCorrectPhrase2.size());

    }
}
