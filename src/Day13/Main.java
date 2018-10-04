package Day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        String fileName = "C://temp//input13.txt";
        List<String> inputRows = null;

        try {
            inputRows = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.print("Part1 result: " + main.part1(inputRows, 0));

        //Part2
        int delay = 0;
        int caught = 0;

        do {
            caught = main.part2(inputRows, delay);
            if (caught == 0) {
                break;
            }
            ++delay;
        } while (true);

        System.out.print("Delay is: " + delay);
        //Part2

    } // main method

    public int part2(List<String> inputRows, int delay) {
        Main mainpart2 = new Main();
        List<FireWall> fireWallList = null;
        int caught = 0;

        Map<Integer, Integer> mappedInput = mainpart2.makeMapOfInput(inputRows);

        fireWallList = mainpart2.formFireWallList(mappedInput);
        //fireWallList.forEach(System.out::println);

        int cnt = 1;
        while (cnt <= delay) {
            for (int j = 0; j < fireWallList.size(); j++) {
                if (fireWallList.get(j) != null) {
                    fireWallList.get(j).moveToPosition();
                }
            }
            ++cnt;
        }

        for (int i = 0; i <= mainpart2.findNumLayer(mappedInput); i++) { // layers
            for (int j = 0; j < fireWallList.size(); j++) {        // fire walls
                if ((fireWallList.get(j) != null)) {
                    if ((fireWallList.get(j).getRange().get(0) == 1) && (j == i)) {
                        ++caught;
                        break;
                    }
                } else {
                    continue;
                }
                fireWallList.get(j).moveToPosition();
            } if (caught > 0) break;
        }
        return caught;
    }

    public int part1(List<String> inputRows, int delay) {
        Main mainpart1 = new Main();
        List<FireWall> fireWallList = null;
        int caught = 0;

        Map<Integer, Integer> mappedInput = mainpart1.makeMapOfInput(inputRows);

        fireWallList = mainpart1.formFireWallList(mappedInput);
        //fireWallList.forEach(System.out::println);

        for (int i = 0; i <= mainpart1.findNumLayer(mappedInput); i++) { // layers
            for (int j = 0; j < fireWallList.size(); j++) {        // fire walls
                if ((fireWallList.get(j) != null)) {
                    if ((fireWallList.get(j).getRange().get(0) == 1) && (j == i)) {
                        caught += (i * fireWallList.get(j).getRange().size());
                    }
                } else {
                    continue;
                }
                fireWallList.get(j).moveToPosition();
            }
        }
        return caught;
    }

    public List<FireWall> formFireWallList(Map<Integer, Integer> mappedInput) {
        Main mainFW = new Main();
        List<FireWall> fire = new ArrayList<FireWall>();

        for (int i = 0; i <= mainFW.findNumLayer(mappedInput); i++) {
            Integer rangeSize = mappedInput.get(i);
            if (rangeSize != null) {
                fire.add(new FireWall(rangeSize));
            } else {
                fire.add(null);
            }
        }

        //fire.forEach(System.out::println);
        return fire;
    }

    public int findNumLayer(Map<Integer, Integer> mappedInput) {
        return mappedInput
                .entrySet()
                .stream()
                .map(integerIntegerEntry -> integerIntegerEntry.getKey())
                .reduce(Integer::max)
                .get();
    }

    public Map<Integer, Integer> makeMapOfInput(List<String> inputRows) {

        Map<Integer, Integer> inputInMap = new HashMap<Integer, Integer>();

        inputRows.forEach(s -> {
            String[] tokens = s.split(": ");
            inputInMap.put(Integer.valueOf(tokens[0].trim()), Integer.valueOf(tokens[1].trim()));
        });

        //System.out.println("MAP: " + inputInMap);
        return inputInMap;
    }

}
