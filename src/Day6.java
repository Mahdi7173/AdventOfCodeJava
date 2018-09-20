package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void updateBlock(List<Integer> inputBlock){
        int indexOfMax = IntStream.range(
                0, inputBlock.size()).reduce((a, b) -> inputBlock.get(a) < inputBlock.get(b) ? b : a).getAsInt();
        //System.out.println("index: " + indexOfMax + " , value  " + blocks.get(indexOfMax));

        int NoTorReallocate = inputBlock.get(indexOfMax);
        inputBlock.set(indexOfMax, 0);
        int nextIndex = indexOfMax + 1;

        while (NoTorReallocate > 0) {
            if (nextIndex < inputBlock.size()) {
                inputBlock.set(nextIndex, inputBlock.get(nextIndex) + 1);
                NoTorReallocate = NoTorReallocate - 1;
                nextIndex = nextIndex + 1;
            } else {
                nextIndex = 0;
            }
        }
    }

    public static void main(String[] args) {
        String fileName = "C://temp//input6.txt";
        List<List<Integer>> balanceHistory = new ArrayList<List<Integer>>();

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> {
                Pattern pattern = Pattern.compile("\\s+");
                List<Integer> blocks = pattern.splitAsStream(line).map(Integer::valueOf)
                        .collect(Collectors.toList());

                int counter = 0;
                int indexOfBlocks = 0;

                do {

                    indexOfBlocks = IntStream.range(0, balanceHistory.size()).
                            filter(i -> balanceHistory.get(i).equals(blocks)).findFirst().orElse(-1);

                    if (indexOfBlocks != -1) {
                        break;
                    }

                    balanceHistory.add(new ArrayList<Integer>(blocks));

                    updateBlock(blocks);

                    counter = counter + 1;
                    System.out.println("counter is : " + counter);

                } while (true);

                System.out.println("counter is : " + counter);
                System.out.println("Number of cycles : " + (counter - indexOfBlocks));

            });

        } catch (IOException e) {
            System.out.println("Main failed " + e.getMessage());
        }
    }
}