package ru.teplyakov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Port {

    public static List<List<Integer>> convertIndexesToSequencesStream(String[] indexes) {
        if (indexes.length != 0) {
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
        } else {
            List<List<Integer>> list = new ArrayList<>();
            list.add(Collections.emptyList());
            return list;
        }
    }

    public static List<List<Integer>> getUniqueOrderedGroups(List<List<Integer>> sequences) {
        List<List<Integer>> groups = new ArrayList<>();
        generateGroups(sequences, new ArrayList<>(), groups);
        return groups;
    }

    private static void generateGroups(List<List<Integer>> sequences, List<Integer> currentGroup, List<List<Integer>> groups) {
        if (sequences.size() != 0) {
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
    }

    public static void main(String[] args) {
        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> sequences = convertIndexesToSequencesStream(indexes);
        List<List<Integer>> groups = getUniqueOrderedGroups(sequences);
        System.out.println(groups);
    }
}