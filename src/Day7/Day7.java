package com.company;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {


        String fileName = "C://temp//input7.txt";

        try {
            List<String> inputRows = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
            //programs.forEach(System.out::println);

            System.out.println("part1: " + part1(inputRows));
            System.out.println("part2: " + part2(inputRows));

        } catch (IOException e) {
            System.out.println("Main failed " + e.getMessage());
        }
    }

    private static Leaf createTree(List<String> inputRows) {
        Map<String, Leaf> tree = new HashMap<String, Leaf>();

        // Create all nodes
        inputRows.forEach(row -> {
            String[] tokens = row.split(" ");
            Leaf leaf = new Leaf();
            leaf.name = tokens[0];
            leaf.weight = Integer.valueOf(tokens[1].substring(1, tokens[1].length() - 1));
            tree.put(leaf.name, leaf);
        });

        // If a node has children, find the children as well as set the children's father to the current node
        inputRows.forEach(row -> {
            String[] tokens = row.split(" ");
            String tag;
            if (tokens.length > 3) {
                List<Leaf> x = new ArrayList<Leaf>();
                for (int i = 3; i < tokens.length; i++) {
                    if (i < tokens.length - 1) {
                        tag = tokens[i].substring(0, tokens[i].length() - 1);
                    } else {
                        tag = tokens[i];
                    }
                    Leaf q = tree.get(tag);
                    q.parent = tree.get(tokens[0]);
                    x.add(tree.get(tag));
                    tree.put(q.name, q);
                }
                Leaf p = tree.get(tokens[0]);
                p.children = x;
            }
        });

        // getting the root
        Leaf root = tree.get(inputRows.get(0).split(" ")[0]);
        while (root.parent != null) root = root.parent;
        return root;
    }

    public static String part1(List<String> inputRows) {
        Leaf root = createTree(inputRows);
        return root.name;
    }

    public static long part2(List<String> inputRows) {
        Leaf root = createTree(inputRows);
        Leaf wrongProgram = root;
        long correctWeight = -1;

        // please note that this actually is a breadth-first search
        do {
            // by sorting the list this way, we know that the wrong program is the first one or the last one

            wrongProgram.children.sort((x, y) -> (int) (x.weight + x.kidsWeight() - y.weight - y.kidsWeight()));

            Leaf k1 = wrongProgram.children.get(0);
            Leaf k2 = wrongProgram.children.get(1); // totally NOT the wrong program
            Leaf k3 = wrongProgram.children.get(wrongProgram.children.size() - 1);

            long k2TotalWeight = k2.weight + k2.kidsWeight();
            wrongProgram = k1.weight + k1.kidsWeight() == k2TotalWeight ? k3 : k1;

            // store temp correctWeight
            correctWeight = wrongProgram.weight - (wrongProgram.weight + wrongProgram.kidsWeight() - k2TotalWeight);

        }
        while (wrongProgram.children.stream().map(x -> x.weight + x.kidsWeight()).distinct().collect(Collectors.toList()).size() > 1); // program's kids are balanced, stop the loop

        return correctWeight;
    }
}

