package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String fileName = "C://temp//input5.txt";
        int currentIndex = 0;
        int nextIndex = 0;
        int counter = 0;

        try {
            List<Integer> steps = Files.lines(Paths.get(fileName)).map(Integer::valueOf).collect(Collectors.toList());
            System.out.println(steps);

            do {
                nextIndex = steps.get(currentIndex) + nextIndex;
                steps.set(currentIndex, (steps.get(currentIndex) < 3) ? (steps.get(currentIndex) + 1) : (steps.get(currentIndex) - 1));
                currentIndex = nextIndex;
                counter = counter + 1;

            } while (nextIndex < steps.size());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(counter);
        ;
    }
}
