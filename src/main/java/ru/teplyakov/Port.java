package ru.teplyakov;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Port {

    public static List<List<Integer>> convertIndexesToSequencesLoop(String[] indexes) {
        List<List<Integer>> sequences = new ArrayList<>();
        for (String index : indexes) {
            if (!index.isEmpty() && !index.isBlank()) {
                Set<Integer> sequence = new HashSet<>();
                String[] parts = index.split(",");
                for (String part : parts) {
                    if (part.contains("-")) {
                        String[] range = part.split("-");
                        for (int i = Integer.parseInt(range[0]); i <= Integer.parseInt(range[1]); i++) {
                            sequence.add(i);
                        }
                    } else {
                        sequence.add(Integer.parseInt(part));
                    }
                }
                sequences.add(new ArrayList<>(sequence));
            }
        }
        return sequences;
    }

    public static List<List<Integer>> convertIndexesToSequencesStream(String[] indexes) {
        return Arrays.stream(indexes)
                .filter(index -> !index.isEmpty() && !index.isBlank())
                .map(index -> Arrays.stream(index.split(","))
                        .flatMap(part -> {
                            if (part.contains("-")) {
                                String[] range = part.split("-");
                                int start = Integer.parseInt(range[0]);
                                int end = Integer.parseInt(range[1]);
                                return IntStream.rangeClosed(start, end).boxed();
                            } else {
                                return Stream.of(Integer.parseInt(part));
                            }
                        })
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> getUniqueOrderedGroups(List<List<Integer>> sequences) {
        List<List<Integer>> groups = new ArrayList<>();
        generateGroups(sequences, new ArrayList<>(), groups);
        return groups;
    }

    private static void generateGroups(List<List<Integer>> sequences, List<Integer> currentGroup, List<List<Integer>> groups) {

        for (int seq : sequences.get(0)) {
            currentGroup.add(seq);
            if (sequences.size() > 1) {
                generateGroups(sequences.subList(1, sequences.size()), currentGroup, groups);
            } else {
                groups.add(new ArrayList<>(currentGroup));
            }
            currentGroup.remove(currentGroup.size() - 1);
        }
    }

    public static void main(String[] args) {
//        String[] indexes = {"1,3-5","3-4"};
//        String[] indexes = {"1,3-5", "4,2-5", "2", "", " ", "3-4"};
//        String[] indexes = {"9,12", "1,3-5", "4,2-5", "2", "", " ", "3-4"};
//        String[] indexes = {"2,4", "2,4"};
        String[] indexes = {};
//        String[] indexes = {"4,2-5"};
//        String[] indexes = {"1,3-5", "2", "3-4", "10-12"};
//        List<List<Integer>> sequences = convertIndexesToSequencesLoop(indexes);
        List<List<Integer>> sequences = convertIndexesToSequencesStream(indexes);
        List<List<Integer>> groups = getUniqueOrderedGroups(sequences);
//        System.out.println(groups.size());
        System.out.println(groups);
    }
}