package random.array.search;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayDuplicates {
    private static Object[] getDuplicatesByStreamGroup(int[] arr) {
        return IntStream.of(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(m -> m.getValue() > 1)
                .map(Map.Entry::getKey)
                .toArray();
    }

    private static int[] getDuplicatesByFilter(int[] arr) {
        Set<Integer> elements = new HashSet<>();
        return IntStream.of(arr)
                .filter(n -> !elements.add(n))
                .toArray();
    }

    private static int[] getDuplicatesByFrequency(int[] arr) {
        final List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return Arrays.stream(IntStream.of(arr)
                        .filter(n -> Collections.frequency(list, n) > 1)
                        .toArray())
                .boxed()
                .collect(Collectors.toSet())
                .stream().mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 4, 5, 10, 8, 9, 5, 10};

        // Input
        System.out.println("Input - " + Arrays.toString(arr));

        // Duplicates
        System.out.println("Duplicate Elements by Stream Grouping - " + Arrays.toString(
                getDuplicatesByStreamGroup(Arrays.copyOf(arr, arr.length))));
        System.out.println("Duplicate Elements by Filter - " + Arrays.toString(
                getDuplicatesByFilter(Arrays.copyOf(arr, arr.length))));
        System.out.println("Duplicate Elements by Frequency - " + Arrays.toString(
                getDuplicatesByFrequency(Arrays.copyOf(arr, arr.length))));
    }
}
